package com.singh.rupesh.robotapocalypse.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="survivor")
@Entity
@Data
@NoArgsConstructor
public class SurvivorEntity {

    private String name;
    private Integer age;
    private String gender;
    @Id
    private String id;
    private Double latitude;
    private Double longitude;
    private Boolean water;
    private Boolean food;
    private Boolean medication;
    private Boolean ammunition;
    private Boolean infectedState=false;

    public Boolean getInfectedState() {
        return infectedState;
    }
    public void setInfectedState(Boolean infectedState) {
        this.infectedState = infectedState;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public Double getLatitude() {
        return latitude;
    }
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
    public Double getLongitude() {
        return longitude;
    }
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
    public Boolean getWater() {
        return water;
    }
    public void setWater(Boolean water) {
        this.water = water;
    }
    public Boolean getFood() {
        return food;
    }
    public void setFood(Boolean food) {
        this.food = food;
    }
    public Boolean getMedication() {
        return medication;
    }
    public void setMedication(Boolean medication) {
        this.medication = medication;
    }
    public Boolean getAmmunition() {
        return ammunition;
    }
    public void setAmmunition(Boolean ammunition) {
        this.ammunition = ammunition;
    }

}
