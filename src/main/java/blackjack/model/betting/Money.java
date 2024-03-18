package blackjack.model.betting;

import java.util.Objects;

public class Money {
    private final int value;

    public Money(final int value) {
        this.value = value;
    }

    public Money multiple(final ProfitRate rate) {
        return new Money((int) (value * rate.getRate()));
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Money money = (Money) o;
        return value == money.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
