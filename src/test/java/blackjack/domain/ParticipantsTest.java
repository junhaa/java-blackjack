package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Shape;
import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.Hands;
import blackjack.domain.participants.Name;
import blackjack.domain.participants.Participants;
import blackjack.domain.participants.Player;
import blackjack.domain.participants.Players;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantsTest {

    private Player siso;
    private Player tacan;
    private Participants participants;

    @BeforeEach
    void beforeEach() {
        siso = new Player(new Name("시소"));
        tacan = new Player(new Name("타칸"));
        List<Player> playerList = List.of(siso, tacan);
        participants = new Participants(new Dealer(),  new Players(playerList));
    }

    @Test
    @DisplayName("딜러가 하나의 카드를 받는다.")
    void receiveDealerCardTest() {
        Card card = new Card(Shape.HEART, Rank.FIVE);

        participants.receiveDealerCard(card);

        assertThat(participants.getDealer().getHands().getCards().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("게임 참가자들이 초기 카드를 받는다")
    void initialDistributeTest() {
        Hands hands1 = new Hands(List.of(
                new Card(Shape.HEART, Rank.ACE),
                new Card(Shape.HEART, Rank.TWO))
        );

        Hands hands2 = new Hands(List.of(
                new Card(Shape.SPADE, Rank.ACE),
                new Card(Shape.SPADE, Rank.TWO))
        );

        Hands hands3 = new Hands(List.of(
                new Card(Shape.DIAMOND, Rank.ACE),
                new Card(Shape.DIAMOND, Rank.TWO))
        );

        participants.receiveInitialHands(
                new ArrayList<>(List.of(hands1, hands2, hands3))
        );

        assertThat(siso.getHands().getCards().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("딜러가 카드를 더 받을 수 있다.")
    void isDealerNotOverTest() {
        Hands hands1 = new Hands(List.of(
                new Card(Shape.HEART, Rank.JACK),
                new Card(Shape.HEART, Rank.SIX))
        );

        Hands hands2 = new Hands(List.of(
                new Card(Shape.SPADE, Rank.ACE),
                new Card(Shape.SPADE, Rank.TWO))
        );

        Hands hands3 = new Hands(List.of(
                new Card(Shape.DIAMOND, Rank.ACE),
                new Card(Shape.DIAMOND, Rank.TWO))
        );

        participants.receiveInitialHands(
                new ArrayList<>(List.of(hands1, hands2, hands3))
        );

        assertThat(participants.isDealerNotOver()).isTrue();
    }

    @Test
    @DisplayName("게임 참가자들의 수를 확인한다.")
    void countTest() {
        assertThat(participants.count()).isEqualTo(3);
    }

    @Test
    @DisplayName("게임 플레이어의 수를 확인한다.")
    void countPlayersTest() {
        assertThat(participants.countPlayers()).isEqualTo(2);
    }
}
