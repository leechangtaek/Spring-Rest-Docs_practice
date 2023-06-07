package com.project.hdjunction.dto.patient;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class PatientWithVisitsResponse {

    private int patient_id;
    private String patient_nm;
    private String patient_no;
    private String gender_cd;
    private String birth;
    private String phone_no;
    private LocalDateTime accept_dt;
    private String visit_status_cd;

}
