package com.api.resources;

import com.model.Author;
import com.model.Proposal;
import com.service.AuthorService;
import com.service.ProposalService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/authors")
public class AuthorController
{
    private AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @POST
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Author addAuthor(Author author) {
        return authorService.addAuthor(author);
    }

    @GET
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Author> getAuthors() {
        return authorService.getAll();
    }
}
