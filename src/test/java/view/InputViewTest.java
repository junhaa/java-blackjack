package view;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class InputViewTest {
    @Nested
    @DisplayName("사용자에게 이름을 입력 받는 테스트")
    class ReadNames {
        @DisplayName("interface Reader로부터 받은 String을 List<String>으로 반환한다.")
        @Test
        void stringToList() {
            Assertions.assertThat(InputView.readNames(() -> "a,b,c"))
                    .isEqualTo(List.of("a", "b", "c"));
        }

        @DisplayName("null 혹은 빈 문자열을 받으면 예외를 발생한다.")
        @ParameterizedTest
        @NullAndEmptySource
        @ValueSource(strings = {" ", "\t", "\n"})
        void emptyInputException(String input) {
            Assertions.assertThatThrownBy(() -> InputView.readNames(() -> input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("입력값에 공백이나 null을 넣을 수 없습니다.");
        }

        @DisplayName("입력값 끝에 쉼표가 있을 경우 예외를 발생한다.")
        @ParameterizedTest
        @ValueSource(strings = {"a,b,"})
        void emptyNameException(String input) {
            Assertions.assertThatThrownBy(() -> InputView.readNames(() -> input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("입력값 끝에 쉼표(,)를 넣을 수 없습니다.");
        }

        @DisplayName("공백을 제거한 이름을 반환한다.")
        @Test
        void nameWithSpaces() {
            Assertions.assertThat(InputView.readNames(() -> "a , b , c"))
                    .isEqualTo(List.of("a", "b", "c"));
        }
    }

    @Nested
    @DisplayName("사용자에게 대답(y 또는 n) 입력 받는 테스트")
    class ReadAnswer {
        @DisplayName("null 혹은 빈 문자열을 받으면 예외를 발생한다.")
        @ParameterizedTest
        @NullAndEmptySource
        @ValueSource(strings = {" ", "\t", "\n"})
        void emptyInputException(String input) {
            Assertions.assertThatThrownBy(() -> InputView.readAnswer(() -> input, "test"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("입력값에 공백이나 null을 넣을 수 없습니다.");
        }

        @Test
        @DisplayName("y 또는 n이 아닌 문자가 나올 경우 예외 발생")
        void wrongAnswer() {
            Assertions.assertThatThrownBy(() -> InputView.readAnswer(() -> "a", "test"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("y또는 n만 입력 받을 수 있습니다.");
        }
    }

    @Nested
    @DisplayName("사용자에게 배팅 금액을 받는 테스트")
    class ReadBettingMoney {
        @DisplayName("null 혹은 빈 문자열을 받으면 예외를 발생한다.")
        @ParameterizedTest
        @NullAndEmptySource
        @ValueSource(strings = {" ", "\t", "\n"})
        void emptyInputException(String input) {
            Assertions.assertThatThrownBy(() -> InputView.readBettingMoney(() -> input, "test"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("입력값에 공백이나 null을 넣을 수 없습니다.");
        }

        @Test
        @DisplayName("숫자가 아닌 값이 나타날 경우 예외가 발생한다.")
        void validateNumeric() {
            Assertions.assertThatThrownBy(() -> InputView.readBettingMoney(() -> "abc", "test"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("금액과 관련된 연산은 숫자만 입력 가능합니다.");
        }

        @Test
        @DisplayName("최소 금액보다 작은 금액일 경우 배팅할 수 없다.")
        void validateAmount() {
            Assertions.assertThatThrownBy(() -> InputView.readBettingMoney(() -> "-100", "test"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("최소 금액보다 작은 금액으로 배팅할 수 없습니다.");
        }
    }
}
