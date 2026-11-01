package com.alpha.jobportal.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alpha.jobportal.dto.ApplicationDto;
import com.alpha.jobportal.dto.ApplicationStatusDto;
import com.alpha.jobportal.dto.CompanyRegistrationDto;
import com.alpha.jobportal.dto.CreateNewJobdto;
import com.alpha.jobportal.dto.GetalljobsDto;
import com.alpha.jobportal.dto.ResponseStructure;
import com.alpha.jobportal.dto.UpdateCompanyDto;
import com.alpha.jobportal.entity.Application;
import com.alpha.jobportal.entity.Company;
import com.alpha.jobportal.entity.Job;
import com.alpha.jobportal.service.CompanyService;
import com.alpha.jobportal.service.JobService;

@RestController
public class CompanyController {

    @Autowired
    private CompanyService cs;

    @Autowired
    private JobService js;

    @PostMapping("/company/register")
    public ResponseStructure<Company> companyRegistration(
            @RequestBody CompanyRegistrationDto crdd) {

        return cs.companyRegisterService(crdd);
    }

    @GetMapping("/findcompany")
    public ResponseStructure<Company> findbyId(
            @RequestParam int id) {

        return cs.findCompanybyId(id);
    }

    @DeleteMapping("/company/delete")
    public ResponseStructure<Company> deleteCompany(
            @RequestParam int id) {

        return cs.dltCompanyById(id);
    }

    @PostMapping("/company/createnewjob")
    public ResponseStructure<Job> createnewjob(
            @RequestBody CreateNewJobdto cnj) {

        return js.createNewJob(cnj);
    }
    
    @PutMapping("/company/job/inactive")
    public ResponseStructure<Job> inactiveJob(
            @RequestParam int compid,
            @RequestParam int jobid) {

        return js.inactiveJob(compid, jobid);
    }
    
    @GetMapping("/company/job/repost")
    public ResponseStructure<Job> repostJob(
            @RequestParam int compid,
            @RequestParam int jobid,
            @RequestParam LocalDate lastdate) {

        return js.repostJob(compid, jobid, lastdate);
    }
    
    @GetMapping("company/getalljobs")
    public ResponseStructure<List<GetalljobsDto>> getalljobs(@RequestParam int compid){
    	
    	return cs.getalljobs(compid);
    }
    
    
    @GetMapping("/company/getAllApplications")
    public ResponseStructure<List<ApplicationDto>> getAllApplications(
            @RequestParam int compid,
            @RequestParam int jobid) {

        return cs.getAllApplications(compid, jobid);
    }
    
    @PutMapping("/company/application/status")
    public ResponseStructure<Application> updateApplicationStatus(
            @RequestParam int companyId,
            @RequestBody ApplicationStatusDto dto) {

        return cs.updateApplicationStatus(companyId, dto);
    }
    
    
    @PutMapping("/company/update/{id}")
    public ResponseStructure<Company> updateCompany(
            @PathVariable int id,
            @RequestBody UpdateCompanyDto dto) {

        return cs.updateCompany(id, dto);
    }
    
    @GetMapping("/company/{companyId}/jobs")
    public ResponseStructure<List<Job>> getCompanyJobs(
            @PathVariable int companyId) {

        return cs.getCompanyJobs(companyId);
    }
    
    
    @GetMapping(value = "/company/{id}/jobs", params = "status")
    public ResponseStructure<List<Job>> getJobsByStatus(
            @PathVariable int id,
            @RequestParam String status) {

        return cs.getJobsByStatus(id, status);
    }
    
    @DeleteMapping("/company/{companyid}/job/{jobid}")
    public ResponseStructure<Job> deleteJob(
            @PathVariable int companyid,
            @PathVariable int jobid) {

        return cs.deleteJob(companyid, jobid);
    }  
    
    @PutMapping("/company/{companyid}/job/{jobid}")
    public ResponseStructure<Job> updateJob(
            @PathVariable int companyid,
            @PathVariable int jobid,
            @RequestBody CreateNewJobdto dto) {

        return cs.updateJob(companyid, jobid, dto);
    }
    
    
    @PutMapping("/company/{companyid}/job/{jobid}/inactivate")
    public ResponseStructure<Job> inactivateJob(
            @PathVariable int companyid,
            @PathVariable int jobid) {

        return js.inactivateJob(companyid, jobid);
    }
    
    @PutMapping("/company/{companyid}/job/{jobid}/activate")
    public ResponseStructure<Job> activateJob(
            @PathVariable int companyid,
            @PathVariable int jobid) {

        return js.activateJob(companyid, jobid);
    }
    
    @PutMapping("/company/{companyid}/job/{jobid}/deadline")
    public ResponseStructure<Job> changeApplicationDeadline(
            @PathVariable int companyid,
            @PathVariable int jobid,
            @RequestParam LocalDate lastdatetoapply) {

        return js.changeApplicationDeadline(
                companyid, jobid, lastdatetoapply);
    }
    
    
    
}