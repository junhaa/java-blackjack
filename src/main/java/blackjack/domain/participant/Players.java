package blackjack.domain.participant;

import blackjack.domain.game.Deck;

import java.util.List;

public class Players {

    private static final int MIN_PLAYER_NUMBER = 1;
    private static final int MAX_PLAYER_NUMBER = 8;

    private final List<Player> players;

    public Players(List<String> playerNames) {
        validatePlayersNumber(playerNames);
        this.players = playerNames.stream()
                .map(Player::new)
                .toList();
    }

    public void handOutInitialCards(Deck deck) {
        for (Player player : players) {
            player.addCard(deck.draw());
            player.addCard(deck.draw());
        }
    }


    private void validatePlayersNumber(List<String> players) {
        if (players.size() < MIN_PLAYER_NUMBER || players.size() > MAX_PLAYER_NUMBER) {
            throw new IllegalArgumentException("게임 참여자는 최소 " + MIN_PLAYER_NUMBER
                    + "명에서 최대 " + MAX_PLAYER_NUMBER + "명까지 가능합니다");
        }
    }

    public List<Player> getPlayers() {
        return players;
    }
}
