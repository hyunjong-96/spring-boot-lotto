package com.zeepyharvard.springbootlotto.repository;

import com.zeepyharvard.springbootlotto.domain.LottoNumber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LottoNumberRepository extends JpaRepository<LottoNumber, Long> {
}
