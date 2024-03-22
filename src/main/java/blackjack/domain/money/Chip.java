package blackjack.domain.money;

import blackjack.domain.game.PlayerResult;

public class Chip {
    private final Long money;

    public Chip(Long money) {
        validateNaturalNumber(money);
        this.money = money;
    }

    private void validateNaturalNumber(Long betting) {
        if (betting < 0) {
            throw new IllegalArgumentException("0 이상의 정수를 입력해 주세요.");
        }
    }

    public double totalProfit(PlayerResult playerResult) {
        return money * playerResult.operator();
    }

    public Long value() {
        return money;
    }
}
