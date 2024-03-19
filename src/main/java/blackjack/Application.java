package blackjack;

import blackjack.domain.GameBoard;
import blackjack.domain.card.Deck;
import blackjack.domain.card.ShuffledDeckFactory;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Gamers;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import blackjack.dto.DealerDto;
import blackjack.dto.PlayerDto;
import blackjack.dto.PlayersDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class Application {

    public static void main(String[] args) {
        final GameBoard gameBoard = createGameBoard();
        drawInitialCard(gameBoard);

        hit(gameBoard);
        OutputView.printFinalState(DealerDto.allCardToDto(gameBoard.getDealer()),
                PlayersDto.toDto(gameBoard.getPlayers()));

        OutputView.printTotalProfits(gameBoard.getDealerProfit(), gameBoard.getPlayersProfit());
    }

    private static GameBoard createGameBoard() {
        final Deck deck = new Deck(new ShuffledDeckFactory());
        final Dealer dealer = new Dealer();

        List<String> playerNames = InputView.readPlayerNames();
        final Players players = new Players(preparePlayers(playerNames));

        final Gamers gamers = new Gamers(dealer, players);
        return new GameBoard(deck, gamers);
    }

    private static List<Player> preparePlayers(List<String> playerNames) {
        return playerNames.stream()
                .map(name -> new Player(name, InputView.readPlayerBetAmount(name)))
                .toList();
    }

    private static void drawInitialCard(final GameBoard gameBoard) {
        gameBoard.drawInitialCards();
        OutputView.printInitialState(DealerDto.firstCardToDto(gameBoard.getDealer()),
                PlayersDto.toDto(gameBoard.getPlayers()));
    }

    private static void hit(final GameBoard gameBoard) {
        hitPlayers(gameBoard);
        hitDealer(gameBoard);
    }

    private static void hitPlayers(final GameBoard gameBoard) {
        for (final Player player : gameBoard.getPlayers()) {
            hitPlayer(gameBoard, player);
        }
    }

    private static void hitPlayer(final GameBoard gameBoard, final Player player) {
        while (gameBoard.canHit(player) && InputView.readDoesWantHit(player.getName())) {
            gameBoard.hit(player);
            OutputView.printCurrentState(PlayerDto.toDto(player));
        }
    }

    private static void hitDealer(final GameBoard gameBoard) {
        while (gameBoard.canHit(gameBoard.getDealer())) {
            gameBoard.hit(gameBoard.getDealer());
            OutputView.printDealerDrawMessage();
        }
    }
}
