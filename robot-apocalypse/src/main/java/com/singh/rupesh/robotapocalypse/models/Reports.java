package com.singh.rupesh.robotapocalypse.models;

import java.util.List;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Reports {

    private Double infectedSurvivors;
    private Double nonInfectedSurvivors;
    private List<Survivor> infectedSurvivorsList;
    private List<Survivor> nonInfectedSurvivorsList;
    private List<Robot> robots;

    public Double getInfectedSurvivors() {
        return infectedSurvivors;
    }
    public void setInfectedSurvivors(Double infectedSurvivors) {
        this.infectedSurvivors = infectedSurvivors;
    }
    public Double getNonInfectedSurvivors() {
        return nonInfectedSurvivors;
    }
    public void setNonInfectedSurvivors(Double nonInfectedSurvivors) {
        this.nonInfectedSurvivors = nonInfectedSurvivors;
    }
    public List<Survivor> getInfectedSurvivorsList() {
        return infectedSurvivorsList;
    }
    public void setInfectedSurvivorsList(List<Survivor> infectedSurvivorsList) {
        this.infectedSurvivorsList = infectedSurvivorsList;
    }
    public List<Survivor> getNonInfectedSurvivorsList() {
        return nonInfectedSurvivorsList;
    }
    public void setNonInfectedSurvivorsList(List<Survivor> nonInfectedSurvivorsList) {
        this.nonInfectedSurvivorsList = nonInfectedSurvivorsList;
    }
    public List<Robot> getRobots() {
        return robots;
    }
    public void setRobots(List<Robot> robots) {
        this.robots = robots;
    }
}
