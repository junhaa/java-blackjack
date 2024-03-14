package blackjack.player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.card.Card;
import blackjack.card.Number;
import blackjack.card.Shape;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    @DisplayName("딜러는 16점 이하이면 추가 드로우가 가능하다.")
    void ableToDrawTest() {
        // given
        List<Card> cards = List.of(
                Card.of(Shape.HEART, Number.JACK),
                Card.of(Shape.DIAMOND, Number.SIX)
        );
        Hand hand = new Hand(cards);
        Dealer dealer = new Dealer(hand);
        // when
        boolean isDrawable = dealer.hasDrawableScore();
        // then
        assertThat(isDrawable).isTrue();
    }

    @Test
    @DisplayName("딜러는 16점 초과이면 추가 드로우가 불가능하다.")
    void unableToDrawTest() {
        // given
        List<Card> cards = List.of(
                Card.of(Shape.HEART, Number.JACK),
                Card.of(Shape.DIAMOND, Number.SEVEN)
        );
        Hand hand = new Hand(cards);
        Dealer dealer = new Dealer(hand);
        // when
        boolean isDrawable = dealer.hasDrawableScore();
        // then
        assertThat(isDrawable).isFalse();
    }

    @Test
    @DisplayName("딜러의 첫 번째 카드를 가져온다.")
    void getFirstCardTest() {
        // given
        List<Card> cards = List.of(
                Card.of(Shape.HEART, Number.JACK),
                Card.of(Shape.DIAMOND, Number.SEVEN)
        );
        Hand hand = new Hand(cards);
        Dealer dealer = new Dealer(hand);
        // when, then
        List<Card> dealerCards = dealer.revealCardsOnFirstPhase();
        assertThat(dealerCards).containsExactlyElementsOf(
                List.of(Card.of(Shape.HEART, Number.JACK))
        );
    }

    @Test
    @DisplayName("딜러가 카드를 가지고 있지 않을 때 가져가는 것을 시도하면 예외를 발생시킨다.")
    void getFirstCardOnEmptyHandTest() {
        // given
        List<Card> cards = List.of();
        Hand hand = new Hand(cards);
        Dealer dealer = new Dealer(hand);
        // when, then
        assertThatThrownBy(dealer::revealCardsOnFirstPhase)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("[ERROR] 카드를 가지고 있지 않습니다.");
    }
}
