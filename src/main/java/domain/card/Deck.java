package domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Deck {
    private final List<Card> cards;

    protected Deck(List<Card> cards) {
        this.cards = cards;
    }

    public static Deck withFullCards() {
        List<Card> cards = Card.initializeDeck();
        Collections.shuffle(cards);
        return new Deck(new ArrayList<>(cards));
    }

    protected static Deck withCustomCards(Card... cards) {
        return new Deck(new ArrayList<>(Arrays.stream(cards).toList()));
    }

    public Card draw() {
        if (cards.isEmpty()) {
            throw new RuntimeException("카드가 더이상 존재하지 않습니다.");
        }
        return cards.remove(cards.size() - 1);
    }
}
