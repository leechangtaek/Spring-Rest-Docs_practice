package com.project.hdjunction.service;

import com.project.hdjunction.domain.Hospital;
import com.project.hdjunction.domain.Patient;
import com.project.hdjunction.dto.patient.CreatePatientRequest;
import com.project.hdjunction.repository.HospitalRepository;
import com.project.hdjunction.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;
    private final HospitalRepository hospitalRepository;
    private static final Map<Integer, AtomicInteger> hospitalCounters = new HashMap<>();

    public String register(CreatePatientRequest request) {
        Hospital hospital = hospitalRepository.findById(request.getHospital_id()).get();

        Patient patient = new Patient();
        patient.setHospital(hospital);

        // 환자 등록 번호 생성 로직
        String patient_no = generatePatientNo(request.getHospital_id());
        patient.setPatient_no(patient_no);

        patient.setPatient_nm(request.getPatient_nm());
        patient.setGender_cd(request.getGender_cd());
        patient.setBirth(request.getBirth());
        patient.setPhone_no(request.getPhone_no());

        patientRepository.save(patient);

        return patient_no;
    }

    private String generatePatientNo(int hospital_id) {

        AtomicInteger counter = hospitalCounters.computeIfAbsent(hospital_id, id -> new AtomicInteger(0));
        int hospitalUniqueId = counter.getAndIncrement() % 10000;

        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        return String.format("%s-%04d", date, hospitalUniqueId);

    }
}
