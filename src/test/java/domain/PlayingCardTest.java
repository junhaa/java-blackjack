package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static domain.PlayingCardShape.HEART;
import static domain.PlayingCardValue.ACE;
import static domain.PlayingCardValue.FIVE;
import static org.assertj.core.api.Assertions.assertThat;

public class PlayingCardTest {

    @DisplayName("카드의 모양과 숫자를 입력하면 인스턴스가 생성된다.")
    @Test
    void createPlayingCardTest() {
        // When
        PlayingCard playingCard = new PlayingCard(HEART, FIVE);

        // Then
        assertThat(playingCard).isNotNull();
    }

    @DisplayName("카드의 숫자를 반환한다.")
    @MethodSource("getValueParameters")
    @ParameterizedTest(name = "[{index}] \"{0}\" => {1}")
    void getValueTest(PlayingCard playingCard, int expect) {
        // When
        int result = playingCard.getValue();

        // Then
        assertThat(result).isEqualTo(expect);
    }

    private static Stream<Arguments> getValueParameters() {
        return Stream.of(
                Arguments.of(new PlayingCard(HEART, ACE), 1),
                Arguments.of(new PlayingCard(HEART, FIVE), 5)
        );
    }
}
