package com.input;

import com.model.Qualifier;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaperInput {

    private String title;
    private String fileName;
    private String description;
    private String[] authors;
    private List<String> topics;
    private List<String> keywords;
}