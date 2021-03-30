package com.zeepyharvard.springbootlotto.service;

import com.zeepyharvard.springbootlotto.domain.LottoBall;
import com.zeepyharvard.springbootlotto.domain.LottoNumber;
import com.zeepyharvard.springbootlotto.domain.LottoTicket;
import com.zeepyharvard.springbootlotto.repository.LottoNumberRepository;
import com.zeepyharvard.springbootlotto.repository.LottoTicketRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)   //의존성 주입!!!!!(중요)
public class LottoService {
    private final LottoTicketRepository lottoTicketRepository;
    private final LottoNumberRepository lottoNumberRepository;

    public Long save(List<Integer> lottoBalls){
        List<LottoNumber> lottoNumbers = lottoBalls.stream()
                .map(LottoBall::getLottoBall)
                .map(lottoBall -> {
                    LottoNumber lottoNumber = new LottoNumber(null,lottoBall);
                    return lottoNumberRepository.save(lottoNumber);
                }) //Stream<LottoNumber> => List<LottoNumber>
                .collect(Collectors.toList());
        LottoTicket lottoTicket = new LottoTicket(null,lottoNumbers);

        LottoTicket savedLottoTicket = lottoTicketRepository.save(lottoTicket);

        return savedLottoTicket.getId();
    }
}
