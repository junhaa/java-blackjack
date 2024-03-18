package blackjack.model.participant;

import static blackjack.model.deck.Score.ACE;
import static blackjack.model.deck.Score.EIGHT;
import static blackjack.model.deck.Score.FIVE;
import static blackjack.model.deck.Score.FOUR;
import static blackjack.model.deck.Shape.CLOVER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import blackjack.model.deck.Card;
import blackjack.model.deck.Deck;
import java.util.ArrayDeque;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PlayersTest {

    @Test
    @DisplayName("플레이어의 이름과 초기 카드들을 받아서 플레이어 그룹을 생성한다.")
    void createPlayers() {
        List<String> names = List.of("리브", "몰리");
        assertThatCode(() -> Players.of(names, Deck.createByRandomOrder()));
    }

    @Test
    @DisplayName("플레이어의 이름이 중복되는 경우 예외를 던진다.")
    void createPlayersByDuplicatedName() {
        List<String> names = List.of("몰리", "몰리");

        assertThatIllegalArgumentException()
                .isThrownBy(() -> Players.of(names, Deck.createByRandomOrder()))
                .withMessage("중복되는 이름을 입력할 수 없습니다.");
    }

    @ParameterizedTest
    @MethodSource("InvalidNames")
    @DisplayName("플레이어의 이름이 1개 이상 10개 이하가 아니면 예외를 던진다.")
    void createPlayersByOutBound(List<String> names, Deck deck) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Players.of(names, deck))
                .withMessage("참여할 인원의 수는 최소 1명 최대 10명이어야 합니다.");
    }

    private static Stream<Arguments> InvalidNames() {
        Deck deck = Deck.createByRandomOrder();
        return Stream.of(
                Arguments.arguments(List.of(), deck),
                Arguments.arguments(List.of("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"), deck));
    }

    @Test
    @DisplayName("플레이어들의 이름 목록을 반환한다.")
    void getNames() {
        List<String> names = List.of("리브", "몰리");
        assertThat(Players.of(names, Deck.createByRandomOrder()).getNames()).containsExactly(new Name("리브"),
                new Name("몰리"));
    }

    @Test
    @DisplayName("각 플레이어의 이름과 카드들을 모아서 반환한다.")
    void collectCardsOfEachPlayer() {
        List<String> names = List.of("리브", "몰리");
        Deck deck = new Deck(new ArrayDeque<>(List.of(Card.from(CLOVER, ACE), Card.from(CLOVER, FIVE),
                Card.from(CLOVER, FOUR), Card.from(CLOVER, EIGHT))));

        Map<Name, List<Card>> expected = new LinkedHashMap<>();
        expected.put(new Name("리브"), List.of(Card.from(CLOVER, ACE), Card.from(CLOVER, FIVE)));
        expected.put(new Name("몰리"), (List.of(Card.from(CLOVER, FOUR), Card.from(CLOVER, EIGHT))));
        assertThat(Players.of(names, deck).collectCardsOfEachPlayer())
                .containsExactlyEntriesOf(expected);
    }
}
