package com.project;

import com.project.hdjunction.domain.Code;
import com.project.hdjunction.domain.CodeGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;



@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init(){
        initService.dbInit1();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService{

        private final EntityManager em;

        public void dbInit1(){
            CodeGroup codeGroup1 = createCodeGroup("성별코드", "성별코드","성별을 표시");
            CodeGroup codeGroup2 = createCodeGroup("방문상태코드", "방문상태코드","환자방문의 상태(방문중, 종료, 취소)");
            CodeGroup codeGroup3 = createCodeGroup("진료과목코드", "진료과목코드","진료과목(내과, 안과 등)");
            CodeGroup codeGroup4 = createCodeGroup("진료유형코드", "진료유형코드","진료의 유형(약처방, 검사 등)");
            em.persist(codeGroup1);
            em.persist(codeGroup2);
            em.persist(codeGroup3);
            em.persist(codeGroup4);

            Code code1 = createCode(codeGroup1,"M","남");
            Code code2 = createCode(codeGroup1,"F","여");
            Code code3 = createCode(codeGroup2,"1","방문중");
            Code code4 = createCode(codeGroup2,"2","종료");
            Code code5 = createCode(codeGroup2,"3","취소");
            Code code6 = createCode(codeGroup3,"01","내과");
            Code code7 = createCode(codeGroup3,"02","안과");
            Code code8 = createCode(codeGroup4,"D","약처방");
            Code code9 = createCode(codeGroup4,"T","검사");
            em.persist(code1);
            em.persist(code2);
            em.persist(code3);
            em.persist(code4);
            em.persist(code5);
            em.persist(code6);
            em.persist(code7);
            em.persist(code8);
            em.persist(code9);

        }

        private CodeGroup createCodeGroup(String code_group, String code_group_nm, String description) {
            CodeGroup codeGroup = new CodeGroup();
            codeGroup.setCode_group(code_group);
            codeGroup.setCode_group_nm(code_group_nm);
            codeGroup.setDescription(description);
            return codeGroup;
        }
        private Code createCode(CodeGroup code_group, String code, String code_nm) {
            Code code1 = new Code();
            code1.setCode_group(code_group);
            code1.setCode(code);
            code1.setCode_nm(code_nm);
            return code1;
        }


    }

}


