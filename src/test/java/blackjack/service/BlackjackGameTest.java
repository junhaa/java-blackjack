package blackjack.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.domain.dealer.Deck;
import blackjack.domain.participant.Player;
import blackjack.dto.ParticipantCardsDto;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackGameTest {
    private final String kirby = "kirby";
    private final Player playerKirby = new Player("kirby");

    @DisplayName("모든 참가자들이 이름을 세팅하고 플레이어는 2장씩, 딜러는 공개용 카드 1장을 가졌는지 확인한다. ")
    @Test
    void init() {
        // given
        BlackjackGame blackjackGame = new BlackjackGame(List.of(kirby, "pobi"), Deck.create());
        blackjackGame.handOutCards();

        // when
        List<ParticipantCardsDto> participantStartCards = blackjackGame.getStartCards();

        // then
        assertAll(() -> assertThat(participantStartCards.get(0).name()).isEqualTo(kirby),
                () -> assertThat(participantStartCards.get(0).cardDtos()).hasSize(2),

                () -> assertThat(participantStartCards.get(1).name()).isEqualTo("pobi"),
                () -> assertThat(participantStartCards.get(1).cardDtos()).hasSize(2),

                () -> assertThat(participantStartCards.get(2).name()).isEqualTo("딜러"),
                () -> assertThat(participantStartCards.get(2).cardDtos()).hasSize(1));
    }

    @DisplayName("플레이어가 카드를 추가할 수 있는 상황일 때 카드가 잘 추가되었는지 확인한다.")
    @Test
    void canAddCardToPlayers() {
        // given
        BlackjackGame blackjackGame = new BlackjackGame(List.of(kirby, "pobi"), Deck.create());
        blackjackGame.handOutCards();

        // when
        blackjackGame.addCardToPlayer(playerKirby);
        boolean playerAlive = blackjackGame.isPlayerAlive(playerKirby);

        // then
        assertAll(() -> assertThat(playerAlive).isTrue(),
                () -> assertThat(blackjackGame.getPlayers().get(0).getHands().getCards()).hasSize(3));
    }

    @DisplayName("플레이어가 카드를 더 추가하면 안되는 때에 카드를 추가할 수 없는지 확인한다.")
    @Test
    void canNotAddCardToPlayers() {
        // given
        BlackjackGame blackjackGame = new BlackjackGame(List.of(kirby, "pobi"), Deck.create());
        blackjackGame.handOutCards();

        // when
        for (int i = 0; i < 20; i++) {
            blackjackGame.addCardToPlayer(playerKirby);
        }
        blackjackGame.addCardToPlayer(playerKirby);
        boolean playerAlive = blackjackGame.isPlayerAlive(playerKirby);

        // then
        assertThat(playerAlive).isFalse();
    }
}
