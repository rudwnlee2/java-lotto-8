package lotto.controller;

import lotto.domain.Lotto;
import lotto.domain.Money;
import lotto.view.InputView;
import lotto.view.OutputView;
import service.LottoPurchaseService;

import java.util.List;

public class LottoPurchaseController {

    private final LottoPurchaseService lottoPurchaseService;

    public LottoPurchaseController(LottoPurchaseService lottoPurchaseService) {
        this.lottoPurchaseService = lottoPurchaseService;
    }

    public void purchaseLottos() {
        int amount = InputView.inputPurchaseAmount();
        Money money = new Money(amount);

        List<Lotto> purchasedLottos = lottoPurchaseService.purchaseLottos(money);

        OutputView.printPurchaseCount(purchasedLottos.size());
        OutputView.printLottos(purchasedLottos);
    }


}
