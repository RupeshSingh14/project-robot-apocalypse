package com.singh.rupesh.robotapocalypse.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.singh.rupesh.robotapocalypse.models.LastLocation;
import com.singh.rupesh.robotapocalypse.models.Reports;
import com.singh.rupesh.robotapocalypse.models.Survivor;
import com.singh.rupesh.robotapocalypse.services.SurvivorService;

@RestController
@RequestMapping(value = "/survivors", produces = MediaType.APPLICATION_JSON_VALUE)
public class RobotApocalypseController {

    private static final Logger LOG = LoggerFactory.getLogger(RobotApocalypseController.class);

    @Autowired
    private SurvivorService survivorService;

    @CrossOrigin
    @PostMapping("/addSurvivor")
    @ResponseStatus(HttpStatus.CREATED)
    public void addSurvivors(@RequestBody Survivor survivor)	{
        LOG.info("RobotApocalypseController.registering survivor starts");
        LOG.info("survivor details received:{}",survivor);
        survivorService.addSurvivors(survivor);
        LOG.info("RobotApocalypseController.registering survivor ends");
    }

    @CrossOrigin
    @PutMapping("/updateSurvivor/lastLocation/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateSurvivorLocation(@PathVariable(name = "id") String id,@RequestBody LastLocation lastLocation)	{
        LOG.info("updating survivor last location starts for survivor id:{}",id);
        survivorService.updateSurvivorLocation(id,lastLocation);
        LOG.info("updating survivor last location ends");
    }

    @CrossOrigin
    @PutMapping("/updateSurvivor/infectedStatus")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateSurvivorStatus(@RequestParam(name = "survivorId") String survivorId,@RequestParam(name = "reporterId") String reporterId)	{
        LOG.info("updating survivor infected status starts");
        survivorService.updateSurvivorStatus(survivorId,reporterId);
        LOG.info("updating survivor infected status ends");
    }

    @CrossOrigin
    @GetMapping("/reports")
    @ResponseStatus(HttpStatus.OK)
    public Reports getReports()	{
        LOG.info("getReports starts");
        Reports reports=survivorService.getReports();
        LOG.info("getReports ends");
        return reports;
    }

}
