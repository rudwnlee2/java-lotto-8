package lotto.controller;

import lotto.domain.Lotto;
import lotto.domain.Money;
import lotto.view.InputView;
import lotto.view.OutputView;
import lotto.service.LottoPurchaseService;

import java.util.List;

public class LottoPurchaseController {

    private final LottoPurchaseService lottoPurchaseService;

    public LottoPurchaseController(LottoPurchaseService lottoPurchaseService) {
        this.lottoPurchaseService = lottoPurchaseService;
    }


    public List<Lotto> purchaseLottos(Money money) {
        // 서비스에게 구매를 요청하고 결과를 바로 반환합니다.
        return lottoPurchaseService.purchaseLottos(money);
    }

//    public void purchaseLottos() {
//        List<Lotto> purchasedLottos = attemptPurchase();
//
//        OutputView.printPurchaseCount(purchasedLottos.size());
//        OutputView.printLottos(purchasedLottos);
//    }

    private List<Lotto> attemptPurchase() {
        while (true) {
            try {
                int amount = InputView.inputPurchaseAmount();

                Money money = new Money(amount);

                List<Lotto> purchasedLottos = lottoPurchaseService.purchaseLottos(money);
                return purchasedLottos;

            } catch (IllegalArgumentException e) {
                OutputView.printError(e.getMessage());
            }
        }
    }


}
