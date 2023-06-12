package com.project.hdjunction.dto.patient;

import com.project.hdjunction.domain.Hospital;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    public CreatePatientRequest(int hospital_id, String patient_nm, String gender_cd, String birth, String phone_no) {
        this.hospital_id = hospital_id;
        this.patient_nm = patient_nm;
        this.gender_cd = gender_cd;
        this.birth = birth;
        this.phone_no = phone_no;
    }
}
