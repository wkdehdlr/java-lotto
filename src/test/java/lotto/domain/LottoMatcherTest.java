package lotto.domain;

import org.junit.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoMatcherTest {

    @Test
    public void 당첨결과_보너스번호없음() {
        final Lotto anyLotto = new Lotto(Arrays.asList(
                LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(3),
                LottoNumber.of(4), LottoNumber.of(5), LottoNumber.of(6)));

        final WinningLotto winningLotto = new WinningLotto(anyLotto);

        final LottoList lottos = new LottoList(Arrays.asList(
                anyLotto, // 6개 일치
                new Lotto(Arrays.asList( // 4개 일치
                        LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(3),
                        LottoNumber.of(4), LottoNumber.of(44), LottoNumber.of(45))),
                new Lotto(Arrays.asList( // 4개 일치
                        LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(3),
                        LottoNumber.of(4), LottoNumber.of(44), LottoNumber.of(45))),
                new Lotto(Arrays.asList( // 3개 일치
                        LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(3),
                        LottoNumber.of(43), LottoNumber.of(44), LottoNumber.of(45)))));

        final WinningResults winningResults = LottoMatcher.calculateWinningResults(lottos, winningLotto);

        assertThat(winningResults.get(Prize.FIRST)).isEqualTo(new WinningResult(Prize.FIRST, 1));
        assertThat(winningResults.get(Prize.SECOND)).isEqualTo(new WinningResult(Prize.SECOND, 0));
        assertThat(winningResults.get(Prize.THIRD)).isEqualTo(new WinningResult(Prize.THIRD, 0));
        assertThat(winningResults.get(Prize.FOURTH)).isEqualTo(new WinningResult(Prize.FOURTH, 2));
        assertThat(winningResults.get(Prize.FIFTH)).isEqualTo(new WinningResult(Prize.FIFTH, 1));
    }

    @Test
    public void 당첨결과_보너스번호있음() {
        final Lotto anyLotto = new Lotto(Arrays.asList(
                LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(3),
                LottoNumber.of(4), LottoNumber.of(5), LottoNumber.of(6)));

        final WinningLotto winningLotto = new WinningLotto(anyLotto, LottoNumber.of(43));

        final LottoList lottos = new LottoList(Arrays.asList(
                anyLotto, // 6개 일치
                new Lotto(Arrays.asList( // 5개 일치 + 보너스 번호 일치
                        LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(3),
                        LottoNumber.of(4), LottoNumber.of(5), LottoNumber.of(43))),
                new Lotto(Arrays.asList( // 4개 일치
                        LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(3),
                        LottoNumber.of(4), LottoNumber.of(44), LottoNumber.of(45))),
                new Lotto(Arrays.asList( // 3개 일치
                        LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(3),
                        LottoNumber.of(43), LottoNumber.of(44), LottoNumber.of(45)))));

        final WinningResults winningResults = LottoMatcher.calculateWinningResults(lottos, winningLotto);

        assertThat(winningResults.get(Prize.FIRST)).isEqualTo(new WinningResult(Prize.FIRST, 1));
        assertThat(winningResults.get(Prize.SECOND)).isEqualTo(new WinningResult(Prize.SECOND, 1));
        assertThat(winningResults.get(Prize.THIRD)).isEqualTo(new WinningResult(Prize.THIRD, 0));
        assertThat(winningResults.get(Prize.FOURTH)).isEqualTo(new WinningResult(Prize.FOURTH, 1));
        assertThat(winningResults.get(Prize.FIFTH)).isEqualTo(new WinningResult(Prize.FIFTH, 1));
    }
}