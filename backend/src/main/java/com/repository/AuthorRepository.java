package com.repository;

import com.entities.AuthorEntity;
import com.entities.UserEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends UserBaseRepository<AuthorEntity> {
}
