package com.service;

import com.entities.AuthorEntity;
import com.entities.PaperEntity;
import com.mapper.AuthorMapper;
import com.mapper.ProposalMapper;
import com.model.Author;
import com.model.Proposal;
import com.repository.AuthorRepository;
import com.repository.PaperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService
{
    private AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Transactional
    public Author addAuthor(Author author) {

        AuthorEntity entity = AuthorMapper.authorToEntity(author);
        return AuthorMapper.entityToAuthor(authorRepository.save(entity));

    }

    @Transactional
    public Author findById(int id) {

        AuthorEntity entity = authorRepository.findById(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Author with id " + id + " not found"));
        return AuthorMapper.entityToAuthor(entity);
    }

    @Transactional
    public List<Author> getAll() {
        return authorRepository.findAll().stream().map(AuthorMapper::entityToAuthor).collect(Collectors.toList());
    }
}
