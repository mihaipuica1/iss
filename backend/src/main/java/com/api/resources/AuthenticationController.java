package com.api.resources;

import com.entities.ApplicationUser;
import com.input.Authentication;
import com.security.TokenUtil;
import com.service.AuthenticationService;
import com.web.json.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.Calendar;
import java.util.Date;

import static javax.ws.rs.core.Response.Status.NOT_FOUND;

@Path("/")
public class AuthenticationController {

    @Context
    private UriInfo uri;

    @Autowired
    private ServletContext context;

    private AuthenticationService authenticationService;
    private PasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }


    @GET
    @Path("authorize")
    public Response  authorize(@QueryParam("redirectUri") String redirectUri) throws UnsupportedEncodingException {
        // forward user to the login page with the desired redirect_uri as path param
        final URI url = UriComponentsBuilder
                .fromHttpUrl(uri.getBaseUri() + "login?redirect_uri=" + redirectUri)
                .build()
                .toUri();
        return Response.status(302)
                .header(HttpHeaders.LOCATION, url)
                .build();
    }

    @GET
    @Path("login")
    public Response authenticate(@QueryParam("redirect_uri") String redirect_uri) {
        InputStream resource = context.getResourceAsStream("login.html");
        return null == resource ? Response.status(NOT_FOUND).build() : Response.ok().entity(resource).build();
    }

    @POST
    @Path("register")
    @Consumes("application/json")
    public void register(Authentication profileInput) {
        authenticationService.register(profileInput);
    }

    @POST
    @Path("authenticate")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public JsonResponse authenticate(@QueryParam("redirect_uri") String redirectUri, Authentication profileInput) {

        String password = profileInput.getPassword();
        ApplicationUser profile = authenticationService.login(profileInput);

        if (bCryptPasswordEncoder.matches(password, profile.getPassword())) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            cal.add(Calendar.DATE, 1);
            String token = TokenUtil.createToken("ISS", profile.getUserName(), null,  profile.getFirstName(), cal.getTime());
            final String url = redirectUri + "?token=" + token;
            return new JsonResponse().with("redirectUrl", url).done();
        }

        return new JsonResponse().with("error", "The username or password you entered is incorrect.").done();
    }


}
