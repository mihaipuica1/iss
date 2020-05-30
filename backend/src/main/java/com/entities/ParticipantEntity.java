package com.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "participant")
@DiscriminatorValue("participant")

public class ParticipantEntity extends UserEntity {

    public ParticipantEntity(@Email String email) {
        super(email);
    }
}