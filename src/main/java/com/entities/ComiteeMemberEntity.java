package com.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "comiteemember")
public class ComiteeMemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "username")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "isauthenticated")
    private Boolean isAuthenticated;

    @Column(name = "website")
    private String webSite;

    //@OneToMany(mappedBy = "paper")
    //private List<PaperEntity> papersToReview;

    //todo: how?
    //@OneToMany(mappedBy = "paper")
    //private Map<PaperEntity, String> reviewedPapers;

    @Column(name = "section")
    private String section;
}
