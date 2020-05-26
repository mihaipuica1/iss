package com.mapper;

import com.entities.ProgramEntity;
import com.input.ProgramInput;
import com.model.ProgramJson;

public class ProgramMapper {

    public static ProgramEntity programToEntity(ProgramInput program){
        return ProgramEntity.builder()
                .date(program.getDate())
                .interval(program.getInterval())
                .abstractDeadline(program.getAbstractDeadline())
                .proposalDeadline(program.getProposalDeadline())
                .biddingDeadline(program.getBiddingDeadline())
                .build();
    }

    public static ProgramJson entityToProgram(ProgramEntity entity){
        return ProgramJson.builder()
                .id(entity.getId())
                .date(entity.getDate())
                .interval(entity.getInterval())
                .abstractDeadline(entity.getAbstractDeadline())
                .proposalDeadline(entity.getProposalDeadline())
                .biddingDeadline(entity.getBiddingDeadline())
                .build();
    }

    public static ProgramEntity updateProgram(ProgramEntity entity, ProgramInput program){
        entity.setDate(program.getDate());
        entity.setInterval(program.getInterval());
        entity.setAbstractDeadline(program.getAbstractDeadline());
        entity.setProposalDeadline(program.getProposalDeadline());
        entity.setBiddingDeadline(program.getBiddingDeadline());
        return entity;
    }
}
