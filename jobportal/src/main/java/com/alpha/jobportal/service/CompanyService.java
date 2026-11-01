package com.alpha.jobportal.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.alpha.jobportal.dto.ApplicationDto;
import com.alpha.jobportal.dto.ApplicationStatusDto;
import com.alpha.jobportal.dto.CandidateRegistrationDto;
import com.alpha.jobportal.dto.CompanyRegistrationDto;
import com.alpha.jobportal.dto.CreateNewJobdto;
import com.alpha.jobportal.dto.GetalljobsDto;
import com.alpha.jobportal.dto.ResponseStructure;
import com.alpha.jobportal.dto.UpdateCompanyDto;
import com.alpha.jobportal.entity.Application;
import com.alpha.jobportal.entity.Candidate;
import com.alpha.jobportal.entity.Company;
import com.alpha.jobportal.entity.Job;
import com.alpha.jobportal.entity.Skill;
import com.alpha.jobportal.exception.CandidateNotFoundException;
import com.alpha.jobportal.exception.CompanyNotFoundException;
import com.alpha.jobportal.exception.Jobnotfoundexception;
import com.alpha.jobportal.reposistory.ApplicationRepo;
import com.alpha.jobportal.reposistory.CandidateRepo;
import com.alpha.jobportal.reposistory.CompanyRepo;
import com.alpha.jobportal.reposistory.JobRepo;
import com.alpha.jobportal.reposistory.SkillRepo;


@Service
public class CompanyService {

		@Autowired
		private CompanyRepo cr;
		
		@Autowired
		private JobRepo jr;
		
		@Autowired
		private ApplicationRepo ar;
		
		@Autowired
		private SkillRepo sr;
	
			public ResponseStructure<Company> companyRegisterService(CompanyRegistrationDto crd) {
		
			Company cc = new Company ();
			cc.setName(crd.getName());
			cc.setPhone(crd.getPhone());
			cc.setType(crd.getType());
			cc.setAddress(crd.getAddress());
			cc.setNoofemps(crd.getNo_of_emps());
//			cc.setmail(crd.getMail());
//			
			
			Company savedcompany = cr.save(cc);
			
			ResponseStructure<Company> rs = new ResponseStructure<Company>();
			rs.setStatuscode(HttpStatus.CREATED.value());
			rs.setData( savedcompany);
			rs.setMessage("Company saved successfully");
			
			return rs;
		
	}

		public ResponseStructure<Company> findCompanybyId(int id) {
			
			Company company =cr.findById(id).orElseThrow(() -> new CompanyNotFoundException());
			ResponseStructure<Company> rs = new ResponseStructure<Company>();
			rs.setStatuscode(HttpStatus.FOUND.value());
			rs.setMessage("Company FOUND");
			rs.setData(company);
			return rs;
		}

		public ResponseStructure<Company> dltCompanyById(int id) {

		    Company company = cr.findById(id)
		            .orElseThrow(() -> new CompanyNotFoundException());

		    cr.delete(company);

		    ResponseStructure<Company> rs = new ResponseStructure<Company>();

		    rs.setStatuscode(HttpStatus.OK.value());
		    rs.setMessage("Company Deleted Successfully");
		    rs.setData(null);

		    return rs;
		}

		public ResponseStructure<List<GetalljobsDto>> getalljobs(int compid) {

		    // Check whether company exists
		    cr.findById(compid)
		      .orElseThrow(() -> new CompanyNotFoundException());

		    // Get all jobs belonging to this company
		    List<Job> jobs = jr.findByCompanyId(compid);

		    // Convert Job objects to DTOs
		    List<GetalljobsDto> jobDtos = new ArrayList<>();

		    for (Job job : jobs) {

		        GetalljobsDto dto = new GetalljobsDto();

		        dto.setId(job.getId());
		        dto.setRole(job.getRole());
		        dto.setReqSkills(job.getReqSkills());
		        dto.setJobDesc(job.getJobDesc());
		        dto.setNoOfPositions(job.getNoOfPositions());
		        dto.setSalary(job.getSalary());
		        dto.setBond(job.getBond());
		        dto.setReqExperiance(job.getReqExperiance());
		        dto.setPostedDate(job.getPostedDate());
		        dto.setLastDate(job.getLastDate());
		        dto.setReqQualification(job.getReqQualification());
		        dto.setStatus(job.getStatus());
		        dto.setCompany(job.getCompany());

		        jobDtos.add(dto);
		    }

		    // Create response
		    ResponseStructure<List<GetalljobsDto>> response =
		            new ResponseStructure<>();

		    response.setStatuscode(200);
		    response.setMessage("Company jobs fetched successfully");
		    response.setData(jobDtos);

		    return response;
		}

		public ResponseStructure<List<ApplicationDto>> getAllApplications(
		        int compid, int jobid) {

		    // 1. Check company exists
		    cr.findById(compid)
		      .orElseThrow(() -> new CompanyNotFoundException());

		    // 2. Check job exists
		    Job job = jr.findById(jobid)
		            .orElseThrow(() -> new Jobnotfoundexception());

		    // 3. Check this job belongs to this company
		    if (compid != job.getCompany().getId()) {
		        throw new RuntimeException("Job does not belong to this company");
		    }

		    // 4. Get all applications for this job
		    List<Application> applications = ar.findByJobId(jobid);

		    // 5. Convert Application to ApplicationDto
		    List<ApplicationDto> applicationDtos = new ArrayList<>();

		    for (Application application : applications) {

		        ApplicationDto dto = new ApplicationDto();

		        dto.setId(application.getId());

		        // Candidate details
		        dto.setCandidate(application.getCandidate());

		        // Application status
		        dto.setStatus(application.getStatus());

		        // Application date
		        dto.setApplication_date(application.getApplication_date());

		        applicationDtos.add(dto);
		    }

		    // 6. Create response
		    ResponseStructure<List<ApplicationDto>> response =
		            new ResponseStructure<>();

		    response.setStatuscode(200);
		    response.setMessage("Applications fetched successfully");
		    response.setData(applicationDtos);

		    return response;
		}
		
		

