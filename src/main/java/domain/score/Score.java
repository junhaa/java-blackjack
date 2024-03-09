package domain.score;

import java.util.HashMap;
import java.util.Map;

import static domain.score.Status.*;


public class Score {

    private final Map<Status, Integer> score;

    public Score() {
        score = new HashMap<>(Map.of(
                WIN, 0,
                TIE, 0,
                LOSE, 0
        ));
    }

    public void increaseScore(Status status) {
        Integer presentScore = score.get(status);
        score.put(status, presentScore + 1);
    }

    public int getScore(Status status) {
        return score.get(status);
    }
}
