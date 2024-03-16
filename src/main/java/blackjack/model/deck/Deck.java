package blackjack.model.deck;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Deck {
    private static final int BLACKJACK_CARD_COUNT = 52;
    static final String DECK_SIZE_IS_NOT_ENOUGH = "카드의 수가 52개가 아닙니다.";
    static final String CAN_NOT_DRAWN_CARD = "카드가 부족합니다.";

    private final Deque<Card> deck;

    public Deck() {
        this.deck = new ArrayDeque<>();
    }

    private Deck(final Deque<Card> deck) {
        this.deck = new ArrayDeque<>(deck);
    }

    private void validateSize(final List<Card> deck) {
        if (deck.size() != BLACKJACK_CARD_COUNT) {
            throw new IllegalStateException(DECK_SIZE_IS_NOT_ENOUGH);
        }
    }

    public Deck makeDeck() {
        List<Card> originDeck = Arrays.stream(Shape.values())
                .flatMap(this::matchScore)
                .collect(Collectors.toList());
        validateSize(originDeck);
        return new Deck(shuffleDeck(originDeck));
    }

    private Stream<Card> matchScore(Shape shape) {
        return Arrays.stream(Score.values())
                .map(score -> new Card(shape, score));
    }

    private Deque<Card> shuffleDeck(final List<Card> originDeck) {
        Collections.shuffle(originDeck);
        return new ArrayDeque<>(originDeck);
    }

    public Card drawn() {
        try {
            return deck.removeFirst();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException(CAN_NOT_DRAWN_CARD);
        }
    }
}
