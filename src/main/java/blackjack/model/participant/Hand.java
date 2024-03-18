package blackjack.model.participant;

import static blackjack.model.deck.Score.ACE;

import blackjack.model.deck.Card;
import java.util.ArrayList;
import java.util.List;

public class Hand {
    private static final int MIN_INITIAL_CARD_SIZE = 2;
    private static final int ADDITIONAL_ACE_SCORE = 10;
    private static final int SOFT_ACE_COUNT = 1;
    private static final int SOFT_MAX_SCORE_WITHOUT_ACE = 10;

    private final List<Card> cards;

    public Hand(final List<Card> cards) {
        validateSize(cards);
        this.cards = new ArrayList<>(cards);
    }

    private void validateSize(final List<Card> cards) {
        if (cards.size() < MIN_INITIAL_CARD_SIZE) {
            throw new IllegalArgumentException("카드는 두 장 이상이어야 합니다.");
        }
    }

    public Hand addCard(final Card card) {
        cards.add(card);
        return new Hand(cards);
    }

    public int calculateScore() {
        int totalScoreWithoutAce = calculateBaseScore();
        int aceCount = countAce();

        if (isSoft(aceCount, totalScoreWithoutAce)) {
            totalScoreWithoutAce += aceCount * ADDITIONAL_ACE_SCORE;
        }

        return totalScoreWithoutAce + aceCount * ACE.getValue();
    }

    private int calculateBaseScore() {
        return cards.stream()
                .filter(card -> !card.isAce())
                .mapToInt(Card::getScoreValue)
                .sum();
    }

    private int countAce() {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
    }

    private boolean isSoft(final int aceCount, final int baseScore) {
        return aceCount == SOFT_ACE_COUNT && baseScore <= SOFT_MAX_SCORE_WITHOUT_ACE;
    }

    public int countSize() {
        return cards.size();
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    public Card getFirstCard() {
        return cards.get(0);
    }

    public boolean isBust() {
        return getHandStatus().equals(HandStatus.BUST);
    }

    public boolean isBlackJack() {
        return getHandStatus().equals(HandStatus.BLACKJACK);
    }

    public boolean isUnderMaxScore() {
        return getHandStatus().equals(HandStatus.UNDER_MAX_SCORE);
    }

    public HandStatus getHandStatus() {
        return HandStatus.of(calculateScore(), cards.size());
    }
}
