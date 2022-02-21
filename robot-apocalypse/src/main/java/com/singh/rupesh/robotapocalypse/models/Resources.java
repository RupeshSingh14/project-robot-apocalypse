package com.singh.rupesh.robotapocalypse.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@Setter
@Getter
public class Resources {

    private Boolean water;
    private Boolean food;
    private Boolean medication;
    private Boolean ammunition;
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
