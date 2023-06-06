package com.project.hdjunction.dto.patient;

import com.project.hdjunction.domain.Hospital;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CreatePatientRequest {

    @NotEmpty
    private int hospital_id;

    private String patient_nm;
    private String gender_cd;
    private String birth;
    private String phone_no;

}
