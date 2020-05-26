package com.mapper;

import com.entities.EventEntity;
import com.model.Event;

public class EventMapper {

    public static EventEntity eventToEntity(Event event){
        return EventEntity.builder()
                .name(event.getName())
                .build();
    }

    public static Event entityToEvent(EventEntity entity){
        return Event.builder()
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
