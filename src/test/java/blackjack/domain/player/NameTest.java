package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class NameTest {

    @Test
    @DisplayName("문자열을 통해서 이름을 생성한다.")
    void Name_Instance_create_with_String() {
        assertThatCode(() -> {
            Name name = new Name("초롱");
            Assertions.assertEquals(name.getValue(), "초롱");
        }).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("문자열에 공백이 포함되는 이름을 생성될 수 없다.")
    void Name_Instance_not_include_blank() {
        assertThatThrownBy(() -> new Name("초 롱"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("초 롱 는 공백을 포함 하고 있습니다");
    }
}
