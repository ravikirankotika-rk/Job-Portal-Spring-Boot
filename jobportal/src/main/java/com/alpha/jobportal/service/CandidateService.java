package com.alpha.jobportal.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.alpha.jobportal.dto.ApplicationDto;
import com.alpha.jobportal.dto.ApplicationHistoryDto;
import com.alpha.jobportal.dto.CandidateProfileDto;
import com.alpha.jobportal.dto.CandidateRegistrationDto;
import com.alpha.jobportal.dto.ResponseStructure;
import com.alpha.jobportal.dto.Searchjobdto;
import com.alpha.jobportal.dto.SkillDto;
import com.alpha.jobportal.entity.Application;
import com.alpha.jobportal.entity.Candidate;
import com.alpha.jobportal.entity.Education;
import com.alpha.jobportal.entity.Experience;
import com.alpha.jobportal.entity.Job;
import com.alpha.jobportal.entity.Skill;
import com.alpha.jobportal.exception.CandidateNotFoundException;
import com.alpha.jobportal.reposistory.ApplicationRepo;
import com.alpha.jobportal.reposistory.CandidateRepo;
import com.alpha.jobportal.reposistory.JobRepo;

@Service
public class CandidateService {

    @Autowired
    private CandidateRepo cs;

    @Autowired
    private JobRepo jr;
    
    @Autowired
    private ApplicationRepo ar;

    public ResponseStructure<Candidate> candidateRegisterService(CandidateRegistrationDto crd) {

        Candidate c = new Candidate();

        c.setName(crd.getName());
        c.setPhone(crd.getPhone());
        c.setEmail(crd.getMail());
        c.setAge(crd.getAge());
        c.setGender(crd.getGender());

        Candidate savedcandidate = cs.save(c);

        ResponseStructure<Candidate> rs = new ResponseStructure<Candidate>();

        rs.setStatuscode(HttpStatus.CREATED.value());
        rs.setData(savedcandidate);
        rs.setMessage("Candidate saved successfully");

        return rs;
    }

    public ResponseStructure<Candidate> findCandidateByIdSer(int id) {

        Candidate candidate = cs.findById(id)
                .orElseThrow(() -> new CandidateNotFoundException());

        ResponseStructure<Candidate> rs = new ResponseStructure<Candidate>();

        rs.setStatuscode(HttpStatus.FOUND.value());
        rs.setMessage("Candidate FOUND");
        rs.setData(candidate);

        return rs;
    }

    public ResponseStructure<Candidate> dltCandidateyById(int id) {

        Candidate candidate = cs.findById(id)
                .orElseThrow(() -> new CandidateNotFoundException());

        cs.delete(candidate);

        ResponseStructure<Candidate> rs = new ResponseStructure<Candidate>();

        rs.setStatuscode(HttpStatus.OK.value());
        rs.setMessage("Candidate Deleted Successfully");
        rs.setData(null);

        return rs;
    }

    public ResponseStructure<List<Searchjobdto>> findjob(String searchKey) {

        List<Job> jobs = jr.findByRoleContainingIgnoreCase(searchKey);

        List<Searchjobdto> searchJobs = new ArrayList<>();

        for (Job job : jobs) {

            Searchjobdto dto = new Searchjobdto();

            dto.setId(job.getId());
            dto.setJob(job.getJobDesc());
            dto.setRole(job.getRole());
            dto.setSkills(job.getReqSkills());
            dto.setSalary(job.getSalary());
            dto.setBond(job.getBond());
            dto.setReqExperiance(job.getReqExperiance());
            dto.setNoOfPositions(job.getNoOfPositions());
            dto.setPostedDate(job.getPostedDate());
            dto.setLastDate(job.getLastDate());
            dto.setReqQualification(job.getReqQualification());
            dto.setStatus(job.getStatus());

            searchJobs.add(dto);
        }

        ResponseStructure<List<Searchjobdto>> response = new ResponseStructure<>();

        response.setStatuscode(HttpStatus.OK.value());
        response.setMessage("Jobs found successfully");
        response.setData(searchJobs);

        return response;
    }

