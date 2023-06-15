package com.project.hdjunction.dto.patient;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class EditPatientRequest {

    @NotNull
    private int patient_id;

    private String patient_nm;
    private String gender_cd;
    private String birth;
    private String phone_no;

    public EditPatientRequest(int patient_id, String patient_nm, String gender_cd, String birth, String phone_no) {
        this.patient_id = patient_id;
        this.patient_nm = patient_nm;
        this.gender_cd = gender_cd;
        this.birth = birth;
        this.phone_no = phone_no;
    }
}
