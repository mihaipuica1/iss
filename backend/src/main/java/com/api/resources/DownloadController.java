package com.api.resources;

import com.model.PaperJson;
import com.service.DownloadService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

@Path("/")
public class DownloadController {
    private DownloadService downloadService;

    @Autowired
    public DownloadController(DownloadService downloadService)
    {
        this.downloadService = downloadService;
    }

    @GET
    @Path("download/{paperId}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response downloadFile(@PathParam("paperId") int paperId){
        File file = downloadService.download(paperId);
        ResponseBuilder response = Response.ok((Object) file);
        response.header("Content-Disposition","attachment; filename=" + file.getName());
        return response.build();

    }
}
