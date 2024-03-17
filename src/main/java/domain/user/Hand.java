package domain.user;

import domain.card.Card;
import domain.card.Number;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {
    private static final int BLACK_JACK_CONDITION = 21;
    private static final int START_CARDS_COUNT = 2;
    public static final int FIRST_INDEX = 0;
    private final List<Card> cards;

    public Hand(Card... cards) {
        this.cards = new ArrayList<>(List.of(cards));
    }

    public void receive(Card card) {
        cards.add(card);
    }

    public int sumCard() {
        int sum = cards.stream()
                .mapToInt(Card::getNumberValue)
                .sum();
        return addSumByAce(sum);
    }

    private int addSumByAce(int sum) {
        if (hasAce()) {
            return Number.sumContainingSoftAce(sum);
        }
        return sum;
    }

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    public boolean isBlackjack() {
        return cards.size() == START_CARDS_COUNT && sumCard() == BLACK_JACK_CONDITION;
    }

    public boolean isBusted() {
        return sumCard() > BLACK_JACK_CONDITION;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public Card getFirstCard() {
        return cards.get(FIRST_INDEX);
    }
}