		public ResponseStructure<Application> updateApplicationStatus(
		        int companyId, ApplicationStatusDto dto) {

		    Application application = ar.findById(dto.getApplicationId())
		            .orElseThrow(() ->
		                    new RuntimeException("Application not found"));

		    Job job = application.getJob();

		    if (job == null) {
		        throw new RuntimeException("Job not found");
		    }

		    if (job.getCompany() == null
		            || job.getCompany().getId() != companyId) {

		        throw new RuntimeException(
		                "Application does not belong to this company");
		    }

		    if (!"Shortlisted".equals(dto.getStatus())
		            && !"Rejected".equals(dto.getStatus())) {

		        throw new RuntimeException(
		                "Status must be Shortlisted or Rejected");
		    }

		    application.setStatus(dto.getStatus());

		    Application updatedApplication = ar.save(application);

		    ResponseStructure<Application> rs =
		            new ResponseStructure<>();

		    rs.setStatuscode(HttpStatus.OK.value());
		    rs.setMessage("Application status updated successfully");
		    rs.setData(updatedApplication);

		    return rs;
		}

		public ResponseStructure<Company> updateCompany(
		        int id, UpdateCompanyDto dto) {

		    Company existingCompany = cr.findById(id)
		            .orElseThrow(() -> new RuntimeException("Company not found"));

		    existingCompany.setName(dto.getName());
		    existingCompany.setPhone(dto.getPhone());
		    existingCompany.setAddress(dto.getAddress());
		    existingCompany.setType(dto.getType());
		    existingCompany.setNoofemps(dto.getNoofemps());

		    Company updatedCompany = cr.save(existingCompany);

		    ResponseStructure<Company> response = new ResponseStructure<>();

		    response.setStatuscode(200);
		    response.setMessage("Company updated successfully");
		    response.setData(updatedCompany);

		    return response;
		}
		public ResponseStructure<List<Job>> getCompanyJobs(int companyId) {

		     // Check company exists
		    cr.findById(companyId)
		            .orElseThrow(() ->
		                    new RuntimeException("Company not found"));

		    // Get jobs belonging to this company
		    List<Job> jobs = jr.findByCompanyId(companyId);

		    ResponseStructure<List<Job>> response =
		            new ResponseStructure<>();

		    response.setStatuscode(HttpStatus.OK.value());
		    response.setMessage("Company jobs fetched successfully");
		    response.setData(jobs);

		    return response;
		}

		public ResponseStructure<List<Job>> getJobsByStatus(
		        int companyId, String status) {

		    cr.findById(companyId)
		            .orElseThrow(() ->
		                    new RuntimeException("Company not found"));

		    List<Job> jobs =
		            jr.findByCompanyIdAndStatus(companyId, status);

		    ResponseStructure<List<Job>> response =
		            new ResponseStructure<>();

		    response.setStatuscode(HttpStatus.OK.value());
		    response.setMessage("Jobs fetched by status successfully");
		    response.setData(jobs);

		    return response;
		}

		public ResponseStructure<Job> deleteJob(int companyid, int jobid) {

		    Company company = cr.findById(companyid)
		            .orElseThrow(() -> new RuntimeException("Company not found"));

		    Job job = jr.findById(jobid)
		            .orElseThrow(() -> new RuntimeException("Job not found"));

		    if (job.getCompany() == null ||
		            job.getCompany().getId() != company.getId()) {

		        throw new RuntimeException("Job does not belong to this company");
		    }

		    // Find all applications for this job
		    List<Application> applications = ar.findByJobId(jobid);

		    // Delete applications first
		    ar.deleteAll(applications);

		    // Delete the job
		    jr.delete(job);

		    ResponseStructure<Job> response = new ResponseStructure<>();
		    response.setStatuscode(HttpStatus.OK.value());
		    response.setMessage("Job and related applications deleted successfully");
		    response.setData(job);

		    return response;
		}

		public ResponseStructure<Job> updateJob(
		        int companyid,
		        int jobid,
		        CreateNewJobdto dto) {

		    Company company = cr.findById(companyid)
		            .orElseThrow(() -> new RuntimeException("Company not found"));

		    Job job = jr.findById(jobid)
		            .orElseThrow(() -> new RuntimeException("Job not found"));

		    if (job.getCompany() == null ||
		            job.getCompany().getId() != company.getId()) {

		        throw new RuntimeException("Job does not belong to this company");
		    }

		    List<Skill> skills = sr.findAllById(dto.getSkillids());

		    job.setRole(dto.getRole());
		    job.setReqSkills(skills);
		    job.setJobDesc(dto.getJobdescription());
		    job.setNoOfPositions(dto.getNoofpositions());
		    job.setSalary(dto.getSalary());
		    job.setBond(dto.getBond());
		    job.setReqExperiance(dto.getExperience());
		    job.setLastDate(dto.getLastdate());
		    job.setReqQualification(dto.getRequalification());

		    Job updatedJob = jr.save(job);

		    ResponseStructure<Job> response = new ResponseStructure<>();
		    response.setStatuscode(HttpStatus.OK.value());
		    response.setMessage("Job details updated successfully");
		    response.setData(updatedJob);

		    return response;
		}
}
