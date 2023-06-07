package com.project.hdjunction.repository;

import com.project.hdjunction.dto.patient.PatientOneResponse;
import com.project.hdjunction.dto.patient.PatientWithVisitsResponse;
import com.project.hdjunction.dto.patient.PatientSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepositoryCustom{
    List<PatientWithVisitsResponse> selectPatient(int patient_id);
    Page<PatientOneResponse> selectListPatient(PatientSearch patientSearch, Pageable pageable);
}
