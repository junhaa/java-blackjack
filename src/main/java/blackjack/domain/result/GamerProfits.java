package blackjack.domain.result;

import blackjack.domain.gamer.Gamer;
import blackjack.domain.gamer.Player;
import blackjack.dto.profit.GamerProfitState;
import blackjack.dto.profit.GamerProfitStates;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GamerProfits {
    private final Map<Player, Integer> playerProfits;
    private final int dealerProfit;

    public GamerProfits() {
        this(new HashMap<>(), 0);
    }

    public GamerProfits(final Map<Player, Integer> playerProfits, int dealerProfit) {
        this.playerProfits = new HashMap<>(playerProfits);
        this.dealerProfit = dealerProfit;
    }

    public int getProfit(final Gamer gamer) {
        return playerProfits.get(gamer);
    }

    public GamerProfitStates getGamerProfitStates() {
        List<GamerProfitState> profitState = new ArrayList<>();
        for (Map.Entry<Player, Integer> entry : playerProfits.entrySet()) {
            profitState.add(new GamerProfitState(entry.getKey().getName(), entry.getValue()));
        }

        return new GamerProfitStates(profitState, dealerProfit);
    }

    public int getDealerProfit() {
        return dealerProfit;
    }

    @Override
    public String toString() {
        return "GamerProfits{" +
                "playerProfits=" + playerProfits +
                ", dealerProfit=" + dealerProfit +
                '}';
    }
}
