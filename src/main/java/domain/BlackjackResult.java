package domain;

import java.util.List;

public record BlackjackResult(String name, List<TrumpCard> trumpCards, int cardSum) {
}
