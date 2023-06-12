package com.project.hdjunction.service;

import com.project.hdjunction.domain.Patient;
import com.project.hdjunction.dto.patient.*;
import com.project.hdjunction.repository.HospitalRepository;
import com.project.hdjunction.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public String generatePatientNo(int hospital_id) {

        AtomicInteger counter = hospitalCounters.computeIfAbsent(hospital_id, id -> new AtomicInteger(0));
        int hospitalUniqueId = counter.getAndIncrement() % 10000;

        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        return String.format("%s-%04d", date, hospitalUniqueId);

    }


    public List<PatientWithVisitsResponse> selectPatient(int patient_id) {
        return patientRepository.selectPatient(patient_id);
    }

    public PatientListResponse selectListPatient(PatientSearch patientSearch, Pageable pageable) {
        Page<PatientOneResponse> patients = patientRepository.selectListPatient(patientSearch,pageable);

        //페이지블럭 처리
        int nowPage = patients.getPageable().getPageNumber() + 1;
        int startPage =  Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage+9, patients.getTotalPages()==0?1: patients.getTotalPages());

        return new PatientListResponse(patients.getContent(),nowPage,startPage,endPage);
    }
}
