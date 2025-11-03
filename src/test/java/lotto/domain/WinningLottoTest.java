package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class WinningLottoTest {

    private final List<Integer> VALID_WINNING_NUMBERS = List.of(1, 2, 3, 4, 5, 6);

    @DisplayName("보너스 번호가 1~45 범위를 벗어나면 예외가 발생한다.")
    @Test
    void 보너스_번호_범위_초과_테스트() {
        // 46일 때
        assertThatThrownBy(() -> new WinningLotto(VALID_WINNING_NUMBERS, 46))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 보너스 번호는 1부터 45 사이여야 합니다.");

        // 0일 때
        assertThatThrownBy(() -> new WinningLotto(VALID_WINNING_NUMBERS, 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 보너스 번호는 1부터 45 사이여야 합니다.");
    }

    @DisplayName("보너스 번호가 당첨 번호 6개와 중복되면 예외가 발생한다.")
    @Test
    void 보너스_번호_중복_테스트() {
        assertThatThrownBy(() -> new WinningLotto(VALID_WINNING_NUMBERS, 6))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
    }

    @DisplayName("당첨 번호 6개가 유효하지 않으면 Lotto 클래스가 예외를 발생시킨다.")
    @Test
    void 당첨_번호_자체_검증_테스트() {
        assertThatThrownBy(() -> new WinningLotto(List.of(1, 2, 3, 4, 5, 6, 7), 8))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 로또 번호는 6개여야 합니다.");
    }

}