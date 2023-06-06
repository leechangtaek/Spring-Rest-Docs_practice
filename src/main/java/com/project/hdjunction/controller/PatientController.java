package com.project.hdjunction.controller;

import com.project.hdjunction.dto.patient.CreatePatientRequest;
import com.project.hdjunction.dto.patient.CreatePatientResponse;
import com.project.hdjunction.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @PostMapping("/patient")
    public String register(@RequestBody @Valid CreatePatientRequest request){

        return patientService.register(request);
    }

}
