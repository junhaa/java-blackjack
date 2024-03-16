package blackjack.domain.participants;

import blackjack.domain.card.Deck;
import java.util.ArrayList;
import java.util.List;

public class GameBoard {

    private static final int INITIAL_CARD_COUNT = 2;

    private final Participants participants;
    private final Deck deck;

    public GameBoard(Dealer dealer, Players players) {
        this.participants = new Participants(dealer, players);
        this.deck = new Deck();
    }

    public int countPlayers() {
        return participants.countPlayers();
    }

    public boolean isDealerNotOver() {
        return participants.isDealerNotOver();
    }

    public void distributeInitialHands() {
        List<Hands> initialDecks = makeInitialHands();
        participants.receiveInitialHands(initialDecks);
    }

    private List<Hands> makeInitialHands() {
        List<Hands> initialDecks = new ArrayList<>();
        for (int participantsCount = 0; participantsCount < participants.count(); participantsCount++) {
            initialDecks.add(makeHands());
        }
        return initialDecks;
    }

    private Hands makeHands() {
        Hands hands = new Hands(new ArrayList<>());
        for (int cardCount = 0; cardCount < INITIAL_CARD_COUNT; cardCount++) {
            hands.addCard(deck.drawCard());
        }
        return hands;
    }

    public void addCardToDealer() {
        participants.receiveDealerCard(deck.drawCard());
    }

    public void addCardToPlayer(Player player) {
        player.hit(deck.drawCard());
    }

    public BlackJackGameResult calculateGameResult() {
        return participants.calculateGameResult();
    }

    public Deck getDeck() {
        return deck;
    }

    public Players getPlayers() {
        return participants.getPlayers();
    }

    public Dealer getDealer() {
        return participants.getDealer();
    }
}
