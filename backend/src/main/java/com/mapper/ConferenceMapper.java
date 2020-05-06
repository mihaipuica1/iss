package com.mapper;

import com.entities.ConferenceEntity;
import com.model.Conference;

public class ConferenceMapper {

    public static ConferenceEntity conferenceToEntity(Conference conference){
        return ConferenceEntity.builder()
                .title(conference.getTitle())
                .date(conference.getDate())
                .deadline(conference.getDeadline())
                .callForPapers(conference.getCallForPapers())
                .build();
    }

    public static Conference entityToConference(ConferenceEntity entity) {
        return Conference.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .date(entity.getDate())
                .deadline(entity.getDeadline())
                .callForPapers(entity.getCallForPapers())
                .build();
    }
}
