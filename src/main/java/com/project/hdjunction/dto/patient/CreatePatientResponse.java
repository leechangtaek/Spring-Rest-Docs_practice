package com.project.hdjunction.dto.patient;

import lombok.Data;

@Data
public class CreatePatientResponse {

    private Long id;

    public CreatePatientResponse(Long id) {
        this.id = id;
    }
}
