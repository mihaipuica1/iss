package com.entities;

import com.model.Role;
import com.vladmihalcea.hibernate.type.array.EnumArrayType;
import lombok.*;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@TypeDef(
        typeClass = EnumArrayType.class,
        defaultForType = Role[].class,
        parameters = {
                @org.hibernate.annotations.Parameter(name = EnumArrayType.SQL_ARRAY_TYPE, value = "role")
        }
)
@Entity(name = "profile")
public class ApplicationUser {

    @Id
    @Email
    @Column(name = "userName", nullable = false, unique = true)
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "roles", columnDefinition = "roles")
    private Role[] roles;

}
