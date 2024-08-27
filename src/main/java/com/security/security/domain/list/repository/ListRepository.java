package com.security.security.domain.list.repository;

import com.security.security.domain.list.entity.ListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ListRepository extends JpaRepository<ListEntity, Long> {
}
