package com.zeepyharvard.springbootlotto.controller;

import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.JsonFieldType;

import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;

public class LottoDocumentation {
    public static RestDocumentationResultHandler createLottoTicket(){
        return document("Lotto/create",
                requestFields(
                        fieldWithPath("lottoTicket").type(JsonFieldType.ARRAY).description("로또 번호예요")   //요청json의 필드
                ),
                responseHeaders(
                        headerWithName("Location").description("생성된 로또 아이디에요")
                ));
    }
}

//pathParameters(), requestFields()많이사용
