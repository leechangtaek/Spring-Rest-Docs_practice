package com.project.hdjunction.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.hdjunction.controller.PatientController;
import com.project.hdjunction.dto.patient.CreatePatientRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@WebMvcTest(PatientController.class)
@AutoConfigureRestDocs
public class PatientServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PatientService patientService;
    @MockBean
    private HospitalService hospitalService;
    @Test
    @DisplayName("환자등록")
    void 환자등록() throws Exception{

        String body = objectMapper.writeValueAsString(
                new CreatePatientRequest(1,"이창택","M","19941014","01089731257"));

        mockMvc.perform(
                        post("/patient").content(body)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(document("patient/create",
                        preprocessRequest(prettyPrint()),   // (2)
                        preprocessResponse(prettyPrint()),  // (3)
                        requestFields( 						// (4)
                                fieldWithPath("hospital_id").description("사용자 ID"),
                                fieldWithPath("patient_nm").description("환자명"),
                                fieldWithPath("gender_cd").description("성별"),
                                fieldWithPath("birth").description("생년월일"),
                                fieldWithPath("phone_no").description("전화번호")
                        )));
    }
}
