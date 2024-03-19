package blackjack.model.betting;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.player.MatchResult;
import blackjack.model.player.PlayerName;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BettingTest {
    private PlayerName playerName;
    private Betting betting;

    @BeforeEach
    void setUp() {
        playerName = new PlayerName("dora");
        Map<PlayerName, BettingMoney> playerBettingMoney = new HashMap<>();
        playerBettingMoney.put(playerName, new BettingMoney(1000));
        betting = new Betting(playerBettingMoney);
    }

    @Test
    @DisplayName("승리한 플레이어는 베팅 금액의 1배를 추가로 받는다")
    void calculateWinningPlayerProfitTest() {
        // when
        int profit = betting.calculatePlayerBettingProfit(playerName, MatchResult.WIN);

        // then
        assertThat(profit).isEqualTo(1000);
    }

    @Test
    @DisplayName("패배한 플레이어는 베팅 금액을 잃는다")
    void calculateLosingPlayerProfitTest() {
        // when
        int profit = betting.calculatePlayerBettingProfit(playerName, MatchResult.LOSE);

        // then
        assertThat(profit).isEqualTo(-1000);
    }

    @Test
    @DisplayName("무승부인 플레이어는 베팅 금액을 돌려받는다")
    void calculatePushPlayerProfitTest() {
        // when
        int profit = betting.calculatePlayerBettingProfit(playerName, MatchResult.PUSH);

        // then
        assertThat(profit).isEqualTo(0);
    }

    @Test
    @DisplayName("블랙잭 승리한 플레이어는 베팅 금액의 1.5배를 추가로 받는다")
    void calculateBlackjackWinningPlayerProfitTest() {
        // when
        int profit = betting.calculatePlayerBettingProfit(playerName, MatchResult.BLACKJACK_WIN);

        // then
        assertThat(profit).isEqualTo(1500);
    }
}
