package com.project.hdjunction.dto.patient;

import com.project.hdjunction.domain.Hospital;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CreatePatientRequest {

    @NotNull
    private int hospital_id;

    @NotNull
    private String patient_nm;
    @NotNull
    private String gender_cd;

    private String birth;
    private String phone_no;

}
