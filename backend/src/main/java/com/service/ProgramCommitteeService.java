package com.service;

import com.entities.*;
import com.mapper.EvaluationMapper;
import com.input.EvaluationInput;
import com.mapper.PaperMapper;
import com.mapper.UserMapper;
import com.model.*;
import com.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class ProgramCommitteeService {

    private RecommendationRepository recommendationRepository;
    private PaperRepository paperRepository;
    private UserRepository userRepository;
    private SectionRepository sectionRepository;
    private EvaluationRepository evaluationRepository;
    private PCMemberRepository pcMemberRepository;
    private BidRepository bidRepository;

    @Autowired
    public ProgramCommitteeService(RecommendationRepository recommendationRepository, PaperRepository paperRepository, UserRepository userRepository, SectionRepository sectionRepository, EvaluationRepository evaluationRepository, PCMemberRepository pcmmemberRepository, BidRepository bidRepository) {
        this.recommendationRepository = recommendationRepository;
        this.paperRepository = paperRepository;
        this.userRepository = userRepository;
        this.sectionRepository = sectionRepository;
        this.evaluationRepository = evaluationRepository;
        this.pcMemberRepository = pcmmemberRepository;
        this.bidRepository = bidRepository;
    }


    @Transactional
    public PaperJson assignPaperToSection(int paperId, int sectionId) {
        PaperEntity existingPaper = paperRepository.findById(paperId).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Paper with id " + paperId + " not found"));
        existingPaper.setSection(sectionRepository.findById(sectionId).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Section with id " + sectionId + " not found.")));

        paperRepository.save(existingPaper);
        return PaperMapper.entityToPaper(existingPaper);
    }

    @Transactional
    public boolean bidProposal(int paperId, String email, StatusJson status) {  // -> only if user is not already an author or reviewer of this paper
        PaperEntity paperEntity = paperRepository.findById(paperId).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Paper with id " + paperId + " not found!"));
        CommitteeMemberEntity pcMemberEntity = pcMemberRepository.findById(email).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "PC member " + email + " not found"));

        List<CommitteeMemberEntity> pcMemberList = new ArrayList<>(paperEntity.getBids().values());
        for (CommitteeMemberEntity pcMember : pcMemberList)
            if (!(pcMember.getEmail().equals(pcMemberEntity.getEmail()))) {
                BidEntity bidEntity = new BidEntity();
                bidEntity.setStatus(status);
                bidRepository.save(bidEntity);

                paperEntity.getBids().put(bidEntity, pcMemberEntity);
                pcMemberEntity.getPapers().values().forEach(paper -> System.out.println(paper.getTitle()));
                paperRepository.save(paperEntity);
                return true;
            }
        return false;
    }


    @Transactional
    public String assignPaper(int paperId, String email) {
        PaperEntity paperEntity = paperRepository.findById(paperId).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Paper with id " + paperId + " not found!"));
        CommitteeMemberEntity committeeMemberEntity = pcMemberRepository.findById(email).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Program committee with email " + email + " not found!"));

        if (paperEntity.getReviews().size() < 4) {

            committeeMemberEntity.addReview(paperEntity);
            return "You are allowed to review this paper.";
        } else return "You are not allowed to review this paper.";

    }


    @Transactional
    public EvaluationJson reviewPaper(int paperId, String email, EvaluationInput evaluationInput) {

        PaperEntity paperEntity = paperRepository.findById(paperId).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Paper with id " + paperId + " not found!"));
        CommitteeMemberEntity pcMemberEntity = pcMemberRepository.findById(email).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "PC member " + email + " not found"));

        RecommendationEntity recommendationEntity = new RecommendationEntity(evaluationInput.getRecommendation());
        recommendationRepository.save(recommendationEntity);

        EvaluationJson result = new EvaluationJson();

        for (EvaluationEntity evaluation : paperEntity.getReviews()) {
            if (evaluation.getReviewer().equals(pcMemberEntity)) {
                evaluation.setQualifier(evaluationInput.getQualifier());
                evaluation.setRecommendation(recommendationEntity);
                result = EvaluationMapper.entityToEvaluation(evaluation);
            }
        }

        return result;
    }

    @Transactional
    public List<PaperJson> getPapersOfReviewer(String email) {
        Optional<CommitteeMemberEntity> reviewer = pcMemberRepository.findById(email);
        if (reviewer.isPresent()) {
            return (reviewer.get().getEvaluations() != null ? reviewer.get().getPapers().values().stream()
                    .map(PaperMapper::entityToPaper)
                    .collect(Collectors.toList()) : new ArrayList<PaperJson>());
        } else {
            return new ArrayList<PaperJson>();
        }
    }

    @Transactional
    public List<ProgramCommitteeJson> getProgramCommitteeMembersForPaper(int paperId) {
        List<CommitteeMemberEntity> pcMembers = pcMemberRepository.findAll();
        List<ProgramCommitteeJson> programCommitteeJsons = new ArrayList<>();
        PaperEntity paper = paperRepository.findById(paperId).orElseThrow(() -> new RuntimeException("Paper does not exist!"));
        List<String> emailForMembersWithBids = new ArrayList<>();
        paper.getBids().forEach((bid, member) ->
        {
            programCommitteeJsons.add(new ProgramCommitteeJson(member.getEmail(), bid.getStatus().getValue()));
            emailForMembersWithBids.add(member.getEmail());
        });
        pcMembers.forEach(pcMember -> {
            if (!emailForMembersWithBids.contains(pcMember.getEmail())) {
                programCommitteeJsons.add(new ProgramCommitteeJson(pcMember.getEmail(), "NO BID"));
            }
        });
        Collections.sort(programCommitteeJsons, new Comparator<ProgramCommitteeJson>() {
            public int compare(ProgramCommitteeJson p1, ProgramCommitteeJson p2) {
                return p1.getStatus().compareTo(p2.getStatus());
            }
        });
        return programCommitteeJsons;
    }

    @Transactional
    public List<User> getAllProgramCommitteeMembers()
    {
        return pcMemberRepository.findAll().stream().map(member ->
        {
            User memberJson = UserMapper.entityToUser(member);
            return memberJson;
        }).collect(Collectors.toList());
    }
}
