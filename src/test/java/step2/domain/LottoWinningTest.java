package step2.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import step2.constant.LottoWinningPrizes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LottoWinningTest {

    @ParameterizedTest
    @DisplayName("로또 당첨 금액 확인")
    @ValueSource(strings = "1,2,3,4,5,6")
    public void 로또_당첨_금액_확인(String lastLottoWinningNumbers) {
        List<Lotto> lottos = new ArrayList<>();
        Lotto lotto = new Lotto();
        IntStream.of(1, 2, 3, 7, 8, 9)
                .forEach(i -> lotto.getLottos().add(i));
        lottos.add(lotto);

        LottoWinning lottoWinning = LottoWinning.of(lastLottoWinningNumbers, lottos);
        assertThat(lottoWinning.getWinningAmount()).isEqualTo(5000);
    }

    @ParameterizedTest
    @DisplayName("로또 당첨 갯수 확인")
    @ValueSource(strings = "1,2,3,4,5,6")
    public void 당첨_갯수_확인(String lastLottoWinningNumbers) {
        List<Lotto> lottos = new ArrayList<>();
        Lotto lotto = new Lotto();
        IntStream.of(1, 2, 3, 7, 8, 9)
                .forEach(i -> lotto.getLottos().add(i));
        lottos.add(lotto);

        LottoWinning lottoWinning = LottoWinning.of(lastLottoWinningNumbers, lottos);
        assertThat(lottoWinning.getLottoWinningMap().get(LottoWinningPrizes.THIRD_MATCHES)).isEqualTo(1);
    }

    @Test
    @DisplayName("총 수익율 계산 확인")
    public void 수익율_계산_확인() {
        List<Lotto> lottos = new ArrayList<>();
        Lotto lotto = new Lotto();
        IntStream.of(1, 2, 3, 7, 8, 9)
                .forEach(i -> lotto.getLottos().add(i));
        lottos.add(lotto);
        LottoWinning lottoWinning = LottoWinning.of("1,2,3,4,5,6", lottos);

        double totalYield = 0.38;
        assertEquals(totalYield, lottoWinning.getTotalYield(13000));
    }

    @ParameterizedTest
    @DisplayName("지난 당첨번호 빈 값인 경우 ")
    @ValueSource(strings = {"",})
    public void 로또_지난_당첨번호_null(String lottoNumbers) {
        assertThatThrownBy(() -> LottoWinning.lottoNumberSizeValidate(lottoNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("지난 로또 번호를 입력해 주세요.");
    }

    @ParameterizedTest
    @DisplayName("지난 당첨번호 갯수 6개 이하 또는 이상인 경우 예외처리")
    @ValueSource(strings = {"1,2,3,", "5,6,7,8,9"})
    public void 로또_지난_당첨번호_갯수_유효체크(String lottoNumbers) {
        assertThatThrownBy(() -> LottoWinning.lottoNumberSizeValidate(lottoNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 6보다 작거나 클 수 없습니다.");
    }
}
