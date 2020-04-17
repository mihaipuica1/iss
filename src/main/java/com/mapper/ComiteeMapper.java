package com.mapper;

import com.entities.ComiteeMemberEntity;
import com.model.ComiteeMember;

public class ComiteeMapper {

    public static ComiteeMemberEntity comiteeMemberToEntity(ComiteeMember member){
        return ComiteeMemberEntity.builder()
                .name(member.getName())
                .email(member.getEmail())
                .userName(member.getUserName())
                .password(member.getPassword())
                .isAuthenticated(member.getIsAuthenticated())
                .webSite(member.getSection())
                .build();

    }

    public static ComiteeMember entityToComiteeMember(ComiteeMemberEntity entity) {
        return ComiteeMember.builder()
                .id(entity.getId())
                .webSite(entity.getWebSite())
                //.papersToReview(entity.getPapersToReview())
                //.reviewedPapers(entity.getReviewedPapers)
                .section(entity.getSection())
                .build();

    }
}