package com.project.hdjunction.repository;

import com.project.hdjunction.domain.Patient;
import com.project.hdjunction.dto.patient.PatientResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepositoryCustom{
    List<PatientResponse> selectPatient(int patient_id);
}
