package com.singh.rupesh.robotapocalypse.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.singh.rupesh.robotapocalypse.models.SurvivorEntity;

@Repository
public interface SurvivorRepository extends CrudRepository<SurvivorEntity, String> {

}
