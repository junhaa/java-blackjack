package domain.gamer;

import domain.card.Card;
import domain.card.Hand;

public class Dealer extends Player {

    private static final String DEALER_NAME = "딜러";

    public Dealer(Hand hand) {
        super(DEALER_NAME, hand);
    }

    public boolean canHit() {
        return hand.cannotDealerHit();
    }

    public Card openOneCard() {
        return hand.pickFirstCard();
    }
}
