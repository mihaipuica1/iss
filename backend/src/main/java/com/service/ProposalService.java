package com.service;


import com.entities.PaperEntity;
import com.mapper.ProposalMapper;
import com.model.Proposal;
import com.repository.PaperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProposalService {

    private PaperRepository paperRepository;

    @Autowired
    public ProposalService(PaperRepository paperRepository) {
        this.paperRepository = paperRepository;
    }

    @Transactional
    public Proposal addProposal(Proposal proposal) {

        PaperEntity entity = ProposalMapper.proposalToEntity(proposal);
        return ProposalMapper.entityToProposal(paperRepository.save(entity));

    }

    @Transactional
    public Proposal findById(int id) {

        PaperEntity entity = paperRepository.findById(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Proposal with id " + id + " not found"));
        return ProposalMapper.entityToProposal(entity);
    }

    @Transactional
    public List<Proposal> getAll() {
        return paperRepository.findAll().stream().map(ProposalMapper::entityToProposal).collect(Collectors.toList());
    }
}
