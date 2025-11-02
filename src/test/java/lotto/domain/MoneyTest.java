package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class MoneyTest {

    @DisplayName("유효한 금액(1000원 단위)으로 Money 객체를 생성한다.")
    @Test
    void 유효한_금액으로_객체를_생성한다() {
        // given
        int validAmount = 8000;

        // when
        Money money = new Money(validAmount);

        // then
        assertThat(money.getValue()).isEqualTo(validAmount);
    }

    @DisplayName("금액이 1000원 단위가 아니면 예외가 발생한다.")
    @Test
    void 금액이_1000원_단위가_아니면_예외가_발생한다() {
        assertThatThrownBy(() -> new Money(1500))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] " + Lotto.LOTTO_PRICE + "원 단위 금액이어야 합니다.");
    }

    @DisplayName("금액이 1000원 미만이면 예외가 발생한다.")
    @Test
    void 금액이_1000원_미만이면_예외가_발생한다() {
        assertThatThrownBy(() -> new Money(500))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] " + Lotto.LOTTO_PRICE + "원 이상이여야 합니다.");

        assertThatThrownBy(() -> new Money(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] " + Lotto.LOTTO_PRICE + "원 이상이여야 합니다.");
    }

    @DisplayName("구입 금액에 따른 로또 개수를 정확히 계산한다.")
    @Test
    void 로또_개수_계산_테스트() {
        // given
        Money money = new Money(8000);

        // when
        int count = money.calculateLottoCount();

        // then
        assertThat(count).isEqualTo(8);
    }
}