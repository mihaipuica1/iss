package com.repository;


import com.entities.UserEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends UserBaseRepository<UserEntity> {
}
