package blackjack.domain.participant;

import blackjack.domain.card.Card;

import java.util.regex.Pattern;

public class Player extends Gamer {

    private static final Pattern NAME_VALID_PATTERN = Pattern.compile("^[a-zA-Z가-힣]+(?:\\s+[a-zA-Z가-힣]+)*$");
    private static final String INVALID_NAME_FORMAT_EXCEPTION = "이름 형식에 맞지 않습니다. 이름은 영어 또는 한글로 입력해주세요.";

    private final String name;

    public Player(final String name, final Dealer dealer) {
        super();

        validateName(name);
        this.name = name;

        initialDraw(dealer);
    }

    private void validateName(final String name) {
        if (!NAME_VALID_PATTERN.matcher(name).matches()) {
            throw new IllegalArgumentException(INVALID_NAME_FORMAT_EXCEPTION);
        }
    }

    private void initialDraw(final Dealer dealer) {
        for (int i = 0; i < 2; i++) {
            draw(dealer);
        }
    }

    public void draw(final Dealer dealer) {
        if (canReceiveCard()) {
            Card card = dealer.draw();
            hand.add(card);
        }
    }

    @Override
    public boolean canReceiveCard() {
        return !hand.isBust() && !hand.isBlackjack();
    }

    public String getName() {
        return name;
    }
}
