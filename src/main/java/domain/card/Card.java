package domain.card;

import java.util.Objects;

public class Card {
    private final Symbol symbol;
    private final Rank rank;

    public Card(final Symbol symbol, final Rank rank) {
        this.symbol = symbol;
        this.rank = rank;
    }

    public boolean isAce() {
        return rank.isAce();
    }

    public int getScore() {
        return rank.getScore();
    }

    public String getSymbolValue() {
        return symbol.getValue();
    }

    public String getRankValue() {
        return rank.getValue();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Card card = (Card) o;
        return symbol == card.symbol && rank == card.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, rank);
    }
}