    public ResponseStructure<Application> applyJob(int jobId, int candidateId) {

        Candidate candidate = cs.findById(candidateId)
                .orElseThrow(() ->
                        new RuntimeException("Candidate not found"));

        Job job = jr.findById(jobId)
                .orElseThrow(() ->
                        new RuntimeException("Job not found"));

        if (!job.getStatus().equalsIgnoreCase("Active")) {
            throw new RuntimeException("Job is not active");
        }

        if (job.getLastDate().isBefore(LocalDate.now())) {
            throw new RuntimeException("Job application last date is over");
        }

        if (ar.existsByCandidateIdAndJobId(candidateId, jobId)) {
            throw new RuntimeException("Candidate has already applied for this job");
        }

        Application application = new Application();

        application.setCandidate(candidate);
        application.setJob(job);
        application.setStatus("Applied");

        Application savedApplication =
                ar.save(application);

        ResponseStructure<Application> response =
                new ResponseStructure<>();

        response.setStatuscode(200);
        response.setMessage("Job applied successfully");
        response.setData(savedApplication);

        return response;
    }

    public ResponseStructure<List<ApplicationDto>> getApplications(int candidateId) {

        // 1. Find candidate by ID
        Candidate candidate = cs.findById(candidateId)
                .orElseThrow(() -> new CandidateNotFoundException());

        // 2. Candidate exists, so get applications
        List<Application> applications = ar.findByCandidateId(candidateId);

        // 3. Create DTO list
        List<ApplicationDto> applicationList = new ArrayList<>();

        // 4. Convert Application to ApplicationDto
        for (Application application : applications) {

            ApplicationDto dto = new ApplicationDto();

            dto.setId(application.getId());
            dto.setCandidate(application.getCandidate());
            dto.setStatus(application.getStatus());
            dto.setApplication_date(application.getApplication_date());

            applicationList.add(dto);
        }

        // 5. Prepare response
        ResponseStructure<List<ApplicationDto>> response =
                new ResponseStructure<>();

        response.setStatuscode(HttpStatus.OK.value());
        response.setMessage("Applications found successfully");
        response.setData(applicationList);

        return response;
    }

    public ResponseStructure<ApplicationDto> getApplicationStatus(
            int candidateId, int applicationId) {

        // 1. Check candidate exists
        Candidate candidate = cs.findById(candidateId)
                .orElseThrow(() -> new CandidateNotFoundException());

        // 2. Find application belonging to this candidate
        Application application = ar
                .findByIdAndCandidateId(applicationId, candidateId)
                .orElseThrow(() ->
                        new RuntimeException("Application not found for this candidate"));

        // 3. Create DTO
        ApplicationDto dto = new ApplicationDto();

      
        dto.setStatus(application.getStatus());

        // 4. Prepare response
        ResponseStructure<ApplicationDto> response =
                new ResponseStructure<>();

        response.setStatuscode(HttpStatus.OK.value());
        response.setMessage("Application status found successfully");
        response.setData(dto);

        return response;
    }

