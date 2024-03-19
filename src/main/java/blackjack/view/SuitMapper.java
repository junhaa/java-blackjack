package blackjack.view;

import blackjack.domain.card.Suit;
import java.util.Arrays;

public enum SuitMapper {
    HEART(Suit.HEART, "하트"),
    SPADE(Suit.SPADE, "스페이드"),
    CLUB(Suit.CLUB, "클로버"),
    DIAMOND(Suit.DIAMOND, "다이아몬드");

    private final Suit suit;
    private final String viewName;

    SuitMapper(final Suit suit, final String viewName) {
        this.suit = suit;
        this.viewName = viewName;
    }

    public static String mapToViewName(final Suit suit) {
        return Arrays.stream(SuitMapper.values())
                .filter(suitMapper -> suitMapper.suit == suit)
                .findAny()
                .orElseThrow(() -> new IllegalStateException("[" + suit.name() + "]에 매칭되는 슈트가 없습니다."))
                .viewName;
    }

    public Suit getSuit() {
        return suit;
    }
}
