import blackjack.domain.player.BettingAmounts;
import blackjack.domain.Blackjack;
import blackjack.domain.card.Card;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.GamePlayer;
import blackjack.domain.player.Names;
import blackjack.domain.player.Players;
import blackjack.domain.result.Result;
import blackjack.view.BlackjackCommand;
import blackjack.view.InputView;
import blackjack.view.PlayerView;
import blackjack.view.ResultView;

public class BlackjackController {
    private final Blackjack blackjack;

    public BlackjackController(Blackjack blackjack) {
        this.blackjack = blackjack;
    }

    public void playBlackJack() {
        Players players = joinPlayer();
        processGame(players);
        printGameResult(players);
    }

    private Players joinPlayer() {
        Names names = Names.from(InputView.inputPlayerNames());
        BettingAmounts bettingAmounts = BettingAmounts.from(InputView.inputPlayerBattingAmounts(names));
        Players players = blackjack.createPlayers(names.getNames(), bettingAmounts.getBettingAmounts());
        PlayerView.printPlayers(players);
        return players;
    }

    private void processGame(Players players) {
        players.getGamePlayers()
                .forEach(this::processGamePlayer);
        processDealer(players.getDealer());
        PlayerView.printPlayersWithScore(players);
    }

    private void processGamePlayer(GamePlayer gamePlayer) {
        while (gamePlayer.isReceivable() && isHit(gamePlayer)) {
            blackjack.drawCard(gamePlayer);
            PlayerView.printPlayerOpenCards(gamePlayer);
        }
    }

    private boolean isHit(GamePlayer gamePlayer) {
        BlackjackCommand command = InputView.inputBlackjackCommand(gamePlayer.getName());
        return command.isHit();
    }

    private void processDealer(Dealer dealer) {
        while (dealer.isReceivable()) {
            blackjack.drawCard(dealer);
            PlayerView.printDealerDrawMessage();
        }
        PlayerView.printDealerNotDrawMessage();
    }

    private void printGameResult(Players players) {
        Result result = players.compareResults();
        ResultView.printResult(result);
    }
}
