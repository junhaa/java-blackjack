package blackjack.domain.cardgame;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import blackjack.domain.player.Participant;
import blackjack.domain.player.Participants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardDeck {
    private final List<Card> deck;

    public CardDeck() {
        final List<Card> deck = new ArrayList<>();

        for (final Denomination denomination : Denomination.values()) {
            for (final Suit suit : Suit.values()) {
                deck.add(new Card(denomination, suit));
            }
        }

        Collections.shuffle(deck);

        this.deck = deck;
    }

    public void giveCard(final Participant participant) {
        participant.addCard(draw());
    }

    public void initializeHand(final Participants participants) {
        participants.getAllParticipants()
                .forEach(participant -> {
                    giveCard(participant);
                    giveCard(participant);
                });
    }

    private Card draw() {
        if (deck.isEmpty()) {
            throw new IllegalStateException("[ERROR] 덱이 비어있어 카드를 뽑을 수 없습니다.");
        }
        return deck.remove(deck.size() - 1);
    }
}
