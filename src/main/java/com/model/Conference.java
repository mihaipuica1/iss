package com.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Conference {
    private int id;
    private String title;
    private Date date;
    private Date deadline;
    private Boolean callForPapers;
}
