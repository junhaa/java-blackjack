package blackjack.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import static blackjack.view.OutputView.LINE_SEPARATOR;

public class InputView {
    private static final String NAME_DELIMITER = ",";
    private static final String ASK_PLAYER_NAMES = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String ASK_BETTING_MONEY = "%s의 배팅 금액은?";
    private static final String ASK_ONE_MORE_CARD = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";
    private static final String IO_ERROR = "입력이 잘못되었습니다.";
    private static final String NO_CONVERT_INTEGER = "정수를 입력해 주세요.";

    public static List<String> readPlayersName() {
        System.out.println(ASK_PLAYER_NAMES);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return parseNames(bufferedReader.readLine());
        } catch (IOException e) {
            throw new IllegalArgumentException(IO_ERROR);
        }
    }

    private static List<String> parseNames(String names) {
        return Arrays.stream(names.split(NAME_DELIMITER)).toList();
    }

    public static int readBettingChip(String name) {
        System.out.println(String.format(ASK_BETTING_MONEY, name));
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return Integer.parseInt(bufferedReader.readLine());
        } catch (IOException e) {
            throw new IllegalArgumentException(IO_ERROR);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NO_CONVERT_INTEGER);
        }
    }

    public static String readPlayerCommand(String name) {
        System.out.printf(LINE_SEPARATOR + ASK_ONE_MORE_CARD + LINE_SEPARATOR, name);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            throw new IllegalArgumentException(IO_ERROR);
        }
    }
}
