package com.example.ResumeGeneratorBackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ResumeGeneratorBackend.dao.*;
import com.example.ResumeGeneratorBackend.dto.ResumeSectionsDTO;
import com.example.ResumeGeneratorBackend.model.SectionObjects.*;
import com.example.ResumeGeneratorBackend.model.ResumeObject;
import com.example.ResumeGeneratorBackend.model.UserAccount;

import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ResumeService {
    @Autowired
    private ResumeRepository resumeRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EducationRepository educationRepository;
    @Autowired
    private LeadershipRepository leadershipRepository;
    @Autowired
    private HeaderRepository headerRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private ExperienceRepository experienceRepository;
    @Autowired
    private TechnicalRepository technicalRepository;

    @Transactional
    public List<ResumeObject> getResumeObjects(String username) throws Exception {
        if(!userRepository.existsByUsername(username)){
            throw new IllegalArgumentException("No user with given username");
        }
        return resumeRepository.findAllByUserUsername(username);
    }

    @Transactional
    public List<SectionObject> getObjectsByTypeAndUsername(Section sectionType, String username) throws Exception {
        if (!userRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("No user with given username");
        }
        switch (sectionType) {
            case EDUCATION:
                return new ArrayList<>(educationRepository.findAllByUserUsername(username));
            case EXPERIENCE:
                return new ArrayList<>(experienceRepository.findAllByUserUsername(username));
            case HEADER:
                return new ArrayList<>(headerRepository.findAllByUserUsername(username));
            case LEADERSHIP:
                return new ArrayList<>(leadershipRepository.findAllByUserUsername(username));
            case PROJECTS:
                return new ArrayList<>(projectRepository.findAllByUserUsername(username));
            case TECH_SKILLS:
                return new ArrayList<>(technicalRepository.findAllByUserUsername(username));
            default:
                throw new IllegalArgumentException("Invalid section type");
        }
    }

    @Transactional
    public String generateLatexFromResumeID(int resumeObjectId) throws Exception{
        if(!resumeRepository.existsById(resumeObjectId)){
            throw new IllegalArgumentException("No resume with given id");
        }
        
        Optional<ResumeObject> resume = resumeRepository.findById(resumeObjectId);
        if(!resume.isPresent()){
            throw new IllegalArgumentException("No resume with given id");
        }
        return resume.get().getLatexString();
    }

    @Transactional
    public void createResumeByUsername(ResumeSectionsDTO resumeSectionsDTO, String username){
        if(!userRepository.existsByUsername(username)){
            throw new IllegalArgumentException("No user with given username");
        }
        UserAccount user = userRepository.findByUsername(username);
        setUserToAllElements(resumeSectionsDTO, user);
        ResumeObject ro = resumeSectionsDTO.convertToResume();
        ro.setUser(user);

        resumeRepository.save(ro);
    }

    @Transactional 
    public void createSectionsByUsername(ResumeSectionsDTO resumeSectionsDTO, String username){
        if(!userRepository.existsByUsername(username)){
            throw new IllegalArgumentException("No user with given username");
        }
        UserAccount user = userRepository.findByUsername(username);
        setUserToAllElements(resumeSectionsDTO, user);
        
        educationRepository.saveAll(resumeSectionsDTO.getEducationExps());
        experienceRepository.saveAll(resumeSectionsDTO.getExperiences());
        projectRepository.saveAll(resumeSectionsDTO.getProjects());
        headerRepository.saveAll(resumeSectionsDTO.getHeader());
        technicalRepository.saveAll(resumeSectionsDTO.getTechnicalSkills());
        leadershipRepository.saveAll(resumeSectionsDTO.getLeadershipExps());
    }


    @Transactional
    public void deleteSectionByIdAndUsername(Section sectionType, int id, String username){
        switch(sectionType){
            case EDUCATION:
                educationRepository.deleteByIdAndUserUsername(id, username);
                break;
            case EXPERIENCE:
                experienceRepository.deleteByIdAndUserUsername(id, username);
                break;
            case HEADER:
                headerRepository.deleteByIdAndUserUsername(id,username);
                break;
            case LEADERSHIP:
                leadershipRepository.deleteByIdAndUserUsername(id,username);
                break;
            case PROJECTS:
                projectRepository.deleteByIdAndUserUsername(id, username);
                break;
            case TECH_SKILLS:
                technicalRepository.deleteByIdAndUserUsername(id, username);
                break;
            default:
                break;
        }
    }


    //helper
    private ResumeSectionsDTO setUserToAllElements(ResumeSectionsDTO resumeSectionsDTO, UserAccount user){
        for (EducationObject ed_obj : resumeSectionsDTO.getEducationExps()) {
            ed_obj.setUser(user);
        }
        for (ExperienceObject exp_obj : resumeSectionsDTO.getExperiences()) {
            exp_obj.setUser(user);
        }
        for (ProjectObject proj_obj : resumeSectionsDTO.getProjects()) {
            proj_obj.setUser(user);
        }
        for (HeaderObject header_obj : resumeSectionsDTO.getHeader()) {
            header_obj.setUser(user);
        }
        for (TechnicalObject tech_obj : resumeSectionsDTO.getTechnicalSkills()) {
            tech_obj.setUser(user);
        }
        for (LeadershipObject lead_obj : resumeSectionsDTO.getLeadershipExps()) {
            lead_obj.setUser(user);
        }
        return resumeSectionsDTO;
    }
}
