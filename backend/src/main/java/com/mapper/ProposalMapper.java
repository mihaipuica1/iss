package com.mapper;

import com.entities.PaperEntity;
import com.model.Proposal;

public class ProposalMapper {

    public static PaperEntity proposalToEntity(Proposal proposal) {

        return PaperEntity.builder()
                .title(proposal.getTitle())
                .content(proposal.getContent())
                .build();
    }

    public static Proposal entityToProposal(PaperEntity entity) {

        return Proposal.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .build();
    }
}
