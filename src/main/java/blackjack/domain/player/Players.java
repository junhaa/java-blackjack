package blackjack.domain.player;

import blackjack.domain.score.Score;
import java.util.List;

public class Players {
    private final List<Player> playerGroup;

    public Players(List<String> names) {
        validate(names);
        this.playerGroup = names.stream()
                .map(Player::new)
                .toList();
    }

    public List<Player> getPlayers() {
        return playerGroup;
    }

    public List<String> getPlayerNames() {
        return playerGroup.stream()
                .map(Player::getName)
                .toList();
    }

    private void validate(List<String> names) {
        if (duplicatedNameExist(names)) {
            throw new IllegalArgumentException("중복된 이름이 존재합니다.");
        }
    }

    private boolean duplicatedNameExist(List<String> names) {
        int distinctCount = (int) names.stream()
                .distinct()
                .count();

        return distinctCount != names.size();
    }

    public boolean isAllBusted() {
        return playerGroup.stream().
                map(Player::calculateScore)
                .allMatch(Score::isBusted);
    }
}
