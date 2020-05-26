package com.api.resources;

import com.input.PaperInput;
import com.model.PaperJson;
import com.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/")
public class PaperController {

    private PaperService paperService;

    @Autowired
    public PaperController(PaperService paperService) {
        this.paperService = paperService;
    }

    @POST
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    @Path("paper")
    public PaperJson submitPaper(PaperInput paper) {
        return paperService.submitPaper(paper);
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("papers")
    public List<PaperJson> getAll() {

        return paperService.getAll();
    }

    @PUT
    @Path("paper/{paperId}")
    @Consumes(MediaType.TEXT_PLAIN)
    public void updatePaper(@PathParam("paperId") int paperId, String newContent) {
        paperService.updatePaper(paperId, newContent);
    }


}
