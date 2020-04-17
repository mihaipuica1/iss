package com.api.resources;

import com.model.ComiteeMember;
        import com.service.ComiteeMemberService;
        import org.springframework.beans.factory.annotation.Autowired;

        import javax.ws.rs.*;
        import javax.ws.rs.core.MediaType;
        import java.util.List;



@Path("/comitee")
public class ComiteeMemberController
{
    private ComiteeMemberService comiteeMemberService;

    @Autowired
    public ComiteeMemberController(ComiteeMemberService comiteeService) {
        this.comiteeMemberService = comiteeService;
    }

    @POST
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public ComiteeMember addComiteeMember(ComiteeMember member) {
        return comiteeMemberService.addComiteeMember(member);
    }

    @GET
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ComiteeMember> getComiteeMembers() {
        return comiteeMemberService.getAll();
    }
}