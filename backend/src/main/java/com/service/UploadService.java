package com.service;
import com.repository.PaperRepository;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class UploadService
{
    private PaperRepository paperRepository;

    @Autowired
    public UploadService(PaperRepository paperRepository)
    {
        this.paperRepository = paperRepository;
    }


    public void upload(InputStream uploadedInputStream, FormDataContentDisposition fileDetails)
    {
        //destination folder: "C:\Papers\"
        String uploadedFileLocation = "C:\\Papers\\" + fileDetails.getFileName();
        writeToFile(uploadedInputStream, uploadedFileLocation);
    }

    private void writeToFile(InputStream uploadedInputStream,
                             String uploadedFileLocation) {
        try {
            OutputStream out = new FileOutputStream(new File(
                    uploadedFileLocation));
            int read = 0;
            byte[] bytes = new byte[1024];

            out = new FileOutputStream(new File(uploadedFileLocation));
            while ((read = uploadedInputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
