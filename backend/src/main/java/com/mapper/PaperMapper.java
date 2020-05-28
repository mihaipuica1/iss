package com.mapper;

import com.entities.CommitteeMemberEntity;
import com.entities.EvaluationEntity;
import com.entities.PaperEntity;
import com.input.PaperInput;
import com.model.PaperJson;

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
                .fileName(entity.getFileName())
                .description(entity.getDescription())
                .bidders(entity.getBids().values().stream().map(CommitteeMemberEntity::getEmail).collect(Collectors.toList()))
                .reviewers(entity.getReviews().stream().map(input -> input.getReviewer().getEmail()).collect(Collectors.toList()))

//                .qualifiers(entity.getQualifiers())
                .authors(entity.getAuthors() != null ? entity.getAuthors().stream()
                        .map(author -> author.getEmail())
                        .collect(Collectors.toList()) : null)
//                .reviewers(entity.getUsers() != null ? entity.getUsers().stream()
//                        .filter(userPaperEntity -> userPaperEntity.getType().equals("reviewer"))
//                        .map(userPaperEntity -> userPaperEntity.getUser().getEmail())
//                        .collect(Collectors.toList()) : null)
//                .bidders(entity.getUsers() != null ? entity.getUsers().stream()
//                        .filter(userPaperEntity -> userPaperEntity.getType().equals("bidder"))
//                        .map(userPaperEntity -> userPaperEntity.getUser().getEmail())
//                        .collect(Collectors.toList()) : null)
                .topics(entity.getTopics())
                .keywords(entity.getKeywords())
                .build();
    }
}
