package com.project.hdjunction;

import com.project.hdjunction.domain.Hospital;
import com.project.hdjunction.domain.Patient;
import com.project.hdjunction.dto.patient.CreatePatientRequest;
import com.project.hdjunction.repository.PatientRepository;
import com.project.hdjunction.service.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@SpringBootTest
public class PatientServiceTest {

    @InjectMocks
    private PatientService patientService;

    @Mock
    private PatientRepository patientRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void 환자등록() {
        CreatePatientRequest request = new CreatePatientRequest();
        Hospital hospital = new Hospital();
        hospital.setId(1);
        hospital.setHospital_nm("안산병원");

        request.setHospital_id(1);
        request.setPatient_nm("John Doe");

        Patient patient = new Patient();
        patient.setHospital(hospital);
        patient.setPatient_no("1-1234-abcd");
        patient.setPatient_nm("John Doe");

        when(patientRepository.save(any(Patient.class))).thenReturn(patient);

        String patient_no = patientService.createPatient(request);

        System.out.println("patient_no = " + patient_no);
        assertEquals(patient_no, patient.getPatient_no());
        verify(patientRepository, times(1)).save(any(Patient.class));
    }
}
