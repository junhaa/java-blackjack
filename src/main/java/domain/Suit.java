package domain;

public enum Suit {

    CLOVER("클로버"),
    SPADE("스페이드"),
    HEART("하트"),
    DIAMOND("다이아몬드");

    private String suit;

    Suit(String suit) {
        this.suit = suit;
    }
}
