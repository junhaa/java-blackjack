package blackjack.view;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.GamePlayer;
import blackjack.domain.player.Name;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerView {

    public static void printPlayers(Players players) {
        printPlayersPreview(players.getDealer(), players.getGamePlayers());
        printPlayersFirstOpenCard(players.getPlayers());
        System.out.println();
    }

    public static void printDealerDrawMessage() {
        System.out.println(System.lineSeparator() + String.format("딜러는 %d이하라 한장의 카드를 더 받았습니다.",
                Dealer.RECEIVE_SIZE));
    }

    public static void printDealerNotDrawMessage() {
        System.out.println(System.lineSeparator() + String.format("딜러는 %d를 초과하므로 카드를 받지 않았습니다.",
                Dealer.RECEIVE_SIZE));
    }

    private static void printPlayersPreview(Dealer dealer, List<GamePlayer> gamePlayers) {
        String result = gamePlayers.stream()
                                   .map(GamePlayer::getName)
                                   .map(Name::getValue)
                                   .collect(Collectors.joining(","));
        System.out.println(
                System.lineSeparator() +
                        String.format("%s와 %s에게 2장을 나누었습니다.", dealer.getName()
                                                                    .getValue(), result));
    }

    private static void printPlayersFirstOpenCard(List<Player> players) {
        players.forEach(PlayerView::printPlayerOpenCards);
    }

    public static void printPlayerOpenCards(Player player) {
        String result = CardPrinter.printCards(player.getOpenCards());
        System.out.println(String.format("%s카드: %s", player.getName()
                                                           .getValue(), result));
    }

    public static void printPlayersWithScore(Players players) {
        System.out.println();
        printPlayerWithScore(players.getDealer());
        players.getGamePlayers()
               .forEach(PlayerView::printPlayerWithScore);
    }

    private static void printPlayerWithScore(Player player) {
        String result = CardPrinter.printCards(player.getCards());
        System.out.println(String.format("%s 카드: %s - 결과 : %d", player.getName()
                                                                      .getValue(), result,
                player.calculateScore()));
    }

}
