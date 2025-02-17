package com.example.ResumeGeneratorBackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ResumeGeneratorBackend.dto.ResumeSectionsDTO;
import com.example.ResumeGeneratorBackend.model.ResumeObject;
import com.example.ResumeGeneratorBackend.model.SectionObjects.EducationObject;
import com.example.ResumeGeneratorBackend.model.SectionObjects.ExperienceObject;
import com.example.ResumeGeneratorBackend.model.SectionObjects.HeaderObject;
import com.example.ResumeGeneratorBackend.model.SectionObjects.LeadershipObject;
import com.example.ResumeGeneratorBackend.model.SectionObjects.ProjectObject;
import com.example.ResumeGeneratorBackend.model.SectionObjects.Section;
import com.example.ResumeGeneratorBackend.model.SectionObjects.SectionObject;
import com.example.ResumeGeneratorBackend.model.SectionObjects.TechnicalObject;
import com.example.ResumeGeneratorBackend.service.ResumeService;

import java.util.ArrayList;
import java.util.List;


@CrossOrigin(origins="*")

@RestController
public class ResumeController {
    @Autowired
    private ResumeService resumeService;


    @GetMapping("/")
    public ResponseEntity<?> healthCheck(){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/user/{username}/resumes")
    public ResponseEntity<?> findResumesByUsername(@PathVariable String username) throws Exception{
        List<ResumeObject> resumes;
        try{
            resumes = resumeService.getResumeObjects(username);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(resumes, HttpStatus.OK);
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<?> findAllResumeInfo(@PathVariable String username){
        ResumeSectionsDTO fullInfo = new ResumeSectionsDTO();
        try{
            fullInfo.setEducationExps(convertToSpecificType(resumeService.getObjectsByTypeAndUsername(Section.EDUCATION, username), EducationObject.class));
            fullInfo.setExperiences(convertToSpecificType(resumeService.getObjectsByTypeAndUsername(Section.EXPERIENCE, username), ExperienceObject.class));
            fullInfo.setProjects(convertToSpecificType(resumeService.getObjectsByTypeAndUsername(Section.PROJECTS, username), ProjectObject.class));
            fullInfo.setLeadershipExps(convertToSpecificType(resumeService.getObjectsByTypeAndUsername(Section.LEADERSHIP, username), LeadershipObject.class));
            fullInfo.setTechnicalSkills(convertToSpecificType(resumeService.getObjectsByTypeAndUsername(Section.TECH_SKILLS, username), TechnicalObject.class));
            fullInfo.setHeader(convertToSpecificType(resumeService.getObjectsByTypeAndUsername(Section.HEADER, username), HeaderObject.class));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(fullInfo, HttpStatus.OK);
    }

    @PostMapping("/user/{username}/resume")
    public ResponseEntity<?> saveResume(@RequestBody ResumeSectionsDTO sampleResume, @PathVariable String username){
        try{
            resumeService.createResumeByUsername(sampleResume, username);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("user/{username}/resume/section")
    public ResponseEntity<?> saveSection(@RequestBody ResumeSectionsDTO sectionsToSave, @PathVariable String username){
        try{
            resumeService.createSectionsByUsername(sectionsToSave, username);
            System.out.println(sectionsToSave);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("resume/{id}/generate")
    public ResponseEntity<?> generateLatexFromStoredResume(@PathVariable String username,@PathVariable String resumeId){
        int id;
        try{
            id = Integer.parseInt(resumeId);
            String latex = resumeService.generateLatexFromResumeID(id);
            return new ResponseEntity<>(latex, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("resume/generate")
    public ResponseEntity<?> generateLatexFromInput(@RequestBody ResumeSectionsDTO sectionsDTO){
        ResumeObject resume = sectionsDTO.convertToResume();
        return new ResponseEntity<>(resume.getLatexString(),HttpStatus.OK);
    }



    //helper
    private <T extends SectionObject> List<T> convertToSpecificType(List<SectionObject> sectionObjects, Class<T> type) {
        List<T> specificTypeList = new ArrayList<>();
        for (SectionObject sectionObject : sectionObjects) {
            if (type.isInstance(sectionObject)) {
                specificTypeList.add(type.cast(sectionObject));
            }
        }
        return specificTypeList;
    }
}

