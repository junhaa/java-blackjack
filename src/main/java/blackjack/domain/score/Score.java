package blackjack.domain.score;

import blackjack.domain.card.Card;
import java.util.List;

public class Score {
    private static final int MAXIMUM_SCORE = 21;
    private static final int DEALER_MINIMUM_SCORE = 17;
    public static final int CONVERTED_ACE_DIFFERENCE = 10;
    public static final int BLACK_JACK_CARDS_SIZE = 2;

    private final int cardsSize;
    private final int score;

    public Score(List<Card> cards) {
        this.score = calculateScore(cards);
        this.cardsSize = cards.size();
    }

    public boolean isBusted() {
        return score > MAXIMUM_SCORE;
    }

    public boolean isBlackJack() {
        return isMaxScore() && cardsSize == BLACK_JACK_CARDS_SIZE;
    }

    public boolean isMaxScore() {
        return score == MAXIMUM_SCORE;
    }

    public boolean isLessThanDealerMinimumScore() {
        return score < DEALER_MINIMUM_SCORE;
    }

    public GameResult compete(Score other) {
        if (isAllBlackJack(other) || isAllBusted(other)) {
            return GameResult.DRAW;
        }
        if (isWin(other)) {
            return GameResult.WIN;
        }
        if (isLose(other)) {
            return GameResult.LOSE;
        }
        return competeScore(other);
    }

    public int getScore() {
        return score;
    }

    public int getCardsSize() {
        return cardsSize;
    }

    private int calculateScore(List<Card> cards) {
        int score = cards.stream()
                .mapToInt(Card::getScore)
                .sum();
        if (hasAce(cards)) {
            score = calculateScoreAceExists(cards, score);
        }
        return score;
    }

    private boolean hasAce(List<Card> cards) {
        return cards.stream().anyMatch(Card::isAce);
    }

    private int calculateScoreAceExists(List<Card> cards, int score) {
        int convertedAceAmount = 0;
        int currentBigAceAmount = (int) cards.stream().filter(Card::isAce).count();
        while (isBusted(score) && convertedAceAmount < currentBigAceAmount) {
            score -= CONVERTED_ACE_DIFFERENCE;
            convertedAceAmount++;
        }
        return score;
    }

    private boolean isBusted(int score) {
        return score > MAXIMUM_SCORE;
    }

    private boolean isAllBlackJack(Score other) {
        return this.isBlackJack() && other.isBlackJack();
    }

    private boolean isAllBusted(Score other) {
        return this.isBusted() && other.isBusted();
    }

    private boolean isWin(Score other) {
        if (this.isBlackJack()) {
            return true;
        }
        return other.isBusted();
    }

    private boolean isLose(Score other) {
        if (other.isBlackJack()) {
            return true;
        }
        return this.isBusted();
    }

    private GameResult competeScore(Score other) {
        if (this.score > other.score) {
            return GameResult.WIN;
        }
        if (this.score < other.score) {
            return GameResult.LOSE;
        }
        return GameResult.DRAW;
    }
}
