package service;

import lotto.domain.Lotto;
import lotto.domain.LottoRank;
import lotto.domain.Money;
import lotto.domain.WinningLotto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoResultService {

    public Map<LottoRank, Integer> calculateStatistics(List<Lotto> purchasedLottos, WinningLotto winningLotto) {
        Map<LottoRank, Integer> statistics = new HashMap<>();

        for (LottoRank rank : LottoRank.values()) {
            if (rank != LottoRank.NONE) {
                statistics.put(rank, 0);
            }
        }

        for (Lotto lotto : purchasedLottos) {
            LottoRank rank = lotto.calculateRank(winningLotto);
            if (rank != LottoRank.NONE) {
                statistics.put(rank, statistics.get(rank) + 1);
            }
        }

        return statistics;
    }

    public double calculateProfitRate(Map<LottoRank, Integer> statistics, Money money) {
        long totalPrizeMoney = 0;
        for (LottoRank rank : statistics.keySet()) {
            int count = statistics.get(rank);
            totalPrizeMoney += (long) rank.getPrizeMoney() * count;
        }

        if (money.getValue() == 0) {
            return 0.0;
        }

        return (double) totalPrizeMoney / money.getValue();
    }

}
