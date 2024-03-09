package controller;

import domain.blackjack.DealerRandomCardDrawStrategy;
import domain.blackjack.GameResult;
import domain.blackjack.GameResultCalculator;
import domain.blackjack.Gamer;
import domain.blackjack.PlayerRandomCardDrawStrategy;
import domain.card.Card;
import domain.card.Deck;
import dto.DealerGameResultDTO;
import dto.GamerDTO;
import dto.PlayerGameResultDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import view.GameResultOutputView;
import view.GamerOutputView;
import view.OutputView;
import view.YesOrNoInputView;

public class BlackjackController {
    private static final int EXECUTION_COUNT = 2;

    private final Gamer dealer;
    private final List<Gamer> players;

    public BlackjackController(Gamer dealer, List<Gamer> players) {
        this.dealer = dealer;
        this.players = players;
    }

    public void startBlackjackGame(Deck deck) {
        initDealerAndPlayers(deck);
        printDealerAndPlayers();

        playersTryDraw(deck);
        dealerTryDraw(deck);

        printDealerAndPlayersWithPoint();
        printDealerAndPlayersGameResult();
    }

    private void initDealerAndPlayers(Deck deck) {
        dealerDraw(deck, EXECUTION_COUNT);
        players.forEach(player -> playerDraw(deck, player, EXECUTION_COUNT));

        List<String> playerNames = players.stream()
                .map(Gamer::getRawName)
                .toList();
        OutputView.printCardSplitMessage(dealer.getRawName(), playerNames);
    }

    private void dealerDraw(Deck deck) {
        dealer.draw(deck, new DealerRandomCardDrawStrategy(dealer));
    }

    private void dealerDraw(Deck deck, int execution_count) {
        for (int count = 1; count <= execution_count; count++) {
            dealer.draw(deck, new DealerRandomCardDrawStrategy(dealer));
        }
    }

    private void playerDraw(Deck deck, Gamer player) {
        player.draw(deck, new PlayerRandomCardDrawStrategy(player));
    }

    private void playerDraw(Deck deck, Gamer player, int execution_count) {
        for (int count = 1; count <= execution_count; count++) {
            player.draw(deck, new PlayerRandomCardDrawStrategy(player));
        }
    }

    private void printDealerAndPlayers() {
        printDealer(dealer);
        players.forEach(this::printPlayer);
    }

    private void printDealer(Gamer dealer) {
        List<Card> rawHoldingCards = new ArrayList<>(dealer.getRawHoldingCards());
        rawHoldingCards.remove(0);
        GamerDTO gamerDTO = new GamerDTO(dealer.getRawName(), rawHoldingCards,
                dealer.getRawSummationCardPoint());
        GamerOutputView.printWithoutSummationCardPoint(gamerDTO);
    }

    private void printPlayer(Gamer player) {
        GamerDTO gamerDTO = new GamerDTO(player.getRawName(), player.getRawHoldingCards(),
                player.getRawSummationCardPoint());
        GamerOutputView.printWithoutSummationCardPoint(gamerDTO);
    }

    private void playersTryDraw(Deck deck) {
        players.forEach(player -> playerTryDraw(deck, player));
    }

    private void playerTryDraw(Deck deck, Gamer player) {
        OutputView.printPlayerAdditionalCardMessage(player.getRawName());
        while (YesOrNoInputView.getYNAsBoolean() && playerTryDrawOnce(deck, player)) {
            OutputView.printPlayerAdditionalCardMessage(player.getRawName());
        }
    }

    private boolean playerTryDrawOnce(Deck deck, Gamer player) {
        try {
            playerDraw(deck, player);
            printPlayer(player);
        } catch (IllegalStateException ignored) {
            return false;
        }
        return true;
    }

    private void dealerTryDraw(Deck deck) {
        try {
            dealerDraw(deck);
            OutputView.printDealerAdditionalCardMessage();
        } catch (IllegalStateException ignored) {

        }
    }

    private void printDealerAndPlayersWithPoint() {
        GamerDTO dealerDTO = new GamerDTO(dealer.getRawName(), dealer.getRawHoldingCards(),
                dealer.getRawSummationCardPoint());
        GamerOutputView.print(dealerDTO);

        for (Gamer player : players) {
            GamerDTO playerDTO = new GamerDTO(player.getRawName(), player.getRawHoldingCards(),
                    player.getRawSummationCardPoint());
            GamerOutputView.print(playerDTO);
        }
    }

    private void printDealerAndPlayersGameResult() {
        Map<GameResult, Integer> dealerGameResultCounts = players.stream()
                .collect(Collectors.groupingBy(player -> GameResultCalculator.calculate(dealer, player),
                        Collectors.summingInt(value -> 1)));
        DealerGameResultDTO dealerGameResultDTO = new DealerGameResultDTO(dealerGameResultCounts);

        List<PlayerGameResultDTO> playerGameResultDTOS = players.stream()
                .map(player -> new PlayerGameResultDTO(player.getRawName(),
                        GameResultCalculator.calculate(player, dealer)))
                .toList();

        OutputView.printGameResultMessage();
        GameResultOutputView.print(dealerGameResultDTO);
        playerGameResultDTOS.forEach(GameResultOutputView::print);
    }
}
