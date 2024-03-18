package view;

import view.dto.card.CardsDto;
import view.dto.participant.ParticipantDto;
import view.dto.result.GameResultDto;

import java.util.List;
import java.util.Map;

import static domain.BlackjackGame.DEALER_HIT_THRESHOLD;
import static domain.BlackjackGame.INITIAL_CARD_COUNT;

public class ResultView {
    private static final ParsingView PARSING = new ParsingView();
    public static final String CONNECT = ": ";
    public static final String SPACING = " ";
    public static final String DELIMITER = ",";


    public void printInitialCards(final ParticipantDto dealer, final List<ParticipantDto> players) {
        printInitialDealMessage(dealer, players);
        printTotalParticipant(dealer, players);
    }

    private void printInitialDealMessage(final ParticipantDto dealer, final List<ParticipantDto> players) {
        System.out.printf(System.lineSeparator() + "%s와 %s에게 %d장을 나누었습니다.",
                dealer.name(),
                PARSING.playerNames(players),
                INITIAL_CARD_COUNT
        );
    }

    private void printTotalParticipant(final ParticipantDto dealer, final List<ParticipantDto> players) {
        printParticipantHand(dealer);
        players.forEach(this::printParticipantHand);
    }

    public void printParticipantHand(final ParticipantDto participant) {
        CardsDto cards = participant.cardsDto();
        System.out.printf(System.lineSeparator() + "%s: %s" + System.lineSeparator(),
                participant.name(), PARSING.cards(cards));
    }

    public void printDealerCardMessage(final ParticipantDto dealer) {
        System.out.printf(System.lineSeparator() + "%s는 %s이하라 한장의 카드를 더 받습니다." + System.lineSeparator(),
                dealer.name(),
                DEALER_HIT_THRESHOLD
        );
    }

    public void printResults(final ParticipantDto dealer, final List<ParticipantDto> players, final GameResultDto gameResultDto) {
        printCardAndSum(dealer);
        players.forEach(this::printCardAndSum);
        printGameResults(gameResultDto);
    }

    private void printCardAndSum(final ParticipantDto participantDto) {
        CardsDto cards = participantDto.cardsDto();
        System.out.printf(System.lineSeparator() + "%s" + CONNECT + "%s - 결과" + CONNECT + "%d" + System.lineSeparator(),
                participantDto.name(),
                PARSING.cards(cards),
                cards.score()
        );
    }

    private void printGameResults(final GameResultDto gameResultDto) {
        Map<String, Integer> result = gameResultDto.participantReuslt();
        System.out.println(System.lineSeparator() + "## 최종 수익");
        System.out.println(PARSING.playerResult(result));
    }
}
