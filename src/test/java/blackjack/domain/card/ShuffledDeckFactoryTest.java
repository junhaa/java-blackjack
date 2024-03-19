package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Stack;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ShuffledDeckFactoryTest {

    @DisplayName("52장으로 구성된 덱에는 모든 종류의 카드가 한 장씩만 포함된다.")
    @Test
    void createDistinctCards() {
        Stack<Card> expectedCards = new Stack<>();
        for (final Rank rank : Rank.values()) {
            for (final Suit suit : Suit.values()) {
                expectedCards.push(Card.of(rank, suit));
            }
        }

        final Deck actual = new ShuffledDeckFactory().create();
        final Stack<Card> actualCards = new Stack<>();
        for (int cardIndex = 0; cardIndex < 52; cardIndex++) {
            actualCards.push(actual.pop());
        }

        assertThat(actualCards).containsExactlyInAnyOrderElementsOf(expectedCards);
    }
}
