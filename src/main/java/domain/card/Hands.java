package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hands {

    private static final int ADDITIONAL_SCORE = 10;
    private static final int BLACK_JACK_COUNT = 21;

    private final List<Card> value;

    public Hands() {
        this.value = new ArrayList<>();
    }

    public int calculateScore() {
        int totalScore = value.stream()
                .mapToInt(Card::getScore)
                .sum();

        if (hasAce()) {
            totalScore = calculateAceScore(totalScore);
        }
        return totalScore;
    }

    private boolean hasAce() {
        return value.stream()
                .anyMatch(Card::isAce);
    }

    private int calculateAceScore(int totalScore) {
        if (totalScore + ADDITIONAL_SCORE <= BLACK_JACK_COUNT) {
            totalScore = totalScore + ADDITIONAL_SCORE;
        }
        return totalScore;
    }

    public void receive(Card card) {
        value.add(card);
    }

    public void receive(List<Card> cards) {
        value.addAll(cards);
    }

    public int getCardCount() {
        return value.size();
    }

    public List<Card> getValue() {
        return Collections.unmodifiableList(value);
    }

    public List<Card> getValue(int cardCount) {
        return value.subList(0, cardCount);
    }
}
