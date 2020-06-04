package com.api.resources;

import com.input.EvaluationInput;
import com.model.*;
import com.service.ProgramCommitteeService;
import com.web.json.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Path("/programCommittee")
public class ProgramCommitteeController {

    private ProgramCommitteeService pcService;

    @Autowired
    public ProgramCommitteeController(ProgramCommitteeService pcService) {
        this.pcService = pcService;
    }


    @GET
    @Path("/bid/{paperId}/{email}/{status}")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public JsonResponse bidProposal(@PathParam("paperId") int paperId, @PathParam("email") String email, @PathParam("status") Status status) {
        if (pcService.bidProposal(paperId, email, status))
            return new JsonResponse().with("response", "OK!");
        else
            return new JsonResponse().with("response", "Not OK!");
    }


    @PUT
    @Path("/review/paper/{paperId}/{email}")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public EvaluationJson reviewPaper(@PathParam("paperId") int paperId, @PathParam("email") String email, EvaluationInput evaluationInput) {

        return pcService.reviewPaper(paperId, email, evaluationInput);
    }


    @PUT
    @Path("assign/paper/to/section/{paperId}/{sectionId}")
    @Produces(MediaType.APPLICATION_JSON)
    public PaperJson assignPaperToSection(@PathParam("paperId") int paperId, @PathParam("sectionId") int sectionId) {
        return pcService.assignPaperToSection(paperId, sectionId);
    }

    @PUT
    @Path("/assign/paper/to/review/{paperId}/{email}")
    public String assignPaper(@PathParam("paperId") int paperId, @PathParam("email") String email) {
        return pcService.assignPaper(paperId, email);
    }

    @GET
    @Path("/members/{paperId}")
    @Consumes("application/json")//
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProgramCommitteeJson> getProgramCommittee(@PathParam("paperId") int paperId) {
        return pcService.getProgramCommitteeMembersForPaper(paperId);
    }

    @GET
    @Path("/members")
    @Consumes("application/json")//
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getProgramCommittee() {
        return pcService.getAllProgramCommitteeMembers();
    }

    @GET
    @Path("/papers/{email}")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public List<PaperJson> getPapersOfReviewer(@PathParam("email") String email) {
        return pcService.getPapersOfReviewer(email);
    }
}
