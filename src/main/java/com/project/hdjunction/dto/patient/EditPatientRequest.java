package com.project.hdjunction.dto.patient;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class EditPatientRequest {

    @NotEmpty
    private int patient_id;

    private String patient_nm;
    private String gender_cd;
    private String birth;
    private String phone_no;

}
