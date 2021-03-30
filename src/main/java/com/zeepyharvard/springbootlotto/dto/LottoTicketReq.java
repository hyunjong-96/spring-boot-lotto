package com.zeepyharvard.springbootlotto.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LottoTicketReq {
    private List<Integer> lottoTicket;
}