    public ResponseStructure<Application> withdrawApplication(
            int candidateId, int applicationId) {

        // 1. Find application by application ID and candidate ID
        Application application = ar
                .findByIdAndCandidateId(applicationId, candidateId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Application not found for this candidate"));

        // 2. Get the job from application
        Job job = application.getJob();

        // 3. Change application status
        application.setStatus("Withdrawn");

        // 4. Remove application from job
        if (job != null && job.getApplication() != null) {
            job.getApplication().remove(application);
        }

        // 5. Save updated job
        if (job != null) {
            jr.save(job);
        }

        // 6. Save updated application
        Application savedApplication = ar.save(application);

        // 7. Create response
        ResponseStructure<Application> response =
                new ResponseStructure<>();

        response.setStatuscode(HttpStatus.OK.value());
        response.setMessage("Application withdrawn successfully");
        response.setData(savedApplication);

        return response;
    }

    public ResponseStructure<Candidate> updateCandidate(
            int id, CandidateProfileDto dto) {

        Candidate candidate = cs.findById(id)
                .orElseThrow(() -> new CandidateNotFoundException());


        // =========================
        // BASIC CANDIDATE DETAILS
        // =========================

        candidate.setName(dto.getName());
        candidate.setEmail(dto.getMail());
        candidate.setPhone(dto.getPhone());
        candidate.setAge(dto.getAge());
        candidate.setGender(dto.getGender());


        // =========================
        // EDUCATION
        // =========================

        if (dto.getEducation() != null) {

            if (candidate.getEducation() == null) {
                candidate.setEducation(new ArrayList<>());
            }

            for (Education newEducation : dto.getEducation()) {

                if (newEducation.getId() == 0) {

                    // New education
                    candidate.getEducation().add(newEducation);

                } else {

                    // Existing education
                    for (Education existingEducation
                            : candidate.getEducation()) {

                        if (existingEducation.getId()
                                == newEducation.getId()) {

                            existingEducation.setQualification(
                                    newEducation.getQualification());

                            existingEducation.setSpecilization(
                                    newEducation.getSpecilization());

                            existingEducation.setPercentage(
                                    newEducation.getPercentage());

                            existingEducation.setYop(
                                    newEducation.getYop());

                            existingEducation.setUniversity(
                                    newEducation.getUniversity());

                            break;
                        }
                    }
                }
            }
        }


        // =========================
        // EXPERIENCE
        // =========================

        if (dto.getExperience() != null) {

            if (candidate.getExperience() == null) {
                candidate.setExperience(new ArrayList<>());
            }

            for (Experience newExperience : dto.getExperience()) {

                if (newExperience.getId() == 0) {

                    // New experience
                    candidate.getExperience().add(newExperience);

                } else {

                    // Existing experience
                    for (Experience existingExperience
                            : candidate.getExperience()) {

                        if (existingExperience.getId()
                                == newExperience.getId()) {

                            existingExperience.setNo_ofemps(
                                    newExperience.getNo_ofemps());

                            existingExperience.setRole(
                                    newExperience.getRole());

                            existingExperience.setCompanyname(
                                    newExperience.getCompanyname());

                            existingExperience.setStartdate(
                                    newExperience.getStartdate());

                            existingExperience.setEnddate(
                                    newExperience.getEnddate());

                            existingExperience.setCurrently_working(
                                    newExperience.getCurrently_working());

                            existingExperience.setDescription(
                                    newExperience.getDescription());

                            existingExperience.setSkill(
                                    newExperience.getSkill());

                            break;
                        }
                    }
                }
            }
        }


        // =========================
        // SKILL
        // =========================

        if (dto.getSkill() != null) {

            if (candidate.getSkill() == null) {
                candidate.setSkill(new ArrayList<>());
            }

            for (Skill newSkill : dto.getSkill()) {

                if (newSkill.getId() == 0) {

                    // New skill
                    candidate.getSkill().add(newSkill);

                } else {

                    // Existing skill
                    for (Skill existingSkill
                            : candidate.getSkill()) {

                        if (existingSkill.getId()
                                == newSkill.getId()) {

                            existingSkill.setSkill_name(
                                    newSkill.getSkill_name());

                            break;
                        }
                    }
                }
            }
        }


        // =========================
        // SAVE
        // =========================

        Candidate updatedCandidate = cs.save(candidate);


        // =========================
        // RESPONSE
        // =========================

        ResponseStructure<Candidate> rs =
                new ResponseStructure<>();

        rs.setStatuscode(HttpStatus.OK.value());

        rs.setMessage(
                "Candidate profile updated successfully");

        rs.setData(updatedCandidate);

        return rs;
    }


    public ResponseStructure<Candidate> addSkillToCandidate(
            int id, SkillDto skillDto) {

        Candidate candidate = cs.findById(id)
                .orElseThrow(() -> new CandidateNotFoundException());

        Skill skill = new Skill();
        skill.setSkill_name(skillDto.getSkill_name());

        if (candidate.getSkill() == null) {
            candidate.setSkill(new ArrayList<>());
        }

        candidate.getSkill().add(skill);

        Candidate updatedCandidate = cs.save(candidate);

        ResponseStructure<Candidate> rs = new ResponseStructure<>();
        rs.setStatuscode(HttpStatus.OK.value());
        rs.setMessage("Skill added to candidate successfully");
        rs.setData(updatedCandidate);

        return rs;
    }

    public ResponseStructure<Candidate> removeCandidateSkill(
            int id, int skillid) {

        Candidate candidate = cs.findById(id)
                .orElseThrow(() -> new CandidateNotFoundException());

        if (candidate.getSkill() == null) {
            throw new RuntimeException("Candidate has no skills");
        }

        Skill skillToRemove = null;

        for (Skill skill : candidate.getSkill()) {

            if (skill.getId() == skillid) {
                skillToRemove = skill;
                break;
            }
        }

        if (skillToRemove == null) {
            throw new RuntimeException("Skill not found for this candidate");
        }

        candidate.getSkill().remove(skillToRemove);

        Candidate updatedCandidate = cs.save(candidate);

        ResponseStructure<Candidate> rs = new ResponseStructure<>();
        rs.setStatuscode(HttpStatus.OK.value());
        rs.setMessage("Skill removed from candidate successfully");
        rs.setData(updatedCandidate);

        return rs;
    }

    public ResponseStructure<Candidate> getCandidateProfile(int id) {

        Candidate candidate = cs.findById(id)
                .orElseThrow(() -> new CandidateNotFoundException());

        ResponseStructure<Candidate> rs = new ResponseStructure<>();
        rs.setStatuscode(HttpStatus.OK.value());
        rs.setMessage("Candidate profile fetched successfully");
        rs.setData(candidate);

        return rs;
    }

    public ResponseStructure<List<ApplicationHistoryDto>> getApplicationHistory(int id) {

        Candidate candidate = cs.findById(id)
                .orElseThrow(() -> new CandidateNotFoundException());

        List<Application> applicationList = ar.findByCandidateId(id);

        List<ApplicationHistoryDto> dtoList = new ArrayList<>();

        for (Application application : applicationList) {

            ApplicationHistoryDto dto = new ApplicationHistoryDto();

            dto.setId(application.getId());
            dto.setStatus(application.getStatus());
            dto.setApplication_date(application.getApplication_date());

            dtoList.add(dto);
        }

        ResponseStructure<List<ApplicationHistoryDto>> rs = new ResponseStructure<>();

        rs.setStatuscode(HttpStatus.OK.value());
        rs.setMessage("Candidate application history fetched successfully");
        rs.setData(dtoList);

        return rs;
    }
    public ResponseStructure<List<ApplicationDto>> getShortlistedApplications(int id) {

        Candidate candidate = cs.findById(id)
                .orElseThrow(() -> new CandidateNotFoundException());

        List<Application> applications =
                ar.findByCandidateIdAndStatus(id, "Shortlisted");

        List<ApplicationDto> dtoList = new ArrayList<>();

        for (Application application : applications) {

            ApplicationDto dto = new ApplicationDto();

            dto.setId(application.getId());
            dto.setCandidate(application.getCandidate());
            dto.setStatus(application.getStatus());
            dto.setApplication_date(application.getApplication_date());

            dtoList.add(dto);
        }

        ResponseStructure<List<ApplicationDto>> rs =
                new ResponseStructure<>();

        rs.setStatuscode(HttpStatus.OK.value());
        rs.setMessage("Shortlisted applications fetched successfully");
        rs.setData(dtoList);

        return rs;
    }

    public ResponseStructure<List<ApplicationDto>> getRejectedApplications(int id) {

        Candidate candidate = cs.findById(id)
                .orElseThrow(() -> new CandidateNotFoundException());

        List<Application> applications =
                ar.findByCandidateIdAndStatus(id, "Rejected");

        List<ApplicationDto> dtoList = new ArrayList<>();

        for (Application application : applications) {

            ApplicationDto dto = new ApplicationDto();

            dto.setId(application.getId());
            dto.setCandidate(application.getCandidate());
            dto.setStatus(application.getStatus());
            dto.setApplication_date(application.getApplication_date());

            dtoList.add(dto);
        }

        ResponseStructure<List<ApplicationDto>> rs =
                new ResponseStructure<>();

        rs.setStatuscode(HttpStatus.OK.value());
        rs.setMessage("Rejected applications fetched successfully");
        rs.setData(dtoList);

        return rs;
    }
	
	
	
	
	
	}