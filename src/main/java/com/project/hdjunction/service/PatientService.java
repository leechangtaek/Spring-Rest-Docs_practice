package com.project.hdjunction.service;

import com.project.hdjunction.domain.Hospital;
import com.project.hdjunction.domain.Patient;
import com.project.hdjunction.dto.patient.CreatePatientRequest;
import com.project.hdjunction.dto.patient.EditPatientRequest;
import com.project.hdjunction.dto.patient.PatientResponse;
import com.project.hdjunction.repository.HospitalRepository;
import com.project.hdjunction.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;
    private final HospitalRepository hospitalRepository;
    private static final Map<Integer, AtomicInteger> hospitalCounters = new HashMap<>();

    public int createPatient(Patient patient) {

        // 환자 등록 번호 생성 로직
        String patient_no = generatePatientNo(patient.getHospital().getId());
        patient.setPatient_no(patient_no);

        patientRepository.save(patient);

        return patient.getId();
    }

    public void editPatient(EditPatientRequest request) {

        Patient patient = new Patient();
        patient.setId(request.getPatient_id());
        patient.setPatient_nm(request.getPatient_nm());
        patient.setGender_cd(request.getGender_cd());
        patient.setBirth(request.getBirth());
        patient.setPhone_no(request.getPhone_no());
        patientRepository.save(patient);
    }

    public Patient findById(int patient_id) {
        return patientRepository.findById(patient_id).get();
    }

    public void deletePatient(int patient_id) {
        patientRepository.deleteById(patient_id);
    }

    private String generatePatientNo(int hospital_id) {

        AtomicInteger counter = hospitalCounters.computeIfAbsent(hospital_id, id -> new AtomicInteger(0));
        int hospitalUniqueId = counter.getAndIncrement() % 10000;

        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        return String.format("%s-%04d", date, hospitalUniqueId);

    }


    public List<PatientResponse> selectPatient(int patient_id) {
        return patientRepository.selectPatient(patient_id);
    }
}
