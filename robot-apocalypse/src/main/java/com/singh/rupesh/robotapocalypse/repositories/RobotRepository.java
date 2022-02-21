package com.singh.rupesh.robotapocalypse.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.singh.rupesh.robotapocalypse.models.Robot;

@Repository
public interface RobotRepository extends CrudRepository<Robot, String> {

}
