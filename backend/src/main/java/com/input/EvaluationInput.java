package com.input;


import com.model.Qualifier;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EvaluationInput {

    private Qualifier qualifier;
    private String reviewer;
    private String recommendation;
    private int paperId;

}


