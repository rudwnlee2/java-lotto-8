package lotto.domain;

public class Money {

    private final int value;

    public Money(int value) {
        validate(value);
        this.value = value;
    }

    private void validate(int value) {
        if (value < Lotto.LOTTO_PRICE) {
            throw new IllegalArgumentException("[ERROR] " + Lotto.LOTTO_PRICE + "원 이상이여야 합니다.");
        }

        if (value % Lotto.LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("[ERROR] " + Lotto.LOTTO_PRICE + "원 단위 금액이어야 합니다.");
        }
    }

    public int calculateLottoCount() {
        return value / Lotto.LOTTO_PRICE;
    }

}
