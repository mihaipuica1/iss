package com.api.resources;

import com.service.UploadService;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

@Path("/")
public class UploadController {

    private UploadService uploadService;

    @Autowired
    public UploadController(UploadService uploadService)
    {
        this.uploadService = uploadService;
    }

    @POST
    @Path("upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response uploadPaper(
            @FormDataParam("file") InputStream uploadedInputStream,
            @FormDataParam("file") FormDataContentDisposition fileDetails) {

        //System.out.println(fileDetails.getFileName());

        this.uploadService.upload(uploadedInputStream, fileDetails);

        //String output = "File uploaded to : " + uploadedFileLocation;
        //System.out.println(output);
        return Response.ok("File uploaded = " + fileDetails.getFileName()).build();
    }
}
