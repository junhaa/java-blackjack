package domain.result;

import domain.gamer.Dealer;
import domain.gamer.Name;
import domain.gamer.Player;
import domain.gamer.Players;
import domain.money.Money;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

public class BettingResult {

    private final Map<Name, Money> playersResult;
    private final Money dealerResult;

    public BettingResult(Dealer dealer, Players players) {
        dealerResult = calculateDealerResult(dealer, players);
        playersResult = calculatePlayersResult(dealer, players);
    }

    private Money calculateDealerResult(Dealer dealer, Players players) {
        return players.getPlayers().stream()
                .filter(player -> GamerResult.DRAW != player.judge(dealer))
                .map(player -> player.bet(dealer))
                .map(Money::negative)
                .reduce(Money.ZERO, Money::add);
    }

    private Map<Name, Money> calculatePlayersResult(Dealer dealer, Players players) {
        return players.getPlayers().stream()
                .collect(Collectors.toMap(Player::getName, player -> player.bet(dealer)));
    }

    public Money getDealerResult() {
        return dealerResult;
    }

    public Map<Name, Money> getPlayersResult() {
        return Collections.unmodifiableMap(playersResult);
    }
}
