package blackjack.view.display;

import blackjack.model.deck.Shape;
import java.util.Arrays;

public enum ShapeDisplay {
    SPADE(Shape.SPADE, "스페이드"),
    DIA(Shape.DIA, "다이아"),
    CLOVER(Shape.CLOVER, "클로버"),
    HEART(Shape.HEART, "하트"),
    ;

    private final Shape shape;
    private final String value;

    ShapeDisplay(final Shape shape, final String value) {
        this.shape = shape;
        this.value = value;
    }

    public static String getValue(final Shape shape) {
        return Arrays.stream(ShapeDisplay.values())
                .filter(converter -> converter.shape == shape)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 Shape 입니다."))
                .value;
    }
}
