package domain.judge;

import domain.gamer.Dealer;
import domain.gamer.Player;
import domain.gamer.Players;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class Judge {

    private final Map<Player, WinState> playerResult;
    private final Map<WinState, Integer> dealerResult;

    public Judge() {
        this.playerResult = new LinkedHashMap<>();
        this.dealerResult = new LinkedHashMap<>();
    }

    public void decideResult(Players players, Dealer dealer) {
        for (Player player : players.getPlayers()) {
            decidePlayerResult(player, dealer);
        }
        decideDealerResult();
    }

    private void decidePlayerResult(Player player, Dealer dealer) {
        if (player.isBust()) {
            playerResult.put(player, WinState.LOSE);
            return;
        }
        if (dealer.isBust()) {
            playerResult.put(player, WinState.WIN);
            return;
        }
        playerResult.put(player, judgePlayerWinState(player, dealer));
    }

    private WinState judgePlayerWinState(Player player, Dealer dealer) {
        int playerScore = player.finalizeCardsScore();
        int dealerScore = dealer.finalizeCardsScore();

        if (playerScore > dealerScore) {
            return WinState.WIN;
        }
        if (playerScore < dealerScore) {
            return WinState.LOSE;
        }
        return WinState.DRAW;
    }

    private void decideDealerResult() {
        dealerResult.put(WinState.WIN, countState(WinState.LOSE));
        dealerResult.put(WinState.DRAW, countState(WinState.DRAW));
        dealerResult.put(WinState.LOSE, countState(WinState.WIN));
    }

    private int countState(WinState winState) {
        return (int) playerResult.values().stream()
                .filter(value -> value.equals(winState))
                .count();
    }

    public Map<Player, WinState> getPlayerResult() {
        return Collections.unmodifiableMap(playerResult);
    }

    public Map<WinState, Integer> getDealerResult() {
        return Collections.unmodifiableMap(dealerResult);
    }
}
