package com.api.resources;

import com.model.Section;
import com.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/sections")
public class SectionController {

    private SectionService sectionService;

    @Autowired
    public SectionController(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    @POST
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Section addSection(Section section) {
        return sectionService.addSection(section);
    }

    @GET
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Section> getSections() {
        return sectionService.getAll();
    }
}
