package com.example.demo.repository;

import com.example.demo.entity.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TeamRepository extends JpaRepository<TeamEntity, Long> {

    @Query("Select t From TeamEntity t WHERE t.name ILIKE %:name%")
    List<TeamEntity> findByName(@Param("name")String name);

    boolean existsByName(String name);
}
