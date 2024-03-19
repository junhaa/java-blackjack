package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.TestDeckFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {

    @DisplayName("카드 덱에서 카드를 한 장 뽑는다.")
    @Test
    void drawCard() {
        Deck deck = new TestDeckFactory().create();

        Card card = deck.pop();

        assertThat(card).isEqualTo(Card.of(Rank.KING, Suit.DIAMOND));
    }
}
