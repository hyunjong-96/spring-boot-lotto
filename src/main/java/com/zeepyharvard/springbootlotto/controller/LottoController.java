package com.zeepyharvard.springbootlotto.controller;

import com.zeepyharvard.springbootlotto.dto.LottoTicketReq;
import com.zeepyharvard.springbootlotto.service.LottoService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class LottoController {
    private final LottoService lottoService;

    @PostMapping("/api/create")
    public ResponseEntity<Void> createLottoTicket(@RequestBody LottoTicketReq lottoTicket){
        Long id = lottoService.save(lottoTicket.getLottoTicket());

        return ResponseEntity.created(URI.create("/api/lotto/"+id)).build();
    }
}
