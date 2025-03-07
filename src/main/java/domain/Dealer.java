package domain;

public class Dealer extends BlackjackPlayer {

    private static final int DEALER_STOP_HIT_STANDARD = 17;
    private static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(DEALER_NAME);
    }

    @Override
    public boolean isDrawable() {
        int sum = calculateCardSum();
        return sum < DEALER_STOP_HIT_STANDARD;
    }
}
