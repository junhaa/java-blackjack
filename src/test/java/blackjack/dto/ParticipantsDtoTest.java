package blackjack.dto;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Players;
import org.junit.jupiter.api.Test;

import java.util.List;

import static blackjack.fixture.DealerFixture.딜러;
import static blackjack.fixture.PlayerFixture.플레이어들;
import static org.assertj.core.api.Assertions.assertThat;

class ParticipantsDtoTest {

    @Test
    void 플레이어들을_dto로_변환할_수_있다() {
        final Players players = 플레이어들("pobi", "jason");
        final ParticipantsDto participantsDto = ParticipantsDto.toDtoWithoutDealer(players);

        final List<ParticipantDto> participantDtos = participantsDto.participants();

        assertThat(participantDtos).hasSize(2);
    }

    @Test
    void 플레이어들과_딜러를_dto로_변환할_수_있다() {
        final Dealer dealer = 딜러();
        final Players players = 플레이어들("pobi", "jason");
        final ParticipantsDto participantsDto = ParticipantsDto.toDtoWithDealer(dealer, players);

        final List<ParticipantDto> participantDtos = participantsDto.participants();

        assertThat(participantDtos).hasSize(3);
    }
}
