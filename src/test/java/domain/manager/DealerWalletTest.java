package domain.manager;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Hand;
import domain.gamer.Player;
import domain.manager.wallet.DealerWallet;
import domain.manager.wallet.PlayersWallet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerWalletTest {

    @DisplayName("플레이어의 수익을 바탕으로 딜러의 수익을 계산한다.")
    @Test
    void calculateProfit() {
        Player player1 = new Player("p1", new Hand());
        Player player2 = new Player("p2", new Hand());
        Player player3 = new Player("p3", new Hand());
        Player player4 = new Player("p4", new Hand());
        PlayersWallet playersWallet = new PlayersWallet();
        playersWallet.addProfit(player1, new Profit(10));
        playersWallet.addProfit(player2, new Profit(10));
        playersWallet.addProfit(player3, new Profit(10));
        playersWallet.addProfit(player4, new Profit(10));
        playersWallet.calculateProfit(player1, GameResult.WIN);
        playersWallet.calculateProfit(player2, GameResult.LOSE);
        playersWallet.calculateProfit(player3, GameResult.DRAW);
        playersWallet.calculateProfit(player4, GameResult.BLACKJACK);

        DealerWallet dealerWallet = new DealerWallet();
        dealerWallet.calculateProfit(playersWallet);

        assertThat(dealerWallet.getProfit().getValue()).isEqualTo(-15);
    }
}
