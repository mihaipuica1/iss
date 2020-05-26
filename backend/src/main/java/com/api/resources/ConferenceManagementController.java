package com.api.resources;

import com.input.EventInput;
import com.input.LocationInput;
import com.input.ProgramInput;
import com.model.EventJson;
import com.model.LocationJson;
import com.model.ProgramJson;
import com.model.SectionJson;
import com.service.ConferenceManagementService;
import com.service.LocationService;
import com.web.json.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/")
public class ConferenceManagementController {

    private ConferenceManagementService conferenceService;
    private LocationService locationService;

    @Autowired
    public ConferenceManagementController(ConferenceManagementService conferenceService, LocationService locationService) {
        this.conferenceService = conferenceService;
        this.locationService = locationService;
    }

    @POST
    @Path("event")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public EventJson addEvent(EventInput event) {

        return conferenceService.addEvent(event);
    }

    @DELETE
    @Path("event/{id}")
    public void deleteEvent(@PathParam("id") int id) {
        conferenceService.deleteEvent(id);
    }

    @GET
    @Path("event/{id}")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public EventJson findEventById(@PathParam("id") int id) {
        return conferenceService.findEventById(id);
    }

    @PUT
    @Path("event/program/{eventId}")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public EventJson updateProgram(@PathParam("eventId") int eventId, ProgramInput program) {
        return conferenceService.updateEventProgram(eventId, program);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("events")
    public List<EventJson> getAllEvents() {
        return conferenceService.getAllEvents();
    }


    // ----------------------- program --------------------------------





    @PUT
    @Path("event/program/postpone/{eventId}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public ProgramJson changeProposalDeadline(@PathParam("eventId") int eventId, String newDate) {
        return conferenceService.changeProposalDeadline(eventId, newDate);
    }


    // -------------------- section ---------------------------------

    @POST
    @Path("event/section/{eventId}")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public SectionJson addSection(@PathParam("eventId") int eventId, SectionJson section) {

        return conferenceService.addSection(eventId, section);
    }

    @DELETE
    @Path("event/section/{sectionId}")
    public void deleteSection(@PathParam("sectionId") int sectionId) {
        conferenceService.deleteSection(sectionId);
    }


    @PUT
    @Path("event/section/supervisor/{sectionId}/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public SectionJson assignSupervisor(@PathParam("sectionId") int sectionId, @PathParam("email") String email) {
        return conferenceService.assignSupervisor(sectionId, email);
    }

    // -------------------- location ---------------------------------

    @POST
    @Consumes("application/json")
    @Path("event/location/{eventId}")
    @Produces(MediaType.APPLICATION_JSON)
    public LocationJson addLocation(@PathParam("eventId") int eventId, LocationInput location) {

        return locationService.addLocation(eventId, location);
    }

    @PUT
    @Path("event/committee/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public JsonResponse addProgramCommittee(@PathParam("email") String email){
        conferenceService.addProgramCommittee(email);
        return new JsonResponse().with("Response", "OK");
    }
}
