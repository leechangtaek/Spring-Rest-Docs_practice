package com.project.hdjunction.dto.patient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientListResponse {

    private int nowPage;
    private int startPage;
    private int endPage;
    private List<PatientOneResponse> patients;

    public PatientListResponse(List<PatientOneResponse> patients, int nowPage, int startPage, int endPage) {
        this.patients = patients;
        this.nowPage = nowPage;
        this.startPage = startPage;
        this.endPage = endPage;
    }

}
