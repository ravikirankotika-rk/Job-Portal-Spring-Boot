package com.alpha.jobportal.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.alpha.jobportal.dto.CreateNewJobdto;
import com.alpha.jobportal.dto.ResponseStructure;
import com.alpha.jobportal.entity.Company;
import com.alpha.jobportal.entity.Job;
import com.alpha.jobportal.entity.Skill;
import com.alpha.jobportal.reposistory.CompanyRepo;
import com.alpha.jobportal.reposistory.JobRepo;
import com.alpha.jobportal.reposistory.SkillRepo;

@Service
public class JobService {

    @Autowired
    private JobRepo jobrepo;

    @Autowired
    private CompanyRepo companyrepo;

    @Autowired
    private SkillRepo skillrepo;

    public ResponseStructure<Job> createNewJob(
            CreateNewJobdto createnewjobdto) {

        
        Company company = companyrepo
                .findById(createnewjobdto.getCompanyid())
                .orElseThrow(() ->
                        new RuntimeException("Company not found"));

        List<Skill> skills = skillrepo
                .findAllById(createnewjobdto.getSkillids());

        Job job = new Job();

        job.setRole(createnewjobdto.getRole());

        job.setReqSkills(skills);

        job.setJobDesc(
                createnewjobdto.getJobdescription());

        job.setNoOfPositions(
                createnewjobdto.getNoofpositions());

        job.setNoOfPositions(
                createnewjobdto.getNoofpositions());

        job.setSalary(
                createnewjobdto.getSalary());

        job.setBond(
                createnewjobdto.getBond());

        job.setReqExperiance(
                createnewjobdto.getExperience());

        job.setLastDate(
                createnewjobdto.getLastdate());

        job.setReqQualification(
                createnewjobdto.getRequalification());

        job.setStatus("Active");

        job.setCompany(company);

        Job savedJob = jobrepo.save(job);

              ResponseStructure<Job> rs =
                new ResponseStructure<>();

        rs.setStatuscode(
                HttpStatus.CREATED.value());

        rs.setData(savedJob);

        rs.setMessage(
                "New Job created successfully");

        return rs;
    }

    public ResponseStructure<Job> inactiveJob(int compid, int jobid) {

                Company company = companyrepo.findById(compid)
                .orElseThrow(() ->
                        new RuntimeException("Company not found"));

               Job job = jobrepo.findById(jobid)
                .orElseThrow(() ->
                        new RuntimeException("Job not found"));

               if (job.getCompany().getId() != company.getId()) {
            throw new RuntimeException(
                    "Job does not belong to this company");
        }

                if (!job.getStatus().equalsIgnoreCase("Active")) {
            throw new RuntimeException(
                    "Job is already inactive");
        }

                job.setStatus("Inactive");

              Job updatedJob = jobrepo.save(job);

              ResponseStructure<Job> rs =
                new ResponseStructure<>();

        rs.setStatuscode(HttpStatus.OK.value());
        rs.setMessage("Job changed to Inactive successfully");
        rs.setData(updatedJob);

        return rs;
    }
    
    public ResponseStructure<Job> repostJob(
            int compid, int jobid, LocalDate lastdate) {

               Company company = companyrepo.findById(compid)
                .orElseThrow(() ->
                        new RuntimeException("Company not found"));

             Job oldjob = jobrepo.findById(jobid)
                .orElseThrow(() ->
                        new RuntimeException("Job not found"));

                if (oldjob.getCompany().getId() != company.getId()) {
            throw new RuntimeException(
                    "Job does not belong to this company");
        }

                if (!oldjob.getStatus().equalsIgnoreCase("Inactive")) {
            throw new RuntimeException(
                    "Only inactive jobs can be reposted");
        }

                Job newjob = new Job();

              newjob.setRole(oldjob.getRole());

               newjob.setReqSkills(
                new ArrayList<>(oldjob.getReqSkills())
        );

        newjob.setJobDesc(oldjob.getJobDesc());
        newjob.setNoOfPositions(oldjob.getNoOfPositions());
        newjob.setSalary(oldjob.getSalary());
        newjob.setBond(oldjob.getBond());
        newjob.setReqExperiance(oldjob.getReqExperiance());
        newjob.setReqQualification(oldjob.getReqQualification());

               newjob.setLastDate(lastdate);
        newjob.setStatus("Active");

           newjob.setCompany(company);

             Job savedJob = jobrepo.save(newjob);

               ResponseStructure<Job> rs =
                new ResponseStructure<>();

        rs.setStatuscode(HttpStatus.CREATED.value());
        rs.setMessage("Job reposted successfully");
        rs.setData(savedJob);

        return rs;
    }

    public ResponseStructure<Job> inactivateJob(
            int companyid, int jobid) {

        Company company = companyrepo.findById(companyid)
                .orElseThrow(() ->
                        new RuntimeException("Company not found"));

        Job job = jobrepo.findById(jobid)
                .orElseThrow(() ->
                        new RuntimeException("Job not found"));

        if (job.getCompany() == null ||
                job.getCompany().getId() != company.getId()) {

            throw new RuntimeException(
                    "Job does not belong to this company");
        }

        if (!"Active".equalsIgnoreCase(job.getStatus())) {

            throw new RuntimeException(
                    "Only active jobs can be inactivated");
        }

        job.setStatus("Inactive");

        Job updatedJob = jobrepo.save(job);

        ResponseStructure<Job> response =
                new ResponseStructure<>();

        response.setStatuscode(HttpStatus.OK.value());
        response.setMessage("Job inactivated successfully");
        response.setData(updatedJob);

        return response;
    }

    public ResponseStructure<Job> activateJob(
            int companyid, int jobid) {

        Company company = companyrepo.findById(companyid)
                .orElseThrow(() ->
                        new RuntimeException("Company not found"));

        Job job = jobrepo.findById(jobid)
                .orElseThrow(() ->
                        new RuntimeException("Job not found"));

        if (job.getCompany() == null ||
                job.getCompany().getId() != company.getId()) {

            throw new RuntimeException(
                    "Job does not belong to this company");
        }

        if (!"Inactive".equalsIgnoreCase(job.getStatus())) {

            throw new RuntimeException(
                    "Only inactive jobs can be activated");
        }

        job.setStatus("Active");

        Job updatedJob = jobrepo.save(job);

        ResponseStructure<Job> response =
                new ResponseStructure<>();

        response.setStatuscode(HttpStatus.OK.value());
        response.setMessage("Job activated successfully");
        response.setData(updatedJob);

        return response;
    }

    public ResponseStructure<Job> changeApplicationDeadline(
            int companyid,
            int jobid,
            LocalDate lastdatetoapply) {

        Company company = companyrepo.findById(companyid)
                .orElseThrow(() ->
                        new RuntimeException("Company not found"));

        Job job = jobrepo.findById(jobid)
                .orElseThrow(() ->
                        new RuntimeException("Job not found"));

        if (job.getCompany() == null ||
                job.getCompany().getId() != company.getId()) {

            throw new RuntimeException(
                    "Job does not belong to this company");
        }

        job.setLastDate(lastdatetoapply);

        Job updatedJob = jobrepo.save(job);

        ResponseStructure<Job> response =
                new ResponseStructure<>();

        response.setStatuscode(HttpStatus.OK.value());
        response.setMessage(
                "Application deadline changed successfully");
        response.setData(updatedJob);

        return response;
    }
    }