package com.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.awt.print.Paper;
import java.util.List;
import java.util.Map;


@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComiteeMember extends Participant {
    private String webSite;
    private List<Paper> papersToReview;
    private Map<Paper, String> reviewedPapers;
    private String section;
}
