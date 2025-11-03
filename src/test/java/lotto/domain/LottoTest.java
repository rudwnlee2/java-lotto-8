package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static lotto.domain.Lotto.MAX_NUMBER;
import static lotto.domain.Lotto.MIN_NUMBER;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class LottoTest {
    @Test
    void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 로또 번호는 6개여야 합니다.");
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 로또 번호는 중복이 없어야 합니다");
    }

    // TODO: 추가 기능 구현에 따른 테스트 코드 작성

    @DisplayName("로또 번호의 개수가 6개 미만이면 예외가 발생한다.")
    @Test
    void 로또_번호의_개수가_6개_미만이면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 로또 번호는 6개여야 합니다.");
    }

    @DisplayName("로또 번호가 1~45 범위를 벗어나면 예외가 발생한다. (46)")
    @Test
    void 로또_번호가_45보다_크면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 46)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 로또 번호는 " + MIN_NUMBER + "부터 " + MAX_NUMBER + " 사이여야 합니다.");
    }

    @DisplayName("로또 번호가 1~45 범위를 벗어나면 예외가 발생한다. (0)")
    @Test
    void 로또_번호가_1보다_작으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(0, 1, 2, 3, 4, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 로또 번호는 " + MIN_NUMBER + "부터 " + MAX_NUMBER + " 사이여야 합니다.");
    }

    @DisplayName("로또 번호는 생성 시 오름차순으로 정렬된다.")
    @Test
    void 로또_번호가_오름차순으로_정렬된다() {
        // given
        List<Integer> unorderedNumbers = List.of(6, 5, 4, 3, 2, 1);

        // when
        Lotto lotto = new Lotto(unorderedNumbers);

        // then
        List<Integer> expectedSortedNumbers = List.of(1, 2, 3, 4, 5, 6);
        assertThat(lotto.getNumbers()).isEqualTo(expectedSortedNumbers);
    }

    private WinningLotto winningLotto;

    @BeforeEach
    void setUp() {
        winningLotto = new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 7);
    }

    @DisplayName("calculateRank가 1등(FIRST)을 반환한다.")
    @Test
    void 랭크_계산_일등() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        assertThat(lotto.calculateRank(winningLotto)).isEqualTo(LottoRank.FIRST);
    }

    @DisplayName("calculateRank가 2등(SECOND)을 반환한다.")
    @Test
    void 랭크_계산_이등() {
        // 5개 일치 + 보너스 일치
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 7));
        assertThat(lotto.calculateRank(winningLotto)).isEqualTo(LottoRank.SECOND);
    }

    @DisplayName("calculateRank가 3등(THIRD)을 반환한다.")
    @Test
    void 랭크_계산_삼등() {
        // 5개 일치 + 보너스 불일치
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 8));
        assertThat(lotto.calculateRank(winningLotto)).isEqualTo(LottoRank.THIRD);
    }

    @DisplayName("calculateRank가 4등(FOURTH)을 반환한다.")
    @Test
    void 랭크_계산_사등() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 8, 9));
        assertThat(lotto.calculateRank(winningLotto)).isEqualTo(LottoRank.FOURTH);
    }

    @DisplayName("calculateRank가 5등(FIFTH)을 반환한다.")
    @Test
    void 랭크_계산_오등() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 8, 9, 10));
        assertThat(lotto.calculateRank(winningLotto)).isEqualTo(LottoRank.FIFTH);
    }

    @DisplayName("calculateRank가 꽝(NONE)을 반환한다.")
    @Test
    void 랭크_계산_꽝() {
        // 2개 일치
        Lotto lotto = new Lotto(List.of(1, 2, 10, 11, 12, 13));
        assertThat(lotto.calculateRank(winningLotto)).isEqualTo(LottoRank.NONE);
    }

}
