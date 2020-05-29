package com.mapper;

import com.entities.AuthorEntity;
import com.entities.CommitteeMemberEntity;
import com.entities.EvaluationEntity;
import com.entities.PaperEntity;
import com.input.PaperInput;
import com.model.PaperJson;

import java.util.ArrayList;
import java.util.stream.Collectors;


public class PaperMapper {

    public static PaperEntity paperToEntity(PaperInput paper) {

        return PaperEntity.builder()
                .title(paper.getTitle())
                .fileName(paper.getFileName())
                .description(paper.getDescription())
                .keywords(paper.getKeywords())
                .topics(paper.getTopics())
                .build();
    }

    public static PaperJson entityToPaper(PaperEntity entity) {

        return PaperJson.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .fileName(entity.getFileName())
                .authors(entity.getAuthors() != null ? entity.getAuthors().stream().map(AuthorEntity::getEmail).collect(Collectors.toList()) : new ArrayList<>())
                .bidders(entity.getBids() != null ? entity.getBids().values().stream().map(CommitteeMemberEntity::getEmail).collect(Collectors.toList()) : new ArrayList<>())
                .reviewers(entity.getReviews() != null ? entity.getReviews().stream().map(input -> input.getReviewer().getEmail()).collect(Collectors.toList()) : new ArrayList<>())
                .qualifiers(entity.getReviews() != null ? entity.getReviews().stream().map(EvaluationEntity::getQualifier).collect(Collectors.toList()) : new ArrayList<>())
                .topics(entity.getTopics())
                .keywords(entity.getKeywords())
                .build();
    }
}
