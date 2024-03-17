package controller;

import domain.gamer.Dealer;
import domain.gamer.Player;
import domain.gamer.Players;
import domain.gamer.PlayersCreator;
import domain.gamer.Referee;
import dto.AllPlayerResultDto;
import dto.DealerResultDto;
import dto.InitCardDto;
import dto.PlayerCardStateDto;
import java.util.List;
import view.InputView;
import view.OutputView;

public class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        List<String> inputPlayerNames = inputView.inputPlayerNames();
        List<Integer> inputMoneys = inputView.inputMoney(inputPlayerNames);
        Players players = new PlayersCreator().create(inputPlayerNames, inputMoneys);
        Dealer dealer = new Dealer();

        players.initCard(dealer);
        showInitialCard(players, dealer);
        playGame(players, dealer);
        showResult(players, dealer);
    }

    private void showInitialCard(Players players, Dealer dealer) {
        outputView.printInitCard(InitCardDto.makeInitCard(players, dealer));
    }

    private void playGame(Players players, Dealer dealer) {
        players.play(this::playTurn, dealer);
        while (dealer.shouldDraw()) {
            dealer.receiveCard();
            outputView.printDealerAddCard();
        }
    }

    private void playTurn(Player player, Dealer dealer) {
        while (player.isDrawable() && inputView.inputPlayerCommand(player.getName())) {
            player.receiveCard();
            outputView.printCardsStatus(PlayerCardStateDto.makePlayerCardState(player));
        }
    }

    private void showResult(Players players, Dealer dealer) {
        Referee referee = new Referee();
        outputView.printDealerScore(DealerResultDto.makeDealerResultDto(dealer));
        outputView.printPlayersScore(AllPlayerResultDto.makeAllPlayerResultDto(players));
        outputView.printResult(players.makeResult(dealer, referee));
    }
}
