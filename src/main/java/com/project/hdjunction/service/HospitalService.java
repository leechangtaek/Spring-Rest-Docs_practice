package com.project.hdjunction.service;

import com.project.hdjunction.domain.Hospital;
import com.project.hdjunction.repository.HospitalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HospitalService {
    private final HospitalRepository hospitalRepository;


    public Hospital findById(int hospital_id) {
        return hospitalRepository.findById(hospital_id).get();
    }

}
