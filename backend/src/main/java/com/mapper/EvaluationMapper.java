package com.mapper;

import com.entities.EvaluationEntity;
import com.input.EvaluationInput;
import com.model.EvaluationJson;

public class EvaluationMapper {



    public static EvaluationJson entityToEvaluation(EvaluationEntity entity) {
        return EvaluationJson.builder()
                .qualifier(entity.getQualifier())
                .recommendation(entity.getRecommendation().getText())
                .reviewer(entity.getReviewer().getEmail())
                .paperId(entity.getPaper().getId())
                .build();
    }
}
