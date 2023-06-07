package com.project.hdjunction.controller;

import com.project.hdjunction.domain.Hospital;
import com.project.hdjunction.domain.Patient;
import com.project.hdjunction.dto.patient.CreatePatientRequest;
import com.project.hdjunction.dto.patient.EditPatientRequest;
import com.project.hdjunction.dto.patient.PatientResponse;
import com.project.hdjunction.service.HospitalService;
import com.project.hdjunction.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.NoSuchElementException;


@RestController
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;
    private final HospitalService hospitalService;

    @PostMapping("/patient")
    public ResponseEntity createPatient(@RequestBody @Valid CreatePatientRequest request){
        Hospital hospital = null;
        try{
            hospital = hospitalService.findById(request.getHospital_id());
        }catch(NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Patient patient = new Patient();
        patient.setHospital(hospital);
        patient.setPatient_nm(request.getPatient_nm());
        patient.setGender_cd(request.getGender_cd());
        patient.setBirth(request.getBirth());
        patient.setPhone_no(request.getPhone_no());

        patientService.createPatient(patient);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PutMapping("/patient")
    public ResponseEntity editPatient(@RequestBody @Valid EditPatientRequest request){
        try{
            Patient findPatient = patientService.findById(request.getPatient_id());
        }catch(NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        patientService.editPatient(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/patient/{patientId}")
    public ResponseEntity deletePatient(@PathVariable("patientId") int patient_id){
        try{
            Patient findPatient = patientService.findById(patient_id);
        }catch(NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        patientService.deletePatient(patient_id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<java.util.List<PatientResponse>> selectPatient(@PathVariable("patientId") int patient_id){
        try{
            Patient findPatient = patientService.findById(patient_id);
        }catch(NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(patientService.selectPatient(patient_id), HttpStatus.OK);
    }

}
