package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public static final int LOTTO_SIZE = 6;
    public static final int MIN_NUMBER = 1;
    public static final int MAX_NUMBER = 45;
    public static final int LOTTO_PRICE = 1000;

    public Lotto(List<Integer> numbers) {
        validate(numbers);

        List<Integer> sortedNumbers = new ArrayList<>(numbers);
        Collections.sort(sortedNumbers);

        this.numbers = sortedNumbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }

        duplicateNumberValidate(numbers);
        numberRangeValidate(numbers);
    }

    // TODO: 추가 기능 구현

    private void numberRangeValidate(List<Integer> numbers) {
        int minNumber = Collections.min(numbers);
        int maxNumber = Collections.max(numbers);

        if (minNumber < MIN_NUMBER || maxNumber > MAX_NUMBER) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 " + MIN_NUMBER + "부터 " + MAX_NUMBER + " 사이여야 합니다.");
        }
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    private void duplicateNumberValidate(List<Integer> numbers) {
        if (numbers.size() != numbers.stream().distinct().count()) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 중복이 없어야 합니다");
        }
    }

    public LottoRank calculateRank(WinningLotto winningLotto) {

        int matchCount = (int) this.numbers.stream()
                .filter(winningLotto.getWinningLotto().getNumbers()::contains)
                .count();

        boolean matchBonus = this.numbers.contains(winningLotto.getBonusNumber());

        return LottoRank.valueOf(matchCount, matchBonus);
    }

}