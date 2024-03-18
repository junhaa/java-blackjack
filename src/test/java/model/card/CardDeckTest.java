package model.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.NoSuchElementException;
import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CardDeckTest {

    @DisplayName("생성 시 사용하는 덱 개수가 1 이상이면 객체 생성 성공")
    @ParameterizedTest
    @ValueSource(ints = {1, 4, 5, 10, 20})
    void testValidCreateCardDeck(int dequeCount) {
        int dequeSize = 52;
        CardDeck cardDeck = CardDeck.createShuffledDeck(dequeCount);
        assertThat(cardDeck.size()).isEqualTo(dequeCount * dequeSize);
    }

    @DisplayName("생성 시 사용하는 덱 개수가 1 미만이면 예외 발생")
    @ParameterizedTest
    @ValueSource(ints = {0, -1, -2, -5, -200})
    void testInvalidCreateCardDeck(int dequeCount) {
        assertThatThrownBy(() -> CardDeck.createShuffledDeck(dequeCount))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("카드 1장을 뽑으면 셔플된 카드의 사이즈가 1 감소한다")
    @ParameterizedTest
    @ValueSource(ints = {1, 4, 5, 10, 20})
    void testDrawCard(int dequeCount) {
        CardDeck cardDeck = CardDeck.createShuffledDeck(dequeCount);
        int previousSize = cardDeck.size();
        cardDeck.drawCard();
        assertThat(cardDeck.size()).isEqualTo(previousSize - 1);
    }

    @DisplayName("뽑을 카드가 없으면 예외 발생")
    @Test
    void testDrawCardFromEmptyDeck() {
        CardDeck cardDeck = CardDeck.createShuffledDeck(1);
        IntStream.range(0, cardDeck.size())
            .forEach(count -> cardDeck.drawCard());
        assertThatThrownBy(cardDeck::drawCard)
            .isInstanceOf(NoSuchElementException.class);
    }
}
