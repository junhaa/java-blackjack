package blackjack;

import blackjack.domain.gamer.Name;
import blackjack.domain.result.Bettings;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.result.Money;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import blackjack.domain.card.ShuffledDeckFactory;
import blackjack.dto.DealerDto;
import blackjack.dto.PlayerDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Application {

    public static void main(String[] args) {
        final Players players = Players.from(InputView.readPlayerNames());
        final Dealer dealer = Dealer.from(new ShuffledDeckFactory().create());
        final Bettings bettings = createBettings(players);

        drawInitialHands(players, dealer);
        hitGamers(players, dealer);

        OutputView.printFinalState(createDealerDto(dealer.getHand()), createPlayerDtos(players));
        OutputView.printFinalProfits(bettings.calculateDealerProfit(dealer), bettings.calculatePlayerProfits(dealer));
    }

    private static Bettings createBettings(final Players players) {
        final Map<Player, Money> bettings = new LinkedHashMap<>();
        for (final Name playerName : players.names()) {
            final Money betting = new Money(InputView.readBettingMoney(playerName));
            bettings.put(players.findBy(playerName), betting);
        }
        return new Bettings(bettings);
    }

    private static void drawInitialHands(final Players players, final Dealer dealer) {
        players.drawInitialHand(dealer);
        dealer.drawInitialHand();

        final DealerDto dealerDto = createDealerDto(dealer.openFirstCard());
        final List<PlayerDto> playerDtos = createPlayerDtos(players);
        OutputView.printInitialState(dealerDto, playerDtos);
    }

    private static DealerDto createDealerDto(final Card card) {
        return createDealerDto(new Hand(List.of(card)));
    }

    private static DealerDto createDealerDto(final Hand hand) {
        return new DealerDto(hand.getCards(), hand.calculateOptimalSum());
    }

    private static List<PlayerDto> createPlayerDtos(final Players players) {
        return players.getPlayers().stream()
                .map(Application::createPlayerDto)
                .toList();
    }

    private static PlayerDto createPlayerDto(final Player player) {
        return new PlayerDto(player.getName(), player.getHand().getCards(), player.calculateScore());
    }

    private static void hitGamers(final Players players, final Dealer dealer) {
        hitPlayers(players, dealer);
        OutputView.printLineSeparator();
        hitDealer(dealer);
        OutputView.printLineSeparator();
    }

    private static void hitPlayers(final Players players, final Dealer dealer) {
        for (final Name playerName : players.names()) {
            hitPlayer(players, playerName, dealer);
        }
    }

    private static void hitPlayer(final Players players, final Name playerName, final Dealer dealer) {
        while (players.canDraw(playerName) && InputView.readDoesWantHit(playerName)) {
            players.draw(dealer.drawPlayerCard(), playerName);
            OutputView.printCurrentState(createPlayerDto(players.findBy(playerName)));
        }
    }

    private static void hitDealer(final Dealer dealer) {
        while (dealer.canDraw()) {
            dealer.draw();
            OutputView.printDealerDrawMessage();
        }
    }
}
