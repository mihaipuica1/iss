package com.service;


import com.entities.AuthorEntity;
import com.entities.ComiteeMemberEntity;
import com.mapper.AuthorMapper;
import com.mapper.ComiteeMapper;
import com.model.Author;
import com.model.ComiteeMember;
import com.repository.AuthorRepository;
import com.repository.ComiteeMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComiteeMemberService
{
    private ComiteeMemberRepository comiteeMemberRepository;

    @Autowired
    public ComiteeMemberService(ComiteeMemberRepository comiteeRepository) {
        this.comiteeMemberRepository = comiteeRepository;
    }

    @Transactional
    public ComiteeMember addComiteeMember(ComiteeMember member) {

        ComiteeMemberEntity entity = ComiteeMapper.comiteeMemberToEntity(member);
        return ComiteeMapper.entityToComiteeMember(comiteeMemberRepository.save(entity));

    }

    @Transactional
    public ComiteeMember findById(int id) {

        ComiteeMemberEntity entity = comiteeMemberRepository.findById(id).orElseThrow(()-> new HttpClientErrorException(HttpStatus.NOT_FOUND, "ComiteeMember with id " + id + " not found"));
        return ComiteeMapper.entityToComiteeMember(entity);
    }

    @Transactional
    public List<ComiteeMember> getAll() {
        return comiteeMemberRepository.findAll().stream().map(ComiteeMapper::entityToComiteeMember).collect(Collectors.toList());
    }
}