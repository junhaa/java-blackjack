package blackjack.domain.participant;

import blackjack.domain.Deck;
import blackjack.domain.card.Card;
import blackjack.domain.stategy.NoShuffleStrategy;
import blackjack.fixture.CardFixture;
import blackjack.fixture.PlayerFixture;
import blackjack.strategy.shuffle.ShuffleStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("플레이어")
public class PlayerTest {

    private final ShuffleStrategy shuffleStrategy = new NoShuffleStrategy();
    private Dealer dealer;
    private Player choco;
    private Player clover;

    @BeforeEach
    void setUp() {
        Deck deck = new Deck(shuffleStrategy);
        dealer = new Dealer(deck);

        choco = PlayerFixture.CHOCO.getPlayer();
        clover = PlayerFixture.CLOVER.getPlayer();
    }

    @DisplayName("사용자의 이름이 형식에 맞지 않으면 예외가 발생한다.")
    @Test
    void validateName() {
        // given
        String name = "noValidName123";

        // when & then
        assertThatThrownBy(() -> new Player(name, dealer))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("버스트 되지 않은 플레이어는 카드를 한장 더 뽑을 수 있다.")
    @Test
    void canReceiveCard() {
        // when
        choco.draw(dealer);

        // then
        assertThat(choco.canReceiveCard()).isTrue();
    }

    @DisplayName("버스트 된 플레이어는 카드를 한장 더 뽑을 수 없다.")
    @Test
    void cantReceiveCard() {
        // when
        for (int i = 0; i < 6; i++) {
            clover.draw(dealer);
        }

        // then
        assertThat(clover.canReceiveCard()).isFalse();
    }

    @DisplayName("플레이어는 한 장을 뽑아서 손패에 넣는다.")
    @Test
    void draw() {
        // given
        Card card = CardFixture.THREE_SPADE_CARD.getCard();

        // when
        choco.draw(dealer);

        // then
        assertThat(choco.getHandCards()).contains(card);
    }
}
