package com.project.hdjunction.dto.patient;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class PatientSearch {

    private String searchGbn;
    private String searchData;
}
