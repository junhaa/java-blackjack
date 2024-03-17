package blackjack.model.generator;

import blackjack.model.cards.Card;
import blackjack.model.cards.CardNumber;
import blackjack.model.cards.CardShape;
import java.util.ArrayList;
import java.util.List;

public class CardGenerator {
    private static final int INITIAL_CARD_SIZE = 2;

    private final IndexGenerator indexGenerator;

    public CardGenerator(final IndexGenerator indexGenerator) {
        this.indexGenerator = indexGenerator;
    }

    public Card drawCard() {
        int numberIndex = indexGenerator.generate(CardNumber.values().length);
        CardNumber cardNumber = CardNumber.generate(numberIndex);
        int shapeIndex = indexGenerator.generate(CardShape.values().length);
        CardShape cardShape = CardShape.generate(shapeIndex);
        return new Card(cardNumber, cardShape);
    }

    public List<Card> drawFirstCardsDealt() {
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < INITIAL_CARD_SIZE; i++) {
            cards.add(drawCard());
        }
        return cards;
    }
}
