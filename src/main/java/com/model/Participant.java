package com.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Participant {
    private int id;
    private String name;
    private String email;
    private String userName;
    private String password;
    private Boolean isAuthenticated;

}
