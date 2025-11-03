package lotto.controller;

import lotto.domain.Lotto;
import lotto.domain.LottoRank;
import lotto.domain.Money;
import lotto.domain.WinningLotto;
import lotto.view.InputView;
import lotto.view.OutputView;
import lotto.service.LottoResultService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LottoResultController {

    private final LottoResultService lottoResultService;

    public LottoResultController(LottoResultService lottoResultService) {
        this.lottoResultService = lottoResultService;
    }

    public void processResults(List<Lotto> purchasedLottos, Money money) {
        WinningLotto winningLotto = attemptReadWinningLotto();

        Map<LottoRank, Integer> statistics = lottoResultService.calculateStatistics(purchasedLottos, winningLotto);
        double profitRate = lottoResultService.calculateProfitRate(statistics, money);

        OutputView.printStatistics(statistics, profitRate);
    }

    private WinningLotto attemptReadWinningLotto() {
        while (true) {
            try {
                String winningNumbersInput = InputView.inputWinningNumbers();
                String bonusNumberInput = InputView.inputBonusNumber();

                List<Integer> winningNumbers = parseWinningNumbers(winningNumbersInput);
                int bonusNumber = parseBonusNumber(bonusNumberInput);

                return new WinningLotto(winningNumbers, bonusNumber);
            } catch (IllegalArgumentException e) {
                OutputView.printError(e.getMessage());
            }
        }
    }

    private List<Integer> parseWinningNumbers(String input) {
        List<Integer> numbers = new ArrayList<>();

        String[] parts = input.split(",");

        try {
            for (String part : parts) {
                String trimmedPart = part.trim();
                int number = Integer.parseInt(trimmedPart);
                numbers.add(number);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 숫자로만 입력해야 합니다.");
        }

        return numbers;
    }

    private int parseBonusNumber(String input) {
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 숫자로만 입력해야 합니다.");
        }
    }

}
