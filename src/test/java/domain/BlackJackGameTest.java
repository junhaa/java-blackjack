package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Hand;
import domain.card.cardinfo.CardNumber;
import domain.card.cardinfo.CardShape;
import domain.gamer.Dealer;
import domain.gamer.Player;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlackJackGameTest {

    @DisplayName("본 게임 시작 전 게이머 모두에게 2장의 카드를 나눠준다.")
    @Test
    void shareInitCardsBeforeGame() {
        BlackJackGame game = new BlackJackGame(List.of("p1", "p2", "p3"));

        game.setUpGame();

        Dealer dealer = game.getGamers().getDealer();
        List<Player> players = game.getGamers().getPlayers();
        assertThat(dealer.getCards()).hasSize(2);
        players.forEach(player -> assertThat(player.getCards()).hasSize(2));
    }

    @DisplayName("플레이어가 카드를 더 받을 경우 플레이어의 카드 수는 1 증가한다.")
    @Test
    void increaseCardSizeWhenPlayerHit() {
        BlackJackGame game = new BlackJackGame(List.of("dummy"));
        Hand hand = new Hand();
        hand.addCard(new Card(CardNumber.THREE, CardShape.HEART));
        hand.addCard(new Card(CardNumber.FOUR, CardShape.DIAMOND));
        hand.addCard(new Card(CardNumber.FIVE, CardShape.CLOVER));
        Player player = new Player("p1", hand);

        game.hitByPlayer(player);

        assertThat(player.getCards()).hasSize(3 + 1);
    }

    @DisplayName("딜러가 카드를 더 받을 경우 딜러의 카드 수는 1 증가한다.")
    @Test
    void increaseCardSizeWhenDealerHit() {
        BlackJackGame game = new BlackJackGame(List.of("dummy"));
        Hand hand = new Hand();
        hand.addCard(new Card(CardNumber.THREE, CardShape.HEART));
        hand.addCard(new Card(CardNumber.FOUR, CardShape.DIAMOND));
        hand.addCard(new Card(CardNumber.FIVE, CardShape.CLOVER));
        Dealer dealer = new Dealer(hand);

        game.hitByDealer(dealer);

        assertThat(dealer.getCards()).hasSize(3 + 1);
    }
}
