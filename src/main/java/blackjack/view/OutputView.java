package blackjack.view;

import blackjack.domain.BetManager;
import blackjack.domain.GameResult;
import blackjack.domain.Result;
import blackjack.domain.deck.Card;
import blackjack.domain.participant.BetMoney;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private static final String ACCEPT_DRAW_MESSAGE = "y";
    private static final String REJECT_DRAW_MESSAGE = "n";
    private static final String ASK_DRAW_MESSAGE = "는/은 한장의 카드를 더 받겠습니까?(예는 "
            + ACCEPT_DRAW_MESSAGE + ", 아니오는 " + REJECT_DRAW_MESSAGE + ")";
    private static final String FINAL_HANDS_AND_SCORE_FORMAT = "%s - 결과: %d";
    private static final String FINAL_RESULT_FORMAT = "%s: %s" + System.lineSeparator();
    private static final String FINAL_RESULT_MESSAGE = System.lineSeparator() + "## 최종 승패";
    private static final String FINAL_PROFIT_RESULT_MESSAGE = System.lineSeparator() + "## 최종 수익";
    private static final String FINAL_PROFIT_RESULT_FORMAT = "%s: %s" + System.lineSeparator();

    private static final int DEALER_DRAW_THRESHOLD = 16;

    private OutputView() {
    }

    public static void printAskNameMessage() {
        System.out.println("게임에 참여 할사람 이름을 입력하세요.(쉽표 기준으로 분리)");
    }

    public static void printDrawInitialHandsMessage(Dealer dealer, Players players) {
        System.out.println(System.lineSeparator()
                + dealer.getName()
                + "와 "
                + resolvePlayerNamesMessage(players)
                + "에게 2장을 나누었습니다");
    }

    public static void printParticipantsInitialHands(Dealer dealer, Players players) {
        printDealerFirstCard(dealer);
        for (Player player : players.getPlayers()) {
            printPlayerHands(player);
        }
        System.out.println();
    }

    public static void printPlayerHands(Player player) {
        System.out.println(resolvePlayerHandsMessage(player));
    }

    public static void printAskDrawMessage(String playerName) {
        System.out.println(playerName + ASK_DRAW_MESSAGE);
    }

    public static void printDealerDrawMessage(String name) {
        System.out.println(System.lineSeparator() + name + "는 "
                + DEALER_DRAW_THRESHOLD + "이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printFinalHandsAndScoreMessage(Dealer dealer, Players players) {
        System.out.println();
        System.out.println(resolveDealerFinalHandsAndScoreMessage(dealer));
        for (Player player : players.getPlayers()) {
            System.out.println(resolvePlayerFinalHandsAndScoreMessage(player));
        }
    }

    public static void printGameResult(Dealer dealer, GameResult gameResult) {
        System.out.println(FINAL_RESULT_MESSAGE);
        printDealerGameResult(dealer, gameResult);
        printPlayerGameResult(gameResult.getGameResult());
    }

    public static void printProfitResult(Dealer dealer, Players players, GameResult gameResult, BetManager betManager) {
        System.out.println(FINAL_PROFIT_RESULT_MESSAGE);
        System.out.printf(FINAL_PROFIT_RESULT_FORMAT, dealer.getName(), gameResult.calculateDealerProfit(betManager));
        for (Player player : players.getPlayers()) {
            BetMoney betMoney = betManager.findPlayerBetMoney(player);
            System.out.printf(FINAL_PROFIT_RESULT_FORMAT, player.getName(),
                    gameResult.calculatePlayerProfit(player, betMoney));
        }
    }

    private static String resolvePlayerNamesMessage(Players players) {
        return players.getPlayers()
                .stream()
                .map(Player::getName)
                .collect(Collectors.joining(","));
    }

    private static void printDealerFirstCard(Dealer dealer) {
        System.out.println(dealer.getName()
                + ": "
                + dealer.getFirstCardName());
    }

    private static String resolvePlayerFinalHandsAndScoreMessage(Player player) {
        return String.format(FINAL_HANDS_AND_SCORE_FORMAT
                , resolvePlayerHandsMessage(player)
                , player.findHandsScore());
    }

    private static String resolveDealerFinalHandsAndScoreMessage(Dealer dealer) {
        return String.format(FINAL_HANDS_AND_SCORE_FORMAT
                , resolveDealerHandsMessage(dealer)
                , dealer.findHandsScore());
    }

    private static void printDealerGameResult(Dealer dealer, GameResult gameResult) {
        String dealerResultMessage = resolveDealerResultMessage(gameResult, Result.WIN)
                + resolveDealerResultMessage(gameResult, Result.LOSE)
                + resolveDealerResultMessage(gameResult, Result.DRAW);

        System.out.printf(FINAL_RESULT_FORMAT, dealer.getName(), dealerResultMessage);
    }

    private static void printPlayerGameResult(Map<Player, Result> gameResult) {
        for (Player player : gameResult.keySet()) {
            System.out.printf(FINAL_RESULT_FORMAT, player.getName()
                    , gameResult.get(player).getResult());
        }
    }

    private static String resolveDealerResultMessage(GameResult gameResult, Result result) {
        long cnt = gameResult.findTargetResultCount(Result.reverseResult(result));
        if (cnt == 0) {
            return "";
        }
        return cnt + result.getResult() + " ";
    }

    private static String resolvePlayerHandsMessage(Player player) {
        return player.getName()
                + "카드: "
                + resolveHandsMessage(player.getHandsCards());
    }

    private static String resolveDealerHandsMessage(Dealer dealer) {
        return dealer.getName()
                + "카드: "
                + resolveHandsMessage(dealer.getHandsCards());
    }

    private static String resolveHandsMessage(List<Card> hands) {
        return hands.stream()
                .map(Card::getCardName)
                .collect(Collectors.joining(", "));
    }
}
