package blackjack.domain.participant;

import blackjack.domain.deck.Card;
import blackjack.domain.deck.Deck;
import java.util.List;

public class Player {
    private final Name name;
    private final Hands hands;

    private Player(Name name, Hands hands) {
        this.name = name;
        this.hands = hands;
    }

    public static Player createPlayerWithCards(Name name, List<Card> initialCards) {
        Hands initialHands = new Hands();
        for (Card card : initialCards) {
            initialHands.addCard(card);
        }
        return new Player(name, initialHands);
    }

    public void draw(Deck deck) {
        hands.addCard(deck.draw());
    }

    public String getName() {
        return name.getName();
    }

    public List<Card> getHandsCards() {
        return hands.getHands();
    }

    public Hands getHands() {
        return hands;
    }

    public int findHandsScore() {
        return hands.findHandsScore();
    }

    public boolean isBust() {
        return hands.isBust();
    }
}
