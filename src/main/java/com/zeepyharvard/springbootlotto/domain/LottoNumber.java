package com.zeepyharvard.springbootlotto.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LottoNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lotto_number_gen")
    @SequenceGenerator(name = "lotto_number_gen",sequenceName = "lotto_number_sequence")
    private Long id;

    @Enumerated(value = EnumType.ORDINAL)
    private LottoBall number;
}
