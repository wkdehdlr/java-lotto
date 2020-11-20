package step2.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class LottoTicketMakerTest {

    @ParameterizedTest
    @DisplayName("입력한 로또 가격 만큼 티켓 생성 갯수 확인")
    @ValueSource(ints = {14})
    public void 입력한_로또_가격_확인(int lottoAmount) {
        LottoTicketMaker lottoTicketMaker = LottoTicketMaker.of(14000);
        assertEquals(lottoAmount, lottoTicketMaker.lottoPurchaseQty());
    }

    @ParameterizedTest
    @DisplayName("입력한_가격으로_로또_티켓_정상출력_갯수확인")
    @CsvSource(value = {"14000:14", "2000:2"}, delimiter = ':')
    public void 입력한_가격으로_로또_티켓_정상출력_갯수확인(int lottoAmount, int qty) {
        LottoTicketMaker lottoTicketMaker = LottoTicketMaker.of(lottoAmount);
        assertEquals(qty, lottoTicketMaker.getLottos().size());
    }

    @ParameterizedTest
    @DisplayName("로또 구매 값 0인 경우 예외")
    @ValueSource(ints = {0})
    public void 로또_구매값_0원(int value) {
        assertThatThrownBy(() -> LottoTicketMaker.purchaseAmountValidate(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("구입 금액 값이 없습니다.");

    }

    @ParameterizedTest
    @DisplayName("로또 구매 값 1000원 이하인 경우 예외")
    @ValueSource(ints = {100, 200, 500})
    public void 로또_구매값_1000원이하(int value) {
        assertThatThrownBy(() -> LottoTicketMaker.purchaseAmountValidate(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("구입 금액은 1000원부터 입력가능합니다.");

    }

}
