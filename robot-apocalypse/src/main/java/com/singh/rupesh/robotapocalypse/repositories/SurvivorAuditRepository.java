package com.singh.rupesh.robotapocalypse.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.singh.rupesh.robotapocalypse.models.SurvivorAuditEntity;

@Repository
public interface SurvivorAuditRepository extends CrudRepository<SurvivorAuditEntity, Integer> {

    List<SurvivorAuditEntity> findBysurvivorIdContaining(String survivorId);

}
