package blackjack.model.deck;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.NoSuchElementException;
import java.util.stream.IntStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {
    private Deck deck;

    @BeforeEach
    void init() {
        deck = Deck.createByRandomOrder();
    }

    @Test
    @DisplayName("카드 한 세트(52장)을 다 소진할 경우 예외가 발생한다.")
    void throwErrorWhenNoCard() {
        assertThatThrownBy(() -> IntStream.range(0, 27).forEach(i -> deck.distributeInitialCard()))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("카드가 부족합니다.");
    }

    @Test
    @DisplayName("Deck에서 카드를 배분한다.")
    void distribute() {
        Deck deck = Deck.createByRandomOrder();
        int deckSizeBeforeDistribute = deck.getDeck().size();

        Card distributeCard = deck.distribute();

        int deckSizeAfterDistribute = deck.getDeck().size();
        assertAll(
                () -> assertThat(deckSizeAfterDistribute).isEqualTo(deckSizeBeforeDistribute - 1),
                () -> assertThat(deck.getDeck()).doesNotContain(distributeCard));
    }
}
