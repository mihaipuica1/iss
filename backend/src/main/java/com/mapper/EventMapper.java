package com.mapper;

import com.entities.EventEntity;
import com.input.EventInput;
import com.model.EventJson;

public class EventMapper {

    public static EventEntity eventToEntity(EventInput event){
        return EventEntity.builder()
                .name(event.getName())
                .build();
    }

    public static EventJson entityToEvent(EventEntity entity){
        return EventJson.builder()
                .id(entity.getId())
                .name(entity.getName())
                //.programCommittee(entity.getProgramCommittee().stream().map(UserEntity::getEmail).collect(Collectors.toList()))
                //.participants(entity.getParticipants().stream().map(UserEntity::getEmail).collect(Collectors.toList()))
                //.speakers(entity.getSpeakers().stream().map(UserEntity::getEmail).collect(Collectors.toList()))
                .program(ProgramMapper.entityToProgram(entity.getProgram()))
                .location(LocationMapper.entityToLocation(entity.getLocation()))
                .build();
    }
}
