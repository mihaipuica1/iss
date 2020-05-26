package com.repository;

import com.entities.ComiteeMemberEntity;
import org.springframework.stereotype.Repository;


@Repository
public interface PCMemberRepository extends UserBaseRepository<ComiteeMemberEntity> {
}
