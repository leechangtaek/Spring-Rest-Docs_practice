package com.project.hdjunction.repository;

import com.project.hdjunction.domain.QPatient;
import com.project.hdjunction.domain.QVisit;
import com.project.hdjunction.dto.patient.PatientResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PatientRepositoryImpl implements PatientRepositoryCustom{
    private final EntityManager em;

    @Override
    public List<PatientResponse> selectPatient(int patient_id){
        JPAQueryFactory query = new JPAQueryFactory(em);
        QPatient patient = QPatient.patient;
        QVisit visit = QVisit.visit;

        List<PatientResponse> patientList = query
                .select(Projections.constructor(PatientResponse.class,
                        patient.id,
                        patient.patient_nm,
                        patient.patient_no,
                        patient.gender_cd,
                        patient.birth,
                        patient.phone_no,
                        visit.accept_dt,
                        visit.visit_status_cd))
                .from(patient)
                .leftJoin(patient.visits,visit)
                .where(patient.id.eq(patient_id))
                .fetch();
        return patientList;
    }
}
