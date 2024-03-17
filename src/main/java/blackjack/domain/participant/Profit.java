package blackjack.domain.participant;

import java.math.BigDecimal;

public class Profit {

    private BigDecimal amount;

    private Profit(final BigDecimal amount) {
        this.amount = amount;
    }

    public static Profit initProfit() {
        return new Profit(BigDecimal.ZERO);
    }

    public void earn(final BigDecimal win, final BigDecimal betting) {
        this.amount = this.amount.add(win.subtract(betting));
    }

    public void earn(final BigDecimal betting) {
        this.amount = this.amount.add(betting);
    }

    public void earnBlackjack(final BigDecimal blackjack, final BigDecimal betting) {
        this.amount = this.amount.add(blackjack.subtract(betting));
    }

    public void lose(final BigDecimal amount) {
        this.amount = this.amount.subtract(amount);
    }

    public String formatProfit(BigDecimal number) {
        if (number.scale() <= 0) {
            return number.setScale(0).toString();
        }
        return number.stripTrailingZeros().toPlainString();
    }

    @Override
    public String toString() {
        return formatProfit(amount);
    }
}
