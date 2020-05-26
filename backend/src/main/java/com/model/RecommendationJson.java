package com.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecommendationJson {

    private int id;
    private String text;

    public RecommendationJson(String text) {
        this.text = text;
    }
}
