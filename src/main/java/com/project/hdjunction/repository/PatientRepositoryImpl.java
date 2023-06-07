package com.project.hdjunction.repository;

import com.project.hdjunction.domain.QPatient;
import com.project.hdjunction.domain.QVisit;
import com.project.hdjunction.dto.patient.PatientOneResponse;
import com.project.hdjunction.dto.patient.PatientWithVisitsResponse;
import com.project.hdjunction.dto.patient.PatientSearch;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PatientRepositoryImpl implements PatientRepositoryCustom{
    private final EntityManager em;

    @Override
    public List<PatientWithVisitsResponse> selectPatient(int patient_id){
        JPAQueryFactory query = new JPAQueryFactory(em);
        QPatient patient = QPatient.patient;
        QVisit visit = QVisit.visit;

        List<PatientWithVisitsResponse> patientList = query
                .select(Projections.constructor(PatientWithVisitsResponse.class,
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
    @Override
    public Page<PatientOneResponse> selectListPatient(PatientSearch patientSearch, Pageable pageable){
        JPAQueryFactory query = new JPAQueryFactory(em);
        QPatient patient = QPatient.patient;
        long totalCnt = query.selectFrom(patient).where(patientSearch(patientSearch)).fetch().size();

        List<PatientOneResponse> boardList = query
                .select(Projections.constructor(PatientOneResponse.class,
                        patient.id,
                        patient.patient_nm,
                        patient.patient_no,
                        patient.gender_cd,
                        patient.birth,
                        patient.phone_no))
                .from(patient)
                .where(patientSearch(patientSearch))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(patient.patient_no.asc())
                .fetch();

        return new PageImpl<>(boardList, pageable, totalCnt);

    }

    private BooleanExpression patientSearch(PatientSearch patientSearch){
        if(patientSearch== null || !StringUtils.hasText(patientSearch.getSearchData())){
            return null;
        }
        if(patientSearch.getSearchGbn().equals("이름")){
            return QPatient.patient.patient_nm.like("%"+patientSearch.getSearchData()+"%");
        }else if(patientSearch.getSearchGbn().equals("한자등록번호")){
            return QPatient.patient.patient_no.like("%"+patientSearch.getSearchData()+"%");
        }else if(patientSearch.getSearchGbn().equals("생년월일")){
            return QPatient.patient.birth.like("%"+patientSearch.getSearchData()+"%");
        }else{
            return null;
        }
    }
}
