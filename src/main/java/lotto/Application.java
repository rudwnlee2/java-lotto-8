package lotto;

import lotto.controller.LottoPurchaseController;
import lotto.controller.LottoResultController;
import lotto.domain.Lotto;
import lotto.domain.LottoMachine;
import lotto.domain.Money;
import lotto.view.InputView;
import lotto.view.OutputView;
import lotto.service.LottoPurchaseService;
import lotto.service.LottoResultService;

import java.util.List;

public class Application {

    private final LottoPurchaseController purchaseController;
    private final LottoResultController resultController;

    public Application(LottoPurchaseController purchaseController, LottoResultController resultController) {
        this.purchaseController = purchaseController;
        this.resultController = resultController;
    }

    public static void main(String[] args) {
        // TODO: 프로그램 구현

        LottoMachine lottoMachine = new LottoMachine();
        LottoPurchaseService purchaseService = new LottoPurchaseService(lottoMachine);
        LottoPurchaseController purchaseController = new LottoPurchaseController(purchaseService);

        LottoResultService resultService = new LottoResultService();
        LottoResultController resultController = new LottoResultController(resultService);

        Application app = new Application(purchaseController, resultController);
        app.run();
    }

    public void run() {
        Money money = attemptReadMoney();
        List<Lotto> purchasedLottos = purchaseController.purchaseLottos(money);

        OutputView.printPurchaseCount(purchasedLottos.size());
        OutputView.printLottos(purchasedLottos);

        resultController.processResults(purchasedLottos, money);
    }

    private Money attemptReadMoney() {
        while (true) {
            try {
                int amount = InputView.inputPurchaseAmount();
                return new Money(amount);
            } catch (IllegalArgumentException e) {
                OutputView.printError(e.getMessage());
            }
        }
    }
}
