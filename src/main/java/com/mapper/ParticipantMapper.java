package com.mapper;

import com.entities.ParticipantEntity;
import com.model.Participant;


public class ParticipantMapper {

    public static ParticipantEntity participantToEntity(Participant participant)
    {
        return ParticipantEntity.builder()
                .name(participant.getName())
                .email(participant.getEmail())
                .userName(participant.getUserName())
                .password(participant.getPassword())
                .isAuthenticated(participant.getIsAuthenticated())
                .build();
    }

    public static Participant entityToParticipant(ParticipantEntity entity)
    {
        return Participant.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .userName(entity.getUserName())
                .password(entity.getPassword())
                .isAuthenticated(entity.getIsAuthenticated())
                .build();
    }
}
