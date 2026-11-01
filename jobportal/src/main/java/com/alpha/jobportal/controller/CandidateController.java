package com.alpha.jobportal.controller;

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
import com.alpha.jobportal.dto.ApplicationHistoryDto;
import com.alpha.jobportal.dto.CandidateProfileDto;
import com.alpha.jobportal.dto.CandidateRegistrationDto;
import com.alpha.jobportal.dto.ResponseStructure;
import com.alpha.jobportal.dto.Searchjobdto;
import com.alpha.jobportal.dto.SkillDto;
import com.alpha.jobportal.entity.Application;
import com.alpha.jobportal.entity.Candidate;
import com.alpha.jobportal.entity.Company;
import com.alpha.jobportal.entity.Skill;
import com.alpha.jobportal.service.CandidateService;

import jakarta.validation.Valid;

@RestController
public class CandidateController {

	@Autowired
	private CandidateService cs;
	
	@PostMapping("/candidate/register")
	public ResponseStructure<Candidate> candidateRegistration( @Valid @RequestBody CandidateRegistrationDto crd){
		return cs.candidateRegisterService(crd);
	}
	
	@GetMapping("/findcandidate")
	public ResponseStructure<Candidate> findCandidateById(@RequestParam int id) {
		
		return cs.findCandidateByIdSer(id);
	}
	
	@DeleteMapping("/candidate/delete")
	public ResponseStructure<Candidate> deleteCandidate(@RequestParam int id) {
		return cs.dltCandidateyById(id);
		
	}
	
	@PostMapping("/candidate/job/find")
	public ResponseStructure<List<Searchjobdto>> findJob(
	        @RequestParam String searchKey) {

	    return cs.findjob(searchKey);
	}
	
	
	@PostMapping("/candidate/job/apply")
	public ResponseStructure<Application> applyJob(
	        @RequestParam int jobId,
	        @RequestParam int candidateId) {

	    return cs.applyJob(jobId, candidateId);
	}
	
	@GetMapping("/candidate/getapplications")
	public ResponseStructure<List<ApplicationDto>> getApplications(
	        @RequestParam int candidateId) {

	    return cs.getApplications(candidateId);
	}
	
	
	@GetMapping("/candidate/application/status")
	public ResponseStructure<ApplicationDto> getApplicationStatus(
	        @RequestParam int candidateId,
	        @RequestParam int applicationId) {

	    return cs.getApplicationStatus(candidateId, applicationId);
	}
	
	
	@PutMapping("/candidate/application/withdraw")
	public ResponseStructure<Application> withdrawApplication(
	        @RequestParam int candidateId,
	        @RequestParam int applicationId) {

	    return cs.withdrawApplication(candidateId, applicationId);
	}
	
	@PutMapping("/candidate/update/{id}")
	public ResponseStructure<Candidate> updateCandidate(
	       @Valid @PathVariable int id,
	        @RequestBody CandidateProfileDto dto) {

	    return cs.updateCandidate(id, dto);
	}
	
	@PostMapping("/candidate/{id}/updateskill")
	public ResponseStructure<Candidate> addSkillToCandidate(
	        @PathVariable int id,
	        @RequestBody SkillDto skillDto) {

	    return cs.addSkillToCandidate(id, skillDto);
	}
	
	@DeleteMapping("/candidate/{id}/skill/{skillid}")
	public ResponseStructure<Candidate> removeCandidateSkill(
	        @PathVariable int id,
	        @PathVariable int skillid) {

	    return cs.removeCandidateSkill(id, skillid);
	}
	
	@GetMapping("/candidate/profile/{id}")
	public ResponseStructure<Candidate> getCandidateProfile(
	        @PathVariable int id) {

	    return cs.getCandidateProfile(id);
	}
	
	@GetMapping("/candidate/{id}/applications/history")
	public ResponseStructure<List<ApplicationHistoryDto>> getApplicationHistory(
	        @PathVariable int id) {

	    return cs.getApplicationHistory(id);
	}
	
	
	@GetMapping("/candidate/{id}/applications/shortlisted")
	public ResponseStructure<List<ApplicationDto>> getShortlistedApplications(
	        @PathVariable int id) {

	    return cs.getShortlistedApplications(id);
	}
	
	@GetMapping("/candidate/{id}/applications/rejected")
	public ResponseStructure<List<ApplicationDto>> getRejectedApplications(
	        @PathVariable int id) {

	    return cs.getRejectedApplications(id);
	}
	
}
