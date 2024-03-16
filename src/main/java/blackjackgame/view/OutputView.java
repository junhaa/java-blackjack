package blackjackgame.view;

import java.util.List;

public class OutputView {

    private OutputView() {
    }

    public static void printInputNamesMessage() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
    }

    public static void printCardSplitMessage(String dealerNameOutput, List<String> playerNames) {
        String playerNamesOutput = String.join(", ", playerNames);
        System.out.printf("\n%s와 %s에게 2장을 나누었습니다.\n", dealerNameOutput, playerNamesOutput);
    }

    public static void printDealerAdditionalCardMessage() {
        System.out.println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printDealerNoAdditionalCardMessage() {
        System.out.println("\n딜러는 16초과라 추가 카드를 받지 않습니다.");
    }

    public static void printPlayerAdditionalCardMessage(String playerName) {
        System.out.printf("\n%s은(는) 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)\n", playerName);
    }

    public static void printGameResultMessage() {
        System.out.println("\n## 최종 수익");
    }

    public static void printInputBetMoneyMessage(String playerName) {
        System.out.println("\n" + playerName + "의 배팅 금액은?");
    }
}
