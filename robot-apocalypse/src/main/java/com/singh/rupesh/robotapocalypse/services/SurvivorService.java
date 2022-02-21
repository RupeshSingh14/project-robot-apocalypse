package com.singh.rupesh.robotapocalypse.services;

import com.singh.rupesh.robotapocalypse.models.LastLocation;
import com.singh.rupesh.robotapocalypse.models.Reports;
import com.singh.rupesh.robotapocalypse.models.Survivor;


public interface SurvivorService {

    void addSurvivors(Survivor survivor);
    void updateSurvivorLocation(String id, LastLocation lastLocation);
    void updateSurvivorStatus(String survivorId, String reporterId);
    Reports getReports();
}
