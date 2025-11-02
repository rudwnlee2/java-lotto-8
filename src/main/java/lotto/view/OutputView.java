package lotto.view;

import lotto.domain.Lotto;

import java.util.List;

public class OutputView {

    public static final String PURCHASE_COUNT_MESSAGE = "%d개를 구매했습니다.";
    public static final String STATISTICS_HEADER = "당첨 통계";
    public static final String SEPARATOR = "---";
    public static final String STATISTICS_RESULT_FORMAT = "%s - %d개";
    public static final String TOTAL_PROFIT_RATE_MESSAGE = "총 수익률은 %.1f%%입니다.";

    public static void printPurchaseCount(int count) {
        System.out.println(String.format(PURCHASE_COUNT_MESSAGE, count));
    }

    public static void printLottos(List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            System.out.println(lotto.getNumbers());
        }
    }

    public static void printError(String message) {
        System.out.println(message);
    }

}
