package blackjack.view;

import blackjack.card.Card;
import blackjack.view.display.CardNumberDisplay;
import blackjack.view.display.CardShapeDisplay;
import java.util.List;

public class OutputView {

    public void printNamesRequest() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
    }

    public void printBetMoneyRequest(String name) {
        System.out.println(name + "의 배팅 금액은?");
    }

    public void printInitializeBlackJack(List<String> names) {
        System.out.println("딜러와 " + String.join(", ", names) + "에게 2장을 나누었습니다.");
    }

    public void printDealerFirstCard(List<Card> cards) {
        System.out.println("딜러: " + convertCards(cards));
    }

    public void printPlayerCards(String name, List<Card> cards) {
        System.out.println(name + "카드: " + convertCards(cards));
    }

    public void printDrawMoreCardRequest(String name) {
        System.out.println(name + "은(는) 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
    }

    public void printDealerDrawCard() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printDealerCardsWithScore(List<Card> cards, int score) {
        printPlayerCardsWithScore("딜러", cards, score);
    }

    public void printPlayerCardsWithScore(String name, List<Card> cards, int score) {
        System.out.println(name + " 카드: " + convertCards(cards) + " - 결과: " + score + "점");
    }

    public void printProfitMessage() {
        System.out.println("## 최종 수익");
    }

    public void printPlayerProfit(String name, int profit) {
        System.out.println(name + ": " + profit);
    }

    public void printNewLine() {
        System.out.println();
    }

    private String convertCard(Card card) {
        CardNumberDisplay numberDisplay = CardNumberDisplay.getDisplayByNumber(card.getNumber());
        CardShapeDisplay shapeDisplay = CardShapeDisplay.getDisplayByShape(card.getShape());
        return numberDisplay.getNotation() + shapeDisplay.getNotation();
    }

    private String convertCards(List<Card> cards) {
        List<String> convertedCards = cards.stream()
                .map(this::convertCard)
                .toList();
        return String.join(", ", convertedCards);
    }
}
