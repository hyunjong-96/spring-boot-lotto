package com.zeepyharvard.springbootlotto.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LottoTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lotto_ticket_gen")
    @SequenceGenerator(name = "lotto_ticket_gen",sequenceName = "lotto_ticket_sequence")
    private Long id;

    @OneToMany
    private List<LottoNumber> lottoNumbers;
}
