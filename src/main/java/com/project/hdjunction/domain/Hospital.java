package com.project.hdjunction.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Hospital {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="hospital_id")
    private Long id;
    private String hospital_nm;
    private String medial_no;
    private String director_nm;

    @OneToMany(mappedBy = "hospital")
    private List<Patient> patients;

    @OneToMany(mappedBy = "hospital")
    private List<Visit> visits;

}
