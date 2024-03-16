package blackjack.domain.participants;

import java.util.ArrayList;

public class Dealer extends GameParticipant {

    private static final int MAX_RECEIVE_SCORE = 17;

    public Dealer() {
        super(new Name("딜러"), new Hands(new ArrayList<>()));
    }

    @Override
    protected boolean isWin(GameParticipant participant) {
        if (participant.isBust()) {
            return true;
        }
        if (isBust()) {
            return false;
        }
        return calculateScore() >= participant.calculateScore();
    }

    @Override
    public boolean canHit() {
        return calculateScore() < MAX_RECEIVE_SCORE;
    }
}
