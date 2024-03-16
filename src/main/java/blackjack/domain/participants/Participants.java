package blackjack.domain.participants;

import blackjack.domain.card.Card;
import java.util.List;

public class Participants {

    private static final int DEALER_COUNT = 1;

    private final Dealer dealer;
    private final Players players;

    public Participants(Dealer dealer, Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public void receiveDealerCard(Card card) {
        dealer.hit(card);
    }

    public void receiveInitialHands(List<Hands> allHands) {
        Hands dealerHands = extractOneHands(allHands);
        dealer.receiveHands(dealerHands);
        players.distributeHands(allHands);
    }

    private Hands extractOneHands(List<Hands> hands) {
        if (hands.isEmpty()) {
            throw new IllegalArgumentException("손패 리스트가 비어 있습니다.");
        }
        return hands.remove(hands.size() - 1);
    }

    public BlackJackGameResult calculateGameResult() {
        return new BlackJackGameResult(players.getPlayers(), dealer);
    }

    public boolean isDealerNotOver() {
        return dealer.canHit();
    }

    public int countPlayers() {
        return players.size();
    }

    public int count() {
        return countPlayers() + DEALER_COUNT;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Players getPlayers() {
        return players;
    }
}
