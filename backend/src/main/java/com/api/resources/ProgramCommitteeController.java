package com.api.resources;

import com.model.*;
import com.service.ProgramCommitteeService;
import com.service.UserService;
import com.web.json.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@Path("/programCommittee")
public class ProgramCommitteeController {

    private ProgramCommitteeService pcService;
    private UserService userService;

    @Autowired
    public ProgramCommitteeController(ProgramCommitteeService pcService) {
        this.pcService = pcService;
    }


    @GET
    @Path("/bid/{paperId}/{email}/{status}")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public JsonResponse bidProposal(@PathParam("paperId") int paperId, @PathParam("email") String email, @PathParam("status") StatusJson status) {
        pcService.bidProposal(paperId, email, status);
        return new JsonResponse().with("response", "OK!");
    }


    @PUT
    @Path("/paper/section/{paperId}/{sectionId}")
    @Produces(MediaType.APPLICATION_JSON)
    public PaperJson setPaperSection(@PathParam("paperId") int paperId, @PathParam("sectionId") int sectionId) {
        return pcService.setPaperSection(paperId, sectionId);
    }




}
