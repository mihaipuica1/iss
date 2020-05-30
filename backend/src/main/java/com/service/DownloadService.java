package com.service;

import com.entities.PaperEntity;
import com.repository.PaperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;

@Service
public class DownloadService
{
    private static final String FILE_PATH = "C:\\Papers\\";
    private PaperRepository paperRepository;

    @Autowired
    public DownloadService(PaperRepository paperRepository)
    {
        this.paperRepository = paperRepository;
    }

    @Transactional
    public File download(int paperId)
    {
        PaperEntity paperEntity = paperRepository.findById(paperId).orElseThrow(() -> new RuntimeException("Paper does not exist!\n"));
        return new File(FILE_PATH + paperEntity.getFileName());
    }
}
