package com.api.resources;

import com.model.*;
import com.service.ProgramCommitteeService;
import com.service.UserService;
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
    @Path("/bid/{paperId}/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public void bidProposal(@PathParam("paperId") int paperId, @PathParam("email") String email, StatusJson status) {
        userService.bidProposal(paperId, email, status);
    }


    @PUT
    @Path("/paper/section/{paperId}/{sectionId}")
    @Produces(MediaType.APPLICATION_JSON)
    public PaperJson setPaperSection(@PathParam("paperId") int paperId, @PathParam("sectionId") int sectionId) {
        return pcService.setPaperSection(paperId, sectionId);
    }


}
