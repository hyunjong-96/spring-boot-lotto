package com.zeepyharvard.springbootlotto.repository;

import com.zeepyharvard.springbootlotto.domain.LottoTicket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LottoTicketRepository extends JpaRepository<LottoTicket,Long> {
}
