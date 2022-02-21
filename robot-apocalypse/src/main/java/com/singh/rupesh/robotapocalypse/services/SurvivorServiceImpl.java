package com.singh.rupesh.robotapocalypse.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.singh.rupesh.robotapocalypse.exceptions.NotFoundException;
import com.singh.rupesh.robotapocalypse.exceptions.UnProcessableException;
import com.singh.rupesh.robotapocalypse.models.LastLocation;
import com.singh.rupesh.robotapocalypse.models.Reports;
import com.singh.rupesh.robotapocalypse.models.Resources;
import com.singh.rupesh.robotapocalypse.models.Robot;
import com.singh.rupesh.robotapocalypse.models.Survivor;
import com.singh.rupesh.robotapocalypse.models.SurvivorAuditEntity;
import com.singh.rupesh.robotapocalypse.models.SurvivorEntity;
import com.singh.rupesh.robotapocalypse.repositories.RobotRepository;
import com.singh.rupesh.robotapocalypse.repositories.SurvivorAuditRepository;
import com.singh.rupesh.robotapocalypse.repositories.SurvivorRepository;

@Service
public class SurvivorServiceImpl implements SurvivorService{

    private static final Logger LOG = LoggerFactory.getLogger(SurvivorService.class);
    @Autowired
    private SurvivorRepository survivorRepository;

    @Autowired
    private RobotRepository robotRepository;

    @Autowired
    private SurvivorAuditRepository survivorAuditRepository;

    //check survivor 
    @Override
    public void addSurvivors(Survivor survivor) {
        LOG.info("SurvivorServiceImpl.addSurvivors starts");
        Optional<SurvivorEntity> survivorEntityData = survivorRepository.findById(survivor.getId());
        if(survivorEntityData.isPresent()){
            throw new UnProcessableException("this survivor record already exists");
        }
        SurvivorEntity survivorEntity =survivorEntityMapping(survivor);
        survivorRepository.save(survivorEntity);
        LOG.info("SurvivorServiceImpl.addSurvivors ends");
    }

    @Override
    public void updateSurvivorLocation(String id, LastLocation lastLocation) {
        LOG.info("SurvivorServiceImpl.updateSurvivorLocation starts");
        Optional<SurvivorEntity> survivorEntityData = survivorRepository.findById(id);
        SurvivorEntity survivorEntity = null;
        if(survivorEntityData.isPresent()){
            survivorEntity= survivorEntityData.get();
            survivorEntity.setLatitude(lastLocation.getLatitude());
            survivorEntity.setLongitude(lastLocation.getLongitude());
            survivorEntity= survivorRepository.save(survivorEntity);
        }else {
            throw new NotFoundException("survivor does not exist");
        }
        LOG.info("survivor is updated {}", survivorEntity);
        LOG.info("SurvivorServiceImpl.updateSurvivorLocation ends");
    }

    @Override
    public void updateSurvivorStatus(String survivorId, String reporterId) {
        LOG.info("SurvivorServiceImpl.updateSurvivorStatus starts");
        Optional<SurvivorEntity> survivorEntityData = survivorRepository.findById(survivorId);
        SurvivorEntity survivorEntity = null;
        if(survivorEntityData.isPresent()){
            survivorEntity= survivorEntityData.get();
            if(!survivorEntity.getInfectedState()) {
                LOG.info("Survivor is not infected starts");
                List<SurvivorAuditEntity> saList = survivorAuditRepository.findBysurvivorIdContaining(survivorId);
                if(saList.isEmpty()) {
                    SurvivorAuditEntity survivorAuditEntity=new SurvivorAuditEntity();
                    survivorAuditEntity.setReporterId(reporterId);
                    survivorAuditEntity.setSurvivorId(survivorId);
                    survivorAuditRepository.save(survivorAuditEntity);
                }
                else if(saList.stream().filter(sAuditEntity ->
                        sAuditEntity.getReporterId().equalsIgnoreCase(reporterId)).count()<1) {
                    SurvivorAuditEntity survivorAuditEntity=new SurvivorAuditEntity();
                    survivorAuditEntity.setReporterId(reporterId);
                    survivorAuditEntity.setSurvivorId(survivorId);
                    survivorAuditRepository.save(survivorAuditEntity);

                    List<SurvivorAuditEntity> serviceAuditList = survivorAuditRepository.findBysurvivorIdContaining(survivorId);
                    if(serviceAuditList.size()==3) {
                        survivorEntity.setInfectedState(true);
                        survivorRepository.save(survivorEntity);
                    }
                }else {
                    throw new UnProcessableException("you have already reported the survivor");
                }
            }else {
                throw new UnProcessableException("survivor is already in infected state");
            }
        } else {
            throw new NotFoundException("survivor record does not exists");
        }
        LOG.info("SurvivorServiceImpl.updateSurvivorStatus ends");
    }

