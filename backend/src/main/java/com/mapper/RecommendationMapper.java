package com.mapper;

import com.entities.RecommendationEntity;
import com.model.RecommendationJson;

public class RecommendationMapper {

    public static RecommendationEntity recommendationToEntity(RecommendationJson recommendation) {
        return RecommendationEntity.builder()
                .text(recommendation.getText())
                .build();
    }

    public static RecommendationJson entityToRecommendation(RecommendationEntity entity) {
        return RecommendationJson.builder()
                .id(entity.getId())
                .text(entity.getText())
                .build();
    }
}
