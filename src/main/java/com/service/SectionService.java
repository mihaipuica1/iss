package com.service;

import com.entities.SectionEntity;
import com.mapper.SectionMapper;
import com.model.Section;
import com.repository.SectionRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SectionService {

    private SectionRepository sectionRepository;

    @Autowired
    public SectionService(SectionRepository sectionRepository) { this.sectionRepository = sectionRepository;}

    @Transactional
    public Section addSection(Section section){

        SectionEntity entity = SectionMapper.sectionToEntity(section);
        return SectionMapper.entityToSection(sectionRepository.save(entity));
    }

    @Transactional
    public Section findById(int id) {
        SectionEntity entity = sectionRepository.findById(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Section with id " + id + " not found"));
        return SectionMapper.entityToSection(entity);
    }

    @Transactional
    public List<Section> getAll() {
        return sectionRepository.findAll().stream().map(SectionMapper::entityToSection).collect(Collectors.toList());
    }
}
