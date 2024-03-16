package domain.card;

import java.util.LinkedList;
import java.util.List;

public class Cards {

    private static final int INIT_CARD_SIZE = 2;
    protected static final int MAX_SCORE = 21;

    protected final List<Card> cards;

    public Cards(List<Card> cards) {
        validate(cards);
        this.cards = new LinkedList<>(cards);
    }

    private void validate(List<Card> cards) {
        if (cards.size() != INIT_CARD_SIZE) {
            throw new IllegalArgumentException("처음 지급받는 카드는 두 장이어야 합니다.");
        }
    }

    public void receive(Card card) {
        cards.add(card);
    }

    public int sumInitCards() {
        Cards initCards = new Cards(cards.subList(0, 2));
        return initCards.bestSum();
    }

    public int bestSum() {
        long countAce = countAce();
        int sum = sum();
        while (sum + 10 <= MAX_SCORE && countAce > 0) {
            sum += 10;
            countAce--;
        }
        return sum;
    }

    public int sum() {
        return cards.stream()
                .mapToInt(Card::getCardNumber)
                .sum();
    }

    private long countAce() {
        return cards.stream()
                .filter(Card::isAce)
                .count();
    }

    public List<String> getCards() {
        return cards.stream()
                .map(Card::toString)
                .toList();
    }
}
