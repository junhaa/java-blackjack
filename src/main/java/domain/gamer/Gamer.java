package domain.gamer;

import domain.card.Card;
import domain.card.Deck;
import domain.card.Hand;
import java.util.List;
import java.util.stream.IntStream;

public abstract class Gamer {

    private final Name name;
    private final Hand hand;

    public Gamer(Name name) {
        this.name = name;
        this.hand = new Hand();
    }

    public void pickCards(Deck deck, int count) {
        IntStream.range(0, count)
                .forEach(it -> hand.add(deck.draw()));
    }

    abstract public boolean canHit();

    public int hit(Deck deck) {
        hand.add(deck.draw());
        return 1;
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public boolean isBlackJack() {
        return hand.isBlackJack();
    }

    public Name getName() {
        return name;
    }

    public List<Card> getCards() {
        return hand.getCards();
    }

    public int getTotalScore() {
        return hand.getResultScore();
    }
}
