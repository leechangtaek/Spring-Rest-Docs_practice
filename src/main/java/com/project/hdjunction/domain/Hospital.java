package com.project.hdjunction.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private int id;
    private String hospital_nm;
    private String medial_no;
    private String director_nm;

    @JsonIgnore
    @OneToMany(mappedBy = "hospital")
    private List<Patient> patients;

    @JsonIgnore
    @OneToMany(mappedBy = "hospital")
    private List<Visit> visits;

}
