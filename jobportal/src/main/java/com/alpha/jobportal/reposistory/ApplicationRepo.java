package com.alpha.jobportal.reposistory;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alpha.jobportal.entity.Application;
import com.alpha.jobportal.entity.Company;

public interface ApplicationRepo extends JpaRepository<Application, Integer> {

    boolean existsByCandidateIdAndJobId(int candidateId, int jobId);

    List<Application> findByCandidateId(int candidateId);

    Optional<Application> findByIdAndCandidateId(
            int applicationId, int candidateId);
    

    List<Application> findByJobId(int jobId);
    List<Application> findByCandidateIdAndStatus(int candidateId, String status);

	Optional<Company> findByIdAndJobCompanyId(int applicationId, int companyId);

	Application save(Company application);
    
}