package blackjack.model.money;

import blackjack.model.participant.Player;
import blackjack.model.result.ResultCommand;
import java.util.LinkedHashMap;
import java.util.Map;

public class Bets {
    private final Map<Player, BetMoney> bets;

    public Bets() {
        this.bets = new LinkedHashMap<>();
    }

    public void addBet(final Player player, final int money) {
        bets.put(player, new BetMoney(money));
    }

    public Map<Player, Profit> calculatePlayersProfit(final Map<Player, ResultCommand> result) {
        final Map<Player, Profit> playersProfit = new LinkedHashMap<>();
        for (Player player : result.keySet()) {
            final double rate = result.get(player).getRate();
            playersProfit.put(player, calculatePlayerProfit(rate, player));
        }
        return playersProfit;
    }

    private Profit calculatePlayerProfit(final double rate, final Player player) {
        int profitMoney = bets.get(player).multiply(rate);
        return new Profit(profitMoney);
    }

    public Profit calculateDealerProfit(final Map<Player, ResultCommand> result) {
        final Map<Player, Profit> playersProfit = calculatePlayersProfit(result);
        final int playerTotalProfit = playersProfit.values().stream()
                .mapToInt(Profit::getProfit)
                .sum();
        return new Profit(playerTotalProfit * -1);
    }
}
