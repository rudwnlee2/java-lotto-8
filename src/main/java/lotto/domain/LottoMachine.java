package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;

public class LottoMachine {

    private final List<Lotto> lottos;

    public LottoMachine(Money money) {
        int count = money.calculateLottoCount();
        this.lottos = generateLottos(count);
    }

    private List<Lotto> generateLottos(int count) {
        List<Lotto> generatedLottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            generatedLottos.add(new Lotto(generateLottoNumbers()));
        }
        return generatedLottos;
    }

    private List<Integer> generateLottoNumbers() {
        List<Integer> lottoNumbers = Randoms.pickUniqueNumbersInRange(Lotto.MIN_NUMBER, Lotto.MAX_NUMBER, Lotto.LOTTO_SIZE);
        return lottoNumbers;
    }

}