package com.api.resources;

import com.model.Conference;
import com.service.ConferenceService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;



@Path("/conference")
public class ConferenceController
{
    private ConferenceService conferenceService;

    @Autowired
    public ConferenceController(ConferenceService confService) {
        this.conferenceService = confService;
    }

    @POST
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Conference addConference(Conference conference) {
        return conferenceService.addConference(conference);
    }

    @GET
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Conference> getComiteeMembers() {
        return conferenceService.getAll();
    }
}