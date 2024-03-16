package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Shape;
import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.GameBoard;
import blackjack.domain.participants.Hands;
import blackjack.domain.participants.Name;
import blackjack.domain.participants.Player;
import blackjack.domain.participants.Players;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GameBoardTest {

    private Player siso;
    private Player takan;
    private Dealer dealer;
    private Players players;
    private GameBoard gameBoard;

    @BeforeEach
    void beforeEach() {
        siso = new Player(new Name("시소"));
        takan = new Player(new Name("타칸"));

        Hands sisoHands = new Hands(List.of(
                new Card(Shape.HEART, Rank.JACK),
                new Card(Shape.HEART, Rank.SIX))
        );

        Hands takanHands = new Hands(List.of(
                new Card(Shape.SPADE, Rank.ACE),
                new Card(Shape.SPADE, Rank.JACK))
        );

        siso.receiveHands(sisoHands);
        takan.receiveHands(takanHands);

        List<Player> playerList = List.of(siso, takan);
        players = new Players(playerList);

        dealer = new Dealer();
        gameBoard = new GameBoard(dealer, players);

    }

    @Test
    @DisplayName("52장 카드 생성한다.")
    void makeAllCardTest() {
        int result = gameBoard.getDeck().size();

        assertThat(result).isEqualTo(52);
    }

    @Test
    @DisplayName("플레이어의 수를 센다.")
    void countPlayerTest() {
        int result = gameBoard.countPlayers();

        assertThat(result).isEqualTo(2);
    }

    @Test
    @DisplayName("딜러가 카드를 더 가질 수 있다.")
    void isDealerNotOverTest() {
        dealer.hit(new Card(Shape.HEART, Rank.SEVEN));
        dealer.hit(new Card(Shape.HEART, Rank.SIX));

        boolean result = dealer.canHit();

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("초기에 딜러와 플레이어는 카드 두 장을 받는다.")
    void initialDistributeTest() {
        gameBoard.distributeInitialHands();

        assertThat(dealer.getHands().getCards().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("플레이어가 카드 한 장을 더 받는다.")
    void addCardToPlayerTest() {
        gameBoard.addCardToPlayer(siso);

        assertThat(siso.getHands().getCards().size()).isEqualTo(3);
    }

    @Test
    @DisplayName("딜러가 카드 한 장을 더 받는다.")
    void addCardToDealerTest() {
        gameBoard.addCardToDealer();

        assertThat(dealer.getHands().getCards().size()).isEqualTo(1);
    }
}
