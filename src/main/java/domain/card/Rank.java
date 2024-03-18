package domain.card;

import java.util.Arrays;
import java.util.List;

public enum Rank {
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(10),
    QUEEN(10),
    KING(10),
    ACE(11);

    private final int value;

    Rank(final int value) {
        this.value = value;
    }

    public static List<Rank> getValues() {
        return Arrays.stream(Rank.values())
                .toList();
    }

    public boolean isAce() {
        return this == ACE;
    }

    public int getValue() {
        return this.value;
    }
}
