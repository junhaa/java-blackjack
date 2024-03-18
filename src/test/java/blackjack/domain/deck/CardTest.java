package blackjack.domain.deck;

import static blackjack.domain.deck.Kind.CLOVER;
import static blackjack.domain.deck.Kind.DIAMOND;
import static blackjack.domain.deck.Kind.SPADE;
import static blackjack.domain.deck.Value.ACE;
import static blackjack.domain.deck.Value.FOUR;
import static blackjack.domain.deck.Value.JACK;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {

    @DisplayName("카드 트럼프 순서에 따라 정해진 문양과 값을 가진 카드가 생성된다")
    @Test
    void should_CreateCard_When_GiveCardOrder() {
        Card testCard1 = new Card(SPADE, ACE);
        Card testCard2 = new Card(DIAMOND, ACE);

        assertAll(
                () -> assertThat(testCard1.getKind()).isEqualTo(SPADE),
                () -> assertThat(testCard1.getValue()).isEqualTo(ACE),

                () -> assertThat(testCard2.getKind()).isEqualTo(DIAMOND),
                () -> assertThat(testCard2.getValue()).isEqualTo(ACE)
        );
    }

    @DisplayName("카드가 가진 점수를 확인할 수 있다")
    @Test
    void should_getCardScore() {
        Card testCard1 = new Card(SPADE, JACK);
        Card testCard2 = new Card(CLOVER, FOUR);

        assertAll(
                () -> assertThat(testCard1.getScore()).isEqualTo(10),
                () -> assertThat(testCard2.getScore()).isEqualTo(4)
        );
    }
}
