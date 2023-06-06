package com.project.hdjunction.repository;

import com.project.hdjunction.domain.Hospital;
import com.project.hdjunction.domain.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital,Integer> {
}
