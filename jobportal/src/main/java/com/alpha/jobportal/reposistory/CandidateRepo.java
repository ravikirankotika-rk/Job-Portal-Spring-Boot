package com.alpha.jobportal.reposistory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alpha.jobportal.entity.Candidate;

public interface CandidateRepo extends JpaRepository<Candidate, Integer>{

}
