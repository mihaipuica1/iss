package com.service;

import com.entities.*;
import com.mapper.EvaluationMapper;
import com.input.EvaluationInput;
import com.mapper.PaperMapper;
import com.mapper.UserMapper;
import com.model.*;
import com.repository.*;
import com.web.json.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDateTime;
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
    private ApplicationUserRepository appUserRepository;
    private ConferenceRepository conferenceRepository;

    @Autowired
    public ProgramCommitteeService(RecommendationRepository recommendationRepository, PaperRepository paperRepository, UserRepository userRepository, SectionRepository sectionRepository, EvaluationRepository evaluationRepository, PCMemberRepository pcmmemberRepository, BidRepository bidRepository,ApplicationUserRepository appUserRepo,ConferenceRepository cr) {
        this.recommendationRepository = recommendationRepository;
        this.paperRepository = paperRepository;
        this.userRepository = userRepository;
        this.sectionRepository = sectionRepository;
        this.evaluationRepository = evaluationRepository;
        this.pcMemberRepository = pcmmemberRepository;
        this.bidRepository = bidRepository;
        this.appUserRepository = appUserRepo;
        this.conferenceRepository = cr;
    }



    @Transactional
    public PaperJson assignPaperToSection(int paperId, int sectionId) {
        PaperEntity existingPaper = paperRepository.findById(paperId).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Paper with id " + paperId + " not found"));
        existingPaper.setSection(sectionRepository.findById(sectionId).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Section with id " + sectionId + " not found.")));

        paperRepository.save(existingPaper);
        return PaperMapper.entityToPaper(existingPaper);
    }

    @Transactional
    public JsonResponse bidProposal(int paperId, String email, Status status) {  // -> only if user is not already an author or reviewer of this paper
        PaperEntity paperEntity = paperRepository.findById(paperId).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Paper with id " + paperId + " not found!"));
        CommitteeMemberEntity pcMemberEntity = pcMemberRepository.findById(email).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "PC member " + email + " not found"));

        EventEntity event = conferenceRepository.findById(1).get();

        if(event.getProgram().getBiddingDeadline().compareTo(LocalDateTime.now())<0)
            return new JsonResponse().with("status","Cannot bit paper after the date" + event.getProgram().getBiddingDeadline().toString());


        List<CommitteeMemberEntity> pcMemberList = new ArrayList<>(paperEntity.getBids().values());
        if (pcMemberList.size() == 0){
            BidEntity bidEntity = new BidEntity();
            bidEntity.setStatus(status);
            bidRepository.save(bidEntity);

            paperEntity.getBids().put(bidEntity, pcMemberEntity);
            pcMemberEntity.getPapers().values().forEach(paper -> System.out.println(paper.getTitle()));
            paperRepository.save(paperEntity);
            return new JsonResponse().with("response", "OK!");
        }
        for (CommitteeMemberEntity pcMember : pcMemberList)
            if (!(pcMember.getEmail().equals(pcMemberEntity.getEmail()))) {
                BidEntity bidEntity = new BidEntity();
                bidEntity.setStatus(status);
                bidRepository.save(bidEntity);

                paperEntity.getBids().put(bidEntity, pcMemberEntity);
                pcMemberEntity.getPapers().values().forEach(paper -> System.out.println(paper.getTitle()));
                paperRepository.save(paperEntity);
                return new JsonResponse().with("response", "OK!");
            }
        return new JsonResponse().with("response", "Not OK!");
    }


    @Transactional
    public String assignPaper(int paperId, String email) {
        PaperEntity paperEntity = paperRepository.findById(paperId).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Paper with id " + paperId + " not found!"));
        CommitteeMemberEntity committeeMemberEntity = pcMemberRepository.findById(email).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Program committee with email " + email + " not found!"));

        if (paperEntity.getReviews().size() < 4) {

            committeeMemberEntity.addReview(paperEntity);
            return "You are allowed to review this paper.";
        } else throw new RuntimeException("You are not allowed to review this paper.");

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
        List<EvaluationEntity> evalList = evaluationRepository.findAll();
        List<PaperEntity> paperList = new ArrayList<>();
        if (reviewer.isPresent()) {
            for (EvaluationEntity evaluation : evalList) {
                if (evaluation.getReviewer().getEmail().equals(reviewer.get().getEmail()))
                    paperList.add(evaluation.getPaper());
            }
        }

        return paperList.stream().map(PaperMapper::entityToPaper).collect(Collectors.toList());
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
        List<ProgramCommitteeJson> filteredJsons = programCommitteeJsons.stream()
                .filter(programCommitteeJson -> {
                if(programCommitteeJson.getStatus().equals("REJECT"))
                {
                    return false;
                }
                else
                    return true;
                })
                .collect(Collectors.toList());
        return filteredJsons;
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


    public List<User> getReviewers() {



        List<ApplicationUser> reviewers = appUserRepository.findAll().stream()
                .filter(r->{
                    List<RoleEntity> roles = r.getRoles();
                    for(RoleEntity role: roles)
                        if(role.getRole()==Role.PC_MEMBER)
                            return true;

                    return false;
                })
                .collect(Collectors.toList());

        List<CommitteeMemberEntity> comitees = pcMemberRepository.findAll();

        List<User> jsonReviewers = new ArrayList<>();

        for(ApplicationUser appU : reviewers) {
            boolean exists = false;
            for (CommitteeMemberEntity u : comitees) {
                if (appU.getUserName().equals(u.getEmail()))
                    exists = true;
            }
            if (!exists)
                jsonReviewers.add(UserMapper.accountToUser(appU));
        }


        return jsonReviewers;



    }


}
