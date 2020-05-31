package com.service;

import com.entities.*;
import com.mapper.EvaluationMapper;
import com.input.EvaluationInput;
import com.mapper.PaperMapper;
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
    public List<ProgramCommitteeJson> getProgramCommittee(){
        List<CommitteeMemberEntity> pcMembers = pcMemberRepository.findAll();
        List<ProgramCommitteeJson> programCommitteeJsons = new ArrayList<>();
        List<PaperEntity> papers = paperRepository.findAll();
        Map<BidEntity, Map.Entry<Integer, CommitteeMemberEntity>> bidAndPcMemberAndPaper = new HashMap<>();
        List<String> emailForMembersWithBids = new ArrayList<>();
        papers.forEach(paper -> {
            paper.getBids().forEach((bid, member) ->
                    bidAndPcMemberAndPaper.put(bid, new AbstractMap.SimpleEntry<Integer, CommitteeMemberEntity>(paper.getId(), member)));
        });
        bidAndPcMemberAndPaper.forEach((bid, integerAndMember) ->
        {
            programCommitteeJsons.add(new ProgramCommitteeJson(integerAndMember.getValue().getEmail(), bid.getStatus().getValue(), integerAndMember.getKey()));
            emailForMembersWithBids.add(integerAndMember.getValue().getEmail());
        });
        pcMembers.forEach(pcMember->{
            if(!emailForMembersWithBids.contains(pcMember.getEmail()))
            {
                programCommitteeJsons.add(new ProgramCommitteeJson(pcMember.getEmail(), "NO BID", -1));
            }
        });
        Collections.sort(programCommitteeJsons, new Comparator<ProgramCommitteeJson>(){
            public int compare(ProgramCommitteeJson p1, ProgramCommitteeJson p2){
                return p1.getStatus().compareTo(p2.getStatus());
            }
        });
        return programCommitteeJsons;
    }

    @Transactional
    public List<PaperJson> getPapersOfReviewer(String email) {
        Optional<CommitteeMemberEntity> reviewer = pcMemberRepository.findById(email);
        if(reviewer.isPresent()){
            return (reviewer.get().getEvaluations() != null ? reviewer.get().getPapers().values().stream()
                    .map(PaperMapper::entityToPaper)
                    .collect(Collectors.toList()) : new ArrayList<PaperJson>());
        }
        else{
            return new ArrayList<PaperJson>();
        }
    }

}
