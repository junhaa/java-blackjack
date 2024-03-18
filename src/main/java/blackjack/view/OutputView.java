package blackjack.view;

import blackjack.dto.NameCardsScore;
import blackjack.dto.NameProfit;
import blackjack.model.deck.Card;
import blackjack.model.participant.Name;
import blackjack.view.formatter.CardsFormatter;
import blackjack.view.formatter.NameProfitFormat;
import blackjack.view.formatter.NamesFormatter;
import blackjack.view.formatter.PlayerNameFormatter;
import blackjack.view.formatter.ScoreResultFormatter;
import java.util.List;

public class OutputView {

    private OutputView() {
        throw new AssertionError("인스턴스를 생성할 수 없습니다.");
    }

    public static void println() {
        System.out.println();
    }

    public static void printDistributionSubject(final List<Name> names) {
        System.out.printf("딜러와 %s에게 2장을 나누었습니다.%n", NamesFormatter.format(names));
    }

    public static void printNameAndCards(final Name name, final List<Card> cards) {
        System.out.println(PlayerNameFormatter.formatWithCardComment(name) + CardsFormatter.format(cards));
    }

    public static void printDealerHit() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printFinalCardsAndScore(final List<NameCardsScore> nameCardsScores) {
        nameCardsScores.forEach(OutputView::printFinalCardsAndScore);
    }

    public static void printFinalCardsAndScore(final NameCardsScore nameCardsScore) {
        System.out.println(PlayerNameFormatter.formatWithCardComment(nameCardsScore.name())
                + CardsFormatter.format(nameCardsScore.cards())
                + ScoreResultFormatter.format(nameCardsScore.score()));
    }

    public static void printProfits(final List<NameProfit> playerProfits) {
        System.out.println();
        System.out.println("## 최종 수익");
        playerProfits.forEach(nameProfit -> System.out.println(NameProfitFormat.format(nameProfit)));
    }
}
