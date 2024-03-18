package mapper;

import domain.card.Cards;
import domain.name.Names;
import domain.participant.Participant;
import domain.participant.Player;
import domain.participant.Players;
import domain.vo.BettingMoney;
import domain.vo.Card;
import domain.vo.Name;
import view.dto.participant.ParticipantDto;

import java.util.ArrayList;
import java.util.List;

public class ParticipantMapper {
    private ParticipantMapper() {
    }

    public static ParticipantDto participantToParticipantDto(final Participant participant) {
        return new ParticipantDto(participant.name(), CardMapper.handToCardsDto(participant.hand(), participant.score().value()));
    }

    public static ParticipantDto participantAndCardToParticipantDto(final Participant participant, final Card card) {
        return new ParticipantDto(participant.name(), CardMapper.cardsToCardsDto(new Cards(List.of(card)), participant.score().value()));
    }

    public static List<ParticipantDto> playersToParticipantsDto(final Players players) {
        return players.getPlayers()
                      .stream()
                      .map(ParticipantMapper::participantToParticipantDto)
                      .toList();
    }

    public static Players namesAndBettingMoneyToPlayers(final Names names, final List<BettingMoney> bettingMoneys) {
        List<Name> playerNames = names.playerNames();
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < playerNames.size(); i++) {
            players.add(Player.register(playerNames.get(i), bettingMoneys.get(i)));
        }
        return new Players(players);
    }
}
