package lotto;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;

public class LottoMachine {

    private final int money;
    private final List<Lotto> lottos;

    public LottoMachine(int money) {
        this.money = money;
        this.lottos = generateLottos(money);
    }

    private List<Lotto> generateLottos(int money) {
        int count = money / Lotto.LOTTO_PRICE;

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