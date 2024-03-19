package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    private static final int BLACKJACK_CANDIDATE = 21;
    private static final int VALUE_FOR_SOFT_HAND = 10;
    private static final int INITIAL_CARD_SIZE = 2;

    private final List<Card> cards;

    public Hand(final List<Card> cards) {
        this.cards = cards;
    }

    public Hand() {
        this(new ArrayList<>());
    }

    public void add(final Card card) {
        cards.add(card);
    }

    public int calculateOptimalSum() {
        final List<Rank> ranks = cards.stream()
                .map(Card::getRank)
                .toList();

        if (ranks.contains(Rank.ACE)) {
            return calculateAceToOptimal(ranks);
        }
        return sumHardHand(ranks);
    }

    private int calculateAceToOptimal(final List<Rank> ranks) {
        final int hardHandScore = sumHardHand(ranks);
        final int softHandScore = hardHandScore + VALUE_FOR_SOFT_HAND;
        if (softHandScore <= BLACKJACK_CANDIDATE) {
            return softHandScore;
        }
        return hardHandScore;
    }

    private int sumHardHand(final List<Rank> ranks) {
        return ranks.stream()
                .mapToInt(Rank::getValue)
                .sum();
    }

    public boolean hasOnlyInitialCard() {
        return cards.size() == INITIAL_CARD_SIZE;
    }

    public Card findFirst() {
        return cards.get(0);
    }

    public List<Card> getCards() {
        return cards;
    }
}
