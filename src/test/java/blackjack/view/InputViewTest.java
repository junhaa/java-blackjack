package blackjack.view;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import blackjack.model.participant.Name;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

class InputViewTest {

    @Test
    @DisplayName("입력받은 이름을 List 형태로 반환한다.")
    void readPlayerNames() {
        String names = "몰리,리브";
        assertThat(InputView.readPlayerNames(() -> names)).containsExactly("몰리", "리브");
    }

    @Test
    @DisplayName("추가 선택 입력 시 y 또는 n이 아닌 경우 예외를 던진다.")
    void readHitOrNotByInvalidInput() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> InputView.readHitOrNot(new Name("리브"), () -> "yes"))
                .withMessage("y 혹은 n만 입력할 수 있습니다.");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("추가 선택 입력 시 blank가 들어온 경우")
    void readHitOrNotByInvalid(String input) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> InputView.readHitOrNot(new Name("리브"), () -> input))
                .withMessage("y 혹은 n만 입력할 수 있습니다.");
    }

    @Test
    @DisplayName("배팅 금액 입력 시 숫자가 아닌 입력값인 경우 예외를 던진다.")
    void readBettingAmountByNotInteger() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> InputView.readBettingAmount(new Name("몰리"), () -> "돈"))
                .withMessage("숫자만 입력할 수 있습니다.");
    }
}
