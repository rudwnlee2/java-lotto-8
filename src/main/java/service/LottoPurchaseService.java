package service;

import lotto.domain.Lotto;
import lotto.domain.LottoMachine;
import lotto.domain.Money;

import java.util.List;

public class LottoPurchaseService {

    private final LottoMachine lottoMachine;

    public LottoPurchaseService(LottoMachine lottoMachine) {
        this.lottoMachine = lottoMachine;
    }

    public List<Lotto> purchaseLottos(Money money) {
        int count = money.calculateLottoCount();
        List<Lotto> purchasedLottos = lottoMachine.generateLottos(count);

        return purchasedLottos;
    }


}
