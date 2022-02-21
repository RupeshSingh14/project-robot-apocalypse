package com.singh.rupesh.robotapocalypse.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Survivor {
    private String id;
    private String name;
    private Integer age;
    private String gender;
    private LastLocation lastLocation;
    private Resources resources;
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
    public LastLocation getLastLocation() {
        return lastLocation;
    }
    public void setLastLocation(LastLocation lastLocation) {
        this.lastLocation = lastLocation;
    }
    public Resources getResources() {
        return resources;
    }
    public void setResources(Resources resources) {
        this.resources = resources;
    }

}
