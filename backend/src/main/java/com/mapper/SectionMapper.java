package com.mapper;

import com.model.Section;
import com.entities.SectionEntity;

public class SectionMapper {

    public static SectionEntity sectionToEntity(Section section){
        return SectionEntity.builder()
                .name(section.getName())
                .build();
    }

    public static Section entityToSection(SectionEntity entity){
        return Section.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }
}
