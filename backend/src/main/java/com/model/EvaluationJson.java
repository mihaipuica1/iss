package com.model;


import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EvaluationJson {

    private int id;
    private Qualifier qualifier;
    private String reviewer;
    private String recommendation;
    private int paperId;

}


