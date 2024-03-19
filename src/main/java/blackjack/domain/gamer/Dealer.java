package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Hand;
import java.util.ArrayList;

public class Dealer extends Gamer {

    private static final int DRAW_THRESHOLD = 16;

    private final Deck deck;

    private Dealer(final Deck deck, final Hand hand) {
        super(hand);
        this.deck = deck;
    }

    public static Dealer from(final Deck deck) {
        return new Dealer(deck, new Hand(new ArrayList<>()));
    }

    public void drawInitialHand() {
        for (int i = 0; i < INITIAL_CARD_COUNT; i++) {
            hand.add(deck.pop());
        }
    }

    public void draw() {
        hand.add(deck.pop());
    }

    public Card drawPlayerCard() {
        return deck.pop();
    }

    public Card openFirstCard() {
        return hand.findFirst();
    }

    @Override
    public boolean canDraw() {
        return hand.calculateOptimalSum() <= DRAW_THRESHOLD;
    }
}
