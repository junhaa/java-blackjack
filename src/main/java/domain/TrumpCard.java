package domain;

public record TrumpCard(Suit suit, CardValue cardValue) {
    public int cardNumberValue() {
        return cardValue.getValue();
    }

    public boolean isAce() {
        return cardValue.isAce();
    }
}
