package com.api.resources;

import com.model.Proposal;
import com.service.ProposalService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/proposals")
public class PaperController {

    private ProposalService proposalService;

    @Autowired
    public PaperController(ProposalService proposalService) {
        this.proposalService = proposalService;
    }

    @POST
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Proposal addProposal(Proposal proposal) {
        return proposalService.addProposal(proposal);
    }

    @GET
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Proposal> getProposals() {
        return proposalService.getAll();
    }
}
