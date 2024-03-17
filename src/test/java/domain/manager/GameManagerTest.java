package domain.manager;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Hand;
import domain.card.cardinfo.CardNumber;
import domain.card.cardinfo.CardShape;
import domain.gamer.Dealer;
import domain.gamer.Gamers;
import domain.gamer.Player;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameManagerTest {

    private GameManager gameManager;

    @BeforeEach
    void setUp() {
        gameManager = new GameManager();
    }

    @DisplayName("플레이어가 bust 일 경우 플레이어의 패배로 판단한다.")
    @Test
    void judgePlayerLoseWhenPlayerBust() {
        Player player = new Player("p", new Hand());
        player.hit(new Card(CardNumber.KING, CardShape.SPADE));
        player.hit(new Card(CardNumber.KING, CardShape.HEART));
        player.hit(new Card(CardNumber.KING, CardShape.CLOVER));
        Dealer dealer = new Dealer(new Hand());
        dealer.hit(new Card(CardNumber.FIVE, CardShape.HEART));
        dealer.hit(new Card(CardNumber.FIVE, CardShape.CLOVER));
        Gamers gamers = new Gamers(List.of(player), dealer);
        gameManager.initializeProfit(player, new Profit(10));

        gameManager.decideResult(gamers);

        assertThat(gameManager.findProfitOfPlayer(player).getValue()).isEqualTo(-10);
    }

    @DisplayName("딜러가 bust 일 경우 플레이어의 승리로 판단한다.")
    @Test
    void judgePlayerWinWhenDealerBust() {
        Player player = new Player("p", new Hand());
        player.hit(new Card(CardNumber.FIVE, CardShape.HEART));
        Dealer dealer = new Dealer(new Hand());
        dealer.hit(new Card(CardNumber.KING, CardShape.SPADE));
        dealer.hit(new Card(CardNumber.KING, CardShape.HEART));
        dealer.hit(new Card(CardNumber.KING, CardShape.CLOVER));
        Gamers gamers = new Gamers(List.of(player), dealer);
        gameManager.initializeProfit(player, new Profit(10));

        gameManager.decideResult(gamers);

        assertThat(gameManager.findProfitOfPlayer(player).getValue()).isEqualTo(10);
    }

    @DisplayName("플레이어와 딜러 모두 bust 가 아닌 경우 플레이어가 높은 점수를 가지면 플레이어의 승리로 판단한다.")
    @Test
    void judgePlayerWinWhenNotBustHigherScore() {
        Player player = new Player("p", new Hand());
        player.hit(new Card(CardNumber.KING, CardShape.SPADE));
        Dealer dealer = new Dealer(new Hand());
        dealer.hit(new Card(CardNumber.TWO, CardShape.SPADE));
        Gamers gamers = new Gamers(List.of(player), dealer);
        gameManager.initializeProfit(player, new Profit(10));

        gameManager.decideResult(gamers);

        assertThat(gameManager.findProfitOfPlayer(player).getValue()).isEqualTo(10);
    }

    @DisplayName("플레이어와 딜러 모두 bust 가 아닌 경우 점수가 같다면 무승부로 판단한다.")
    @Test
    void judgePlayerDrawWhenNotBustSameScore() {
        Player player = new Player("p", new Hand());
        player.hit(new Card(CardNumber.FIVE, CardShape.SPADE));
        Dealer dealer = new Dealer(new Hand());
        dealer.hit(new Card(CardNumber.FIVE, CardShape.CLOVER));
        Gamers gamers = new Gamers(List.of(player), dealer);
        gameManager.initializeProfit(player, new Profit(10));

        gameManager.decideResult(gamers);

        assertThat(gameManager.findProfitOfPlayer(player).getValue()).isEqualTo(0);
    }

    @DisplayName("플레이어와 딜러 모두 bust 가 아닌 경우 딜러가 높은 점수를 가지면 플레이어의 패배로 판단한다.")
    @Test
    void judgePlayerLoseWhenNotBustLowerScore() {
        Player player = new Player("p", new Hand());
        player.hit(new Card(CardNumber.FIVE, CardShape.SPADE));
        Dealer dealer = new Dealer(new Hand());
        dealer.hit(new Card(CardNumber.KING, CardShape.CLOVER));
        Gamers gamers = new Gamers(List.of(player), dealer);
        gameManager.initializeProfit(player, new Profit(10));

        gameManager.decideResult(gamers);

        assertThat(gameManager.findProfitOfPlayer(player).getValue()).isEqualTo(-10);
    }

    @DisplayName("N명의 플레이어가 패배한 경우 딜러는 N번의 승리를 얻는다.")
    @Test
    void judgeDealerWinWhenPlayerLose() {
        Player player1 = new Player("p1", new Hand());
        player1.hit(new Card(CardNumber.TWO, CardShape.SPADE));
        gameManager.initializeProfit(player1, new Profit(10));
        Player player2 = new Player("p2", new Hand());
        player2.hit(new Card(CardNumber.THREE, CardShape.SPADE));
        gameManager.initializeProfit(player2, new Profit(10));
        Player player3 = new Player("p3", new Hand());
        player3.hit(new Card(CardNumber.FOUR, CardShape.SPADE));
        gameManager.initializeProfit(player3, new Profit(10));
        Dealer dealer = new Dealer(new Hand());
        dealer.hit(new Card(CardNumber.KING, CardShape.CLOVER));
        Gamers gamers = new Gamers(List.of(player1, player2, player3), dealer);

        gameManager.decideResult(gamers);

        assertThat(gameManager.getDealerProfit().getValue()).isEqualTo(30);
    }

    @DisplayName("N명의 플레이어가 승리한 경우 딜러는 N번의 패배를 얻는다.")
    @Test
    void judgeDealerLoseWhenPlayerWin() {
        Player player1 = new Player("p1", new Hand());
        player1.hit(new Card(CardNumber.JACK, CardShape.SPADE));
        gameManager.initializeProfit(player1, new Profit(10));
        Player player2 = new Player("p2", new Hand());
        player2.hit(new Card(CardNumber.QUEEN, CardShape.SPADE));
        gameManager.initializeProfit(player2, new Profit(10));
        Player player3 = new Player("p3", new Hand());
        player3.hit(new Card(CardNumber.KING, CardShape.SPADE));
        gameManager.initializeProfit(player3, new Profit(10));
        Dealer dealer = new Dealer(new Hand());
        dealer.hit(new Card(CardNumber.FIVE, CardShape.CLOVER));
        Gamers gamers = new Gamers(List.of(player1, player2, player3), dealer);

        gameManager.decideResult(gamers);

        assertThat(gameManager.getDealerProfit().getValue()).isEqualTo(-30);
    }

    @DisplayName("N명의 플레이어가 무승부인 경우 딜러는 N번의 무승부를 얻는다.")
    @Test
    void judgeDealerDrawWhenPlayerDraw() {
        Player player1 = new Player("p1", new Hand());
        player1.hit(new Card(CardNumber.KING, CardShape.HEART));
        gameManager.initializeProfit(player1, new Profit(10));
        Player player2 = new Player("p2", new Hand());
        player2.hit(new Card(CardNumber.KING, CardShape.DIAMOND));
        gameManager.initializeProfit(player2, new Profit(10));
        Player player3 = new Player("p3", new Hand());
        player3.hit(new Card(CardNumber.KING, CardShape.SPADE));
        gameManager.initializeProfit(player3, new Profit(10));
        Dealer dealer = new Dealer(new Hand());
        dealer.hit(new Card(CardNumber.KING, CardShape.CLOVER));
        Gamers gamers = new Gamers(List.of(player1, player2, player3), dealer);

        gameManager.decideResult(gamers);

        assertThat(gameManager.getDealerProfit().getValue()).isEqualTo(0);
    }
}
