package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LottoRankTest {

    @DisplayName("6개 일치 시 1등(FIRST)을 반환한다.")
    @Test
    void 일등_테스트() {
        assertThat(LottoRank.valueOf(6, false)).isEqualTo(LottoRank.FIRST);
        assertThat(LottoRank.valueOf(6, true)).isEqualTo(LottoRank.FIRST);
    }

    @DisplayName("5개 일치 및 보너스 일치 시 2등(SECOND)을 반환한다.")
    @Test
    void 이등_테스트() {
        assertThat(LottoRank.valueOf(5, true)).isEqualTo(LottoRank.SECOND);
    }

    @DisplayName("5개 일치 및 보너스 불일치 시 3등(THIRD)을 반환한다.")
    @Test
    void 삼등_테스트() {
        assertThat(LottoRank.valueOf(5, false)).isEqualTo(LottoRank.THIRD);
    }

    @DisplayName("4개 일치 시 4등(FOURTH)을 반환한다.")
    @Test
    void 사등_테스트() {
        assertThat(LottoRank.valueOf(4, false)).isEqualTo(LottoRank.FOURTH);
        assertThat(LottoRank.valueOf(4, true)).isEqualTo(LottoRank.FOURTH);
    }

    @DisplayName("3개 일치 시 5등(FIFTH)을 반환한다.")
    @Test
    void 오등_테스트() {
        assertThat(LottoRank.valueOf(3, false)).isEqualTo(LottoRank.FIFTH);
        assertThat(LottoRank.valueOf(3, true)).isEqualTo(LottoRank.FIFTH);
    }

    @DisplayName("2개 이하 일치 시 꽝(NONE)을 반환한다.")
    @Test
    void 꽝_테스트() {
        assertThat(LottoRank.valueOf(2, true)).isEqualTo(LottoRank.NONE);
        assertThat(LottoRank.valueOf(1, false)).isEqualTo(LottoRank.NONE);
        assertThat(LottoRank.valueOf(0, false)).isEqualTo(LottoRank.NONE);
    }


}