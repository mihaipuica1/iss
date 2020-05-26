package com.service;


import com.entities.AuthorEntity;
import com.entities.PaperEntity;
import com.input.PaperInput;
import com.mapper.PaperMapper;
import com.model.PaperJson;
import com.repository.AuthorRepository;
import com.repository.PaperRepository;
import com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class PaperService {

    private PaperRepository paperRepository;
    private AuthorRepository authorRepository;

    @Autowired
    public PaperService(PaperRepository paperRepository, AuthorRepository authorRepository) {
        this.paperRepository = paperRepository;
        this.authorRepository = authorRepository;
    }

    @Transactional
    public PaperJson submitPaper(PaperInput paperInput) {

        PaperEntity entity = PaperMapper.paperToEntity(paperInput);
        for(String email : paperInput.getAuthors())
        {
            Optional<AuthorEntity> author = authorRepository.findById(email);
            if(author.isPresent())
            {
                entity.addAuthor(author.get());
            }
            else
            {
                AuthorEntity newAuthor = new AuthorEntity(email);
                authorRepository.save(newAuthor);
                entity.addAuthor(newAuthor);
            }
        }

        paperRepository.save(entity);
        return PaperMapper.entityToPaper(entity);
    }



    @Transactional
    public List<PaperJson> getAll() {
        return paperRepository.findAll().stream().map(PaperMapper::entityToPaper).collect(Collectors.toList());
    }

    @Transactional
    public void updatePaper(int paperId, String newFileName) {

        paperRepository.findById(paperId).ifPresent(paperEntity -> paperEntity.setFileName(newFileName));
    }

}
