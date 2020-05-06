package com.service;


import com.entities.ConferenceEntity;
import com.mapper.ConferenceMapper;
import com.model.Conference;
import com.repository.ConferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConferenceService {


    private ConferenceRepository conferenceRepository;

    @Autowired
    public ConferenceService(ConferenceRepository repo) {
        this.conferenceRepository = repo;
    }

    @Transactional
    public Conference addConference(Conference conf) {

        ConferenceEntity entity = ConferenceMapper.conferenceToEntity(conf);
        return ConferenceMapper.entityToConference(conferenceRepository.save(entity));

    }

    @Transactional
    public Conference findById(int id) {

        ConferenceEntity entity = conferenceRepository.findById(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Conference with id " + id + " not found"));
        return ConferenceMapper.entityToConference(entity);
    }

    @Transactional
    public List<Conference> getAll() {
        return conferenceRepository.findAll().stream().map(ConferenceMapper::entityToConference).collect(Collectors.toList());
    }

}


