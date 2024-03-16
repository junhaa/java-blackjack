package domain.card;

import java.util.List;

public class DealerCards extends Cards implements Drawable {

    private static final int MIN_SCORE = 16;

    public DealerCards(List<Card> cards) {
        super(cards);
    }

    @Override
    public boolean canDraw() {
        return bestSum() <= MIN_SCORE;
    }

    public String getFirstCard() {
        if (cards.isEmpty()) {
            throw new UnsupportedOperationException("카드가 없습니다.");
        }
        return cards.get(0).toString();
    }
}
