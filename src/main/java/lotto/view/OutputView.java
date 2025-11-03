package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.LottoRank;

import java.util.List;
import java.util.Map;

public class OutputView {

    public static final String PURCHASE_COUNT_MESSAGE = "%d개를 구매했습니다.";
    public static final String STATISTICS_HEADER = "\n당첨 통계";
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

    public static void printStatistics(Map<LottoRank, Integer> statistics, double profitRate) {
        System.out.println(STATISTICS_HEADER);
        System.out.println(SEPARATOR);

        System.out.println(String.format(STATISTICS_RESULT_FORMAT,
                LottoRank.FIFTH.getFormattedDescription(), statistics.get(LottoRank.FIFTH)));

        System.out.println(String.format(STATISTICS_RESULT_FORMAT,
                LottoRank.FOURTH.getFormattedDescription(), statistics.get(LottoRank.FOURTH)));

        System.out.println(String.format(STATISTICS_RESULT_FORMAT,
                LottoRank.THIRD.getFormattedDescription(), statistics.get(LottoRank.THIRD)));

        System.out.println(String.format(STATISTICS_RESULT_FORMAT,
                LottoRank.SECOND.getFormattedDescription(), statistics.get(LottoRank.SECOND)));

        System.out.println(String.format(STATISTICS_RESULT_FORMAT,
                LottoRank.FIRST.getFormattedDescription(), statistics.get(LottoRank.FIRST)));

        System.out.println(String.format(TOTAL_PROFIT_RATE_MESSAGE, profitRate * 100.0));
    }

}
