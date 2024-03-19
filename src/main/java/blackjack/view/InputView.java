package blackjack.view;

import blackjack.model.betting.BettingMoney;
import blackjack.model.player.PlayerName;
import blackjack.view.form.Command;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String ASK_PLAYER_NAMES = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String ASK_BETTING_MONEY_TO_PLAYER = "\n%s의 베팅 금액은?\n";
    private static final String ASK_DRAW_OR_STAND_COMMAND_TO_PLAYER = "\n%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)\n";
    private static final String INVALID_BETTING_MONEY_AMOUNT = "베팅 금액은 정수로 입력해야 합니다.";

    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public List<PlayerName> askPlayerNames() {
        System.out.println(ASK_PLAYER_NAMES);
        String input = scanner.nextLine();
        return convertInputToPlayerNames(input);
    }

    private List<PlayerName> convertInputToPlayerNames(final String input) {
        return Arrays.stream(input.split(","))
                .map(String::trim)
                .map(PlayerName::new)
                .toList();
    }

    public BettingMoney askBettingMoneyToPlayer(final String playerName) {
        System.out.printf(ASK_BETTING_MONEY_TO_PLAYER, playerName);
        String input = scanner.nextLine();
        return convertInputToBettingMoney(input);
    }

    private BettingMoney convertInputToBettingMoney(final String input) {
        int amount = parseInputToAmount(input);
        return new BettingMoney(amount);
    }

    private int parseInputToAmount(final String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_BETTING_MONEY_AMOUNT);
        }
    }

    public Command askDrawOrStandCommandToPlayer(final String name) {
        System.out.printf(ASK_DRAW_OR_STAND_COMMAND_TO_PLAYER, name);
        String input = scanner.nextLine();
        return Command.from(input);
    }
}
