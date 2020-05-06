package com.repository;

import com.entities.ComiteeMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComiteeMemberRepository extends JpaRepository<ComiteeMemberEntity, Integer> {
}
