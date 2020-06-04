package com.service;


import com.entities.AuthorEntity;
import com.entities.EventEntity;
import com.entities.PaperEntity;
import com.input.PaperInput;
import com.mapper.PaperMapper;
import com.model.PaperJson;
import com.repository.AuthorRepository;
import com.repository.ConferenceRepository;
import com.repository.PaperRepository;
import com.repository.UserRepository;
import com.web.json.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class PaperService {

    private PaperRepository paperRepository;
    private AuthorRepository authorRepository;
    private ConferenceRepository conferenceRepository;

    @Autowired
    public PaperService(PaperRepository paperRepository, AuthorRepository authorRepository, ConferenceRepository cr) {
        this.paperRepository = paperRepository;
        this.authorRepository = authorRepository;
        this.conferenceRepository = cr;
    }

    @Transactional
    public Serializable submitPaper(PaperInput paperInput) {

        PaperEntity entity = PaperMapper.paperToEntity(paperInput);

        EventEntity event = conferenceRepository.findById(1).get();

        if(event.getProgram().getProposalDeadline().compareTo(LocalDateTime.now())<0)
            return new JsonResponse().with("status","Cannot submit paper after the date" + event.getProgram().getProposalDeadline().toString());



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
    public PaperJson findById(int id) {

        PaperEntity entity = paperRepository.findById(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Paper with id " + id + " not found"));
        return PaperMapper.entityToPaper(entity);
    }

    @Transactional
    public List<PaperJson> getAll() {
        return paperRepository.findAll().stream().map(PaperMapper::entityToPaper).collect(Collectors.toList());
    }

    @Transactional
    public void updatePaper(int paperId, PaperInput paperInput) {

        PaperEntity existingPaper = paperRepository.findById(paperId).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Paper with id " + paperId + " not found"));
        existingPaper.setTitle(paperInput.getTitle());
        existingPaper.setDescription(paperInput.getDescription());
        existingPaper.setFileName(paperInput.getFileName());
        existingPaper.setTopics(paperInput.getTopics());
        existingPaper.setKeywords(paperInput.getKeywords());

        for (String email : paperInput.getAuthors()) {
            Optional<AuthorEntity> author = authorRepository.findById(email);

            if (author.isPresent()) {
                if (!existingPaper.getAuthors().contains(author.get())) {
                    existingPaper.addAuthor(author.get());
                }
            } else {
                AuthorEntity newAuthor = new AuthorEntity(email);
                authorRepository.save(newAuthor);
                existingPaper.addAuthor(newAuthor);
            }
        }

        List<String> authors = existingPaper.getAuthors().stream().map(AuthorEntity::getEmail).collect(Collectors.toList());

        for (String email : authors) {
            if (!paperInput.getAuthors().contains(email)) {
                AuthorEntity authorEntity = authorRepository.findById(email).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Author with email: " + email + " not found."));
                existingPaper.getAuthors().remove(authorEntity);
            }
        }

        paperRepository.save(existingPaper);
    }


}
