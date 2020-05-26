package com.service;


import com.entities.*;
import com.model.StatusJson;
import com.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class UserService {

    private UserRepository userRepository;
    private SectionRepository sectionRepository;
    private PaperRepository paperRepository;
    private PCMemberRepository pcMemberRepository;
    private BidRepository bidRepository;

    @Autowired
    public UserService(UserRepository userRepository, SectionRepository sectionRepository, PaperRepository paperRepository, PCMemberRepository pcMemberRepository, BidRepository bidRepository) {
        this.userRepository = userRepository;
        this.sectionRepository = sectionRepository;
        this.paperRepository = paperRepository;
        this.pcMemberRepository = pcMemberRepository;
        this.bidRepository = bidRepository;
    }


    @Transactional
    public void registerToSection(String email, int sectionId) {

        UserEntity existingUser = userRepository.findById(email).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "User with email " + email + " not found!"));
        SectionEntity section = sectionRepository.findById(sectionId).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Section not found!"));

        existingUser.setSection(section);
        userRepository.save(existingUser);
    }





}
