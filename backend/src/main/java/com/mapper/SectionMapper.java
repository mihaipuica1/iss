package com.mapper;

import com.entities.SectionEntity;
import com.input.SectionInput;
import com.model.SectionJson;

public class SectionMapper {

    public static SectionEntity sectionToEntity(SectionInput section) {
        return SectionEntity.builder()
                .name(section.getName())
                .build();
    }

    public static SectionJson entityToSection(SectionEntity entity) {
        return SectionJson.builder()
                .id(entity.getId())
                .name(entity.getName())
                .eventId(entity.getEvent().getId())
                .supervisorEmail(entity.getSupervisor() != null ? entity.getSupervisor().getEmail() : null)
                .build();
    }
}
