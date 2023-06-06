package com.project.hdjunction.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CodeGroup {

    @Id
    private String code_group;
    private String code_group_nm;
    private String description;

    @OneToMany(mappedBy = "code_group")
    private List<Code> codes;
}
