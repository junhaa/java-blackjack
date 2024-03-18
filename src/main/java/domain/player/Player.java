package domain.player;

import domain.card.Card;
import domain.card.Hand;
import domain.game.Score;
import java.util.List;

public class Player {
    private final PlayerName playerName;
    private final Hand hand;

    public Player(final PlayerName playerName, final Hand hand) {
        this.playerName = playerName;
        this.hand = hand;
    }

    public Player(final List<Card> cards) {
        this.playerName = new PlayerName("Test");
        this.hand = new Hand(cards);
    }

    public void hit(final Card card) {
        this.hand.add(card);
    }

    public boolean isBust() {
        return getScore().isBust();
    }

    public boolean isHittable() {
        return !getScore().isBlackJack() && !getScore().isBust();
    }

    public boolean isBlackJack() {
        return getScore().isBlackJack() && hand.isInitialSize();
    }

    public Score getScore() {
        return hand.calculateScore();
    }

    public Hand getHand() {
        return hand;
    }

    public PlayerName getName() {
        return playerName;
    }

}
