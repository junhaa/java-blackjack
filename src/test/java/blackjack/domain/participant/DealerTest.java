package blackjack.domain.participant;

import static blackjack.fixture.TrumpCardFixture.aceSpadeTrumpCard;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.Deck;
import blackjack.domain.card.Card;
import blackjack.domain.stategy.NoShuffleStrategy;
import blackjack.strategy.ShuffleStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("딜러")
public class DealerTest {

    private final ShuffleStrategy shuffleStrategy = new NoShuffleStrategy();

    private Deck deck;
    private Dealer dealer;
    private final Card trumpCardAceSpade = aceSpadeTrumpCard();

    @BeforeEach
    void setUp() {
        dealer = Dealer.from(shuffleStrategy);
    }

    @DisplayName("딜러는 한 장을 뽑아서 손패에 넣는다.")
    @Test
    void draw() {
        //given & when
        dealer.draw(3);

        //then
        assertThat(dealer.getHandCards()).contains(trumpCardAceSpade);
    }

    @DisplayName("딜러의 첫번째 카드를 공개한다.")
    @Test
    void showFirstCard() {
        //given
        dealer.draw(2);

        //when & then
        assertThat(dealer.showFirstCard()).isEqualTo(trumpCardAceSpade);
    }
}
