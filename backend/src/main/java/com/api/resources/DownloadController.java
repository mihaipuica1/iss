package com.api.resources;

import com.model.PaperJson;

import java.io.File;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

@Path("/")
public class DownloadController {
    private static final String FILE_PATH = "C:\\Papers\\";

    @GET
    @Path("files/{fileName}")
    @Produces("application/pdf")
    public Response downloadFile(@PathParam("fileName") String fileName){
        File file = new File(FILE_PATH + fileName);
        ResponseBuilder response = Response.ok((Object) file);
        response.header("Content-Disposition","attachment; filename=" + fileName);
        return response.build();

    }
}
