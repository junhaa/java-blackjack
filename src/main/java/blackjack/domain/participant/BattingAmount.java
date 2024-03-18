package blackjack.domain.participant;

import java.util.Objects;

public class BattingAmount {
    private static final int MINIMUM_BATTING_AMOUNT = 10_000;
    private static final int MAXIMUM_BATTING_AMOUNT = 1_000_000;
    private static final int VALID_BATTING_AMOUNT_UNIT = 1_000;

    private final int battingAmount;

    public BattingAmount(final int battingAmount) {
        validateBattingAmount(battingAmount);
        this.battingAmount = battingAmount;
    }

    private void validateBattingAmount(final int battingAmount) {
        validateRange(battingAmount);
        validateUnit(battingAmount);
    }

    private void validateRange(final int battingAmount) {
        if (battingAmount < MINIMUM_BATTING_AMOUNT || battingAmount > MAXIMUM_BATTING_AMOUNT) {
            throw new IllegalArgumentException(
                    String.format("배팅 금액은 %,d ~ %,d원 사이어야 합니다.", MINIMUM_BATTING_AMOUNT, MAXIMUM_BATTING_AMOUNT));
        }
    }

    private void validateUnit(final int battingAmount) {
        if (battingAmount % VALID_BATTING_AMOUNT_UNIT != 0) {
            throw new IllegalArgumentException(
                    String.format("배팅 금액은 %,d원 단위어야 합니다.", VALID_BATTING_AMOUNT_UNIT));
        }
    }

    public int multiply(final double earningRate) {
        return (int) (battingAmount * earningRate);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final BattingAmount other = (BattingAmount) o;
        return battingAmount == other.battingAmount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(battingAmount);
    }
}