    @Override
    public Reports getReports() {
        LOG.info("SurvivorServiceImpl.getReports ends");
        Reports reports = new Reports();
        List<Robot> robots = new ArrayList<>();
        List<SurvivorEntity> survivors = new ArrayList<>();
        robotRepository.findAll().forEach(robots::add);
        survivorRepository.findAll().forEach(survivors::add);
        int survivorsCount=survivors.size();

        List<SurvivorEntity> infectedSurvivors = survivors.stream().filter(p -> p.getInfectedState().equals(true)).collect(Collectors.toList());
        List<SurvivorEntity> nonInfectedSurvivors = survivors.stream().filter(p -> p.getInfectedState().equals(false)).collect(Collectors.toList());

        List<Survivor> infectedSurvivorsList = survivorTransformation(infectedSurvivors);
        List<Survivor> nonInfectedSurvivorsList = survivorTransformation(nonInfectedSurvivors);

        double infectedSurvivorsCount=infectedSurvivors.size();
        double nonInfectedSurvivorsCount=nonInfectedSurvivors.size();

        reports.setInfectedSurvivorsList(infectedSurvivorsList);
        reports.setNonInfectedSurvivorsList(nonInfectedSurvivorsList);
        reports.setInfectedSurvivors((infectedSurvivorsCount/survivorsCount)*100);
        reports.setNonInfectedSurvivors((nonInfectedSurvivorsCount/survivorsCount)*100);
        reports.setRobots(robots);
        LOG.info("SurvivorServiceImpl.getReports ends");
        return reports;
    }

    private SurvivorEntity survivorEntityMapping(Survivor survivor) {
        SurvivorEntity survivorEntity=new SurvivorEntity();
        survivorEntity.setName(survivor.getName());
        survivorEntity.setAge(survivor.getAge());
        survivorEntity.setGender(survivor.getGender());
        survivorEntity.setId(survivor.getId());
        if(survivor.getLastLocation()!=null) {
            survivorEntity.setLatitude(survivor.getLastLocation().getLatitude());
            survivorEntity.setLongitude(survivor.getLastLocation().getLongitude());
        }
        if(survivor.getResources()!=null) {
            survivorEntity.setWater(survivor.getResources().getWater());
            survivorEntity.setFood(survivor.getResources().getFood());
            survivorEntity.setMedication(survivor.getResources().getMedication());
            survivorEntity.setAmmunition(survivor.getResources().getAmmunition());
        }
        return survivorEntity;
    }

    private List<Survivor> survivorTransformation(List<SurvivorEntity> survivorsEntityList) {
        return survivorsEntityList.stream().
                map(is -> {
                    Survivor s = new Survivor();
                    s.setName(is.getName());
                    s.setAge(is.getAge());
                    s.setGender(is.getGender());
                    s.setId(is.getId());
                    LastLocation lastLocation= new LastLocation();
                    lastLocation.setLatitude(is.getLatitude());
                    lastLocation.setLongitude(is.getLongitude());
                    s.setLastLocation(lastLocation);
                    Resources resources= new Resources();
                    resources.setAmmunition(is.getAmmunition());
                    resources.setFood(is.getFood());
                    resources.setMedication(is.getMedication());
                    resources.setWater(is.getWater());
                    s.setResources(resources);
                    return s;
                }).collect(Collectors.toList());
    }
}
