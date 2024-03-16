package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class InputView {
    private static final String YES = "y";
    private static final String NO = "n";
    static final String SHOULD_NUMBER = "숫자를 입력해 주세요.";
    static final String INVALID_FORMAT = "y 혹은 n만 입력할 수 있습니다.";

    private final Supplier<String> reader;

    public InputView(Supplier<String> reader) {
        this.reader = reader;
    }

    public List<String> readPlayerName() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String names = reader.get();
        System.out.println();
        return parsePlayerNames(names);
    }

    private List<String> parsePlayerNames(final String names) {
        return Arrays.stream(names.split(","))
                .toList();
    }

    public int readBetMoney(final String playerName) {
        System.out.println(playerName +"의 배팅 금액은?");
        String betMoney = reader.get();
        System.out.println();
        return parseBetMoney(betMoney);
    }

    private int parseBetMoney(final String betAmount) {
        try {
            return Integer.parseInt(betAmount);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(SHOULD_NUMBER);
        }
    }

    public boolean readHitOrNot(final String playerName) {
        System.out.println(playerName + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        String input = reader.get();
        validateHitOrNotCommand(input);
        return YES.equals(input);
    }

    private void validateHitOrNotCommand(final String input) {
        if (!YES.equals(input) && !NO.equals(input)) {
            throw new IllegalArgumentException(INVALID_FORMAT);
        }
    }
}
