package com.zeepyharvard.springbootlotto.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zeepyharvard.springbootlotto.dto.LottoTicketReq;
import com.zeepyharvard.springbootlotto.service.LottoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = LottoController.class)
@ExtendWith(RestDocumentationExtension.class) //api문서 자동으로 만들때 restDoc에서 필요한것을 사용하기 위해 가져오는것.
class LottoControllerTest {
    @MockBean
    private LottoService lottoService;

    private MockMvc mockMvc;//WebApplicationCOntext와 restDocumentationContextProvider의 의존성을 받기 위함 & mocMvc사용할떄 필요한 인코딩, Security를 적용해주기 위한 과정.

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp(WebApplicationContext webApplicationCOntext, RestDocumentationContextProvider restDocumentationContextProvider){
        this.mockMvc = MockMvcBuilders //mockMvc를 사용하기 위한 환경을 만드는 과정.
                .webAppContextSetup(webApplicationCOntext)
                .addFilter(new CharacterEncodingFilter("UTF-8",true))
                .apply(documentationConfiguration(restDocumentationContextProvider))
                .build();

        this.objectMapper = new ObjectMapper();//필드초기화를 해줘야한다.
    }

    @Test
    void createLottoTicket() throws Exception {
        given(lottoService.save(any())).willReturn(1L);//임의의 long값 ,lottoService.save를 불렀을떄 any()를 통해 어떤 파라미터값을 넣어주면 willReturn으로 어떤값이 반환될꺼라고 예상.
        LottoTicketReq lottoTicketReq = new LottoTicketReq(Arrays.asList(1,2,3,4));

        //post로 body에 LottoTicketReq 객체를 바로 넣어줄수없으므로 objectMapper.writeAsString메소드를 통해 Json으로 변환해준다.
        mockMvc.perform(post("/api/create")
        .content(objectMapper.writeValueAsString(lottoTicketReq))//body부분
                .contentType(MediaType.APPLICATION_JSON))//contentType
                .andExpect(status().isCreated())//실행하고 응답의 결과예측
                .andExpect(header().string("Location","/api/lotto/1"))//header의 Loaction이라는 key에 value.
                .andDo(print())
                .andDo(LottoDocumentation.createLottoTicket());;
    }
}