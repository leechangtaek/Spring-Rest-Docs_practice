package com.project.hdjunction.controller;

import com.project.hdjunction.domain.Patient;
import com.project.hdjunction.dto.patient.CreatePatientRequest;
import com.project.hdjunction.dto.patient.EditPatientRequest;
import com.project.hdjunction.dto.patient.PatientResponse;
import com.project.hdjunction.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @PostMapping("/patient")
    public ResponseEntity createPatient(@RequestBody @Valid CreatePatientRequest request){
        patientService.createPatient(request);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PutMapping("/patient")
    public ResponseEntity editPatient(@RequestBody @Valid EditPatientRequest request){
        Patient findPatient = patientService.findById(request.getPatient_id());
        if(findPatient == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        patientService.editPatient(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/patient")
    public ResponseEntity deletePatient(@RequestParam int patient_id){
        Patient findPatient = patientService.findById(patient_id);
        if(findPatient == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        patientService.deletePatient(patient_id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
