package blackjack.domain;

import static blackjack.domain.card.Shape.DIAMOND;
import static blackjack.domain.card.Value.ACE;
import static blackjack.domain.card.Value.FOUR;
import static blackjack.domain.card.Value.JACK;
import static blackjack.domain.card.Value.QUEEN;
import static blackjack.domain.card.Value.THREE;
import static blackjack.domain.card.Value.TWO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.score.Score;
import blackjack.domain.player.Player;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class PlayerTest {
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "      ", "\n"})
    @DisplayName("공백 이름으로는 플레이어를 생성하면 예외가 발생한다.")
    void throwsExceptionWhenNameIsBlankTest(String blankName) {
        assertThatThrownBy(() -> new Player(blankName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름이 비어있습니다.");
    }

    @Test
    @DisplayName("덱으로 부터 카드 한장을 받아올 수 있다.")
    void hitTest() {
        List<Card> cards = List.of(new Card(DIAMOND, TWO), new Card(DIAMOND, THREE), new Card(DIAMOND, FOUR));
        Deck deck = new Deck(cards);

        Player player = new Player("pedro");
        player.hit(deck);

        List<Card> playerCards = player.getCards();
        assertThat(playerCards).hasSize(1);
    }

    @Test
    @DisplayName("자신의 점수를 계산할 수 있다.")
    void calculateScoreTest() {
        List<Card> cards = List.of(new Card(DIAMOND, TWO), new Card(DIAMOND, THREE), new Card(DIAMOND, FOUR));
        Deck deck = new Deck(cards);

        Player player = new Player("pedro");
        for (int i = 0; i < cards.size(); i++) {
            player.hit(deck);
        }

        assertThat(player.calculateScore().getScore()).isEqualTo(9);
    }

    @ParameterizedTest
    @MethodSource("cardsAndBustStatus")
    @DisplayName("자신의 버스트 여부를 판단할 수 있다.")
    void checkBustTest(List<Card> cards, boolean expected) {
        Deck deck = new Deck(cards);

        Player player = new Player("pedro");
        for (int i = 0; i < cards.size(); i++) {
            player.hit(deck);
        }

        Score score = player.calculateScore();

        assertThat(score.isBusted()).isEqualTo(expected);
    }

    private static Stream<Arguments> cardsAndBustStatus() {
        return Stream.of(
                Arguments.arguments(List.of(new Card(DIAMOND, JACK), new Card(DIAMOND, QUEEN), new Card(DIAMOND, ACE)),
                        false),
                Arguments.arguments(List.of(new Card(DIAMOND, JACK), new Card(DIAMOND, QUEEN), new Card(DIAMOND, TWO)),
                        true)
        );
    }

    @ParameterizedTest
    @MethodSource("cardsAndShouldHit")
    @DisplayName("자신의 버스트 여부를 판단할 수 있다.")
    void shouldHitTest(List<Card> cards, boolean expected) {
        Deck deck = new Deck(cards);

        Player player = new Player("pedro");
        for (int i = 0; i < cards.size(); i++) {
            player.hit(deck);
        }

        assertThat(player.shouldHit()).isEqualTo(expected);
    }

    private static Stream<Arguments> cardsAndShouldHit() {
        return Stream.of(
                Arguments.arguments(List.of(new Card(DIAMOND, JACK), new Card(DIAMOND, ACE)),
                        false),
                Arguments.arguments(List.of(new Card(DIAMOND, JACK), new Card(DIAMOND, QUEEN), new Card(DIAMOND, ACE)),
                        false),
                Arguments.arguments(List.of(new Card(DIAMOND, JACK), new Card(DIAMOND, QUEEN), new Card(DIAMOND, TWO)),
                        false),
                Arguments.arguments(List.of(new Card(DIAMOND, JACK), new Card(DIAMOND, QUEEN)),
                        true)
        );
    }
}
