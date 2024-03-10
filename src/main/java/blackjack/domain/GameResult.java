package blackjack.domain;

public enum GameResult {

    WIN("승"),
    TIE("무"),
    LOSE("패");

    private final String value;

    GameResult(String value) {
        this.value = value;
    }

    public GameResult getOpposite() {
        if (this == WIN) {
            return LOSE;
        }

        if (this == LOSE) {
            return WIN;
        }

        return TIE;
    }

    public String getValue() {
        return value;
    }
}
