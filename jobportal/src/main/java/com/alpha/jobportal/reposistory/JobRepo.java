package com.alpha.jobportal.reposistory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alpha.jobportal.entity.Job;

public interface JobRepo extends JpaRepository<Job, Integer> {
	

	List<Job> findByRoleContainingIgnoreCase(String searchKey);

	List<Job> findByCompanyId(int compid);
	List<Job> findByCompanyIdAndStatus(int companyId, String status);
	

}
