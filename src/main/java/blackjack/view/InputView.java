package blackjack.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class InputView {

    private static final String PLAYER_NAME_INPUT = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String BETTING_INPUT = System.lineSeparator() + "%s의 배팅 금액은?" + System.lineSeparator();
    private static final String MORE_CARD_CHOICE = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";
    private static final String NAME_SEPARATOR = ",";
    private static final String IOEXCEPTION_ERROR = "입력 과정 도중 에러가 발생했습니다.";

    private static final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static final InputView instance = new InputView();

    private InputView() {
    }

    public static InputView getInstance() {
        return instance;
    }

    public List<String> readPlayersName() {
        System.out.println(PLAYER_NAME_INPUT);
        try {
            return Arrays.stream(bufferedReader.readLine().split(NAME_SEPARATOR))
                    .map(String::trim)
                    .toList();
        } catch (IOException exception) {
            throw new IllegalArgumentException(IOEXCEPTION_ERROR);
        }
    }

    public String readPlayerBetting(final String name) {
        System.out.printf(BETTING_INPUT, name);

        try {
            return bufferedReader.readLine().trim();
        } catch (IOException exception) {
            throw new IllegalArgumentException(IOEXCEPTION_ERROR);
        }
    }

    public String readMoreCardChoice(final String name) {
        System.out.printf(MORE_CARD_CHOICE + System.lineSeparator(), name);
        try {
            return bufferedReader.readLine();
        } catch (IOException exception) {
            throw new IllegalArgumentException(IOEXCEPTION_ERROR);
        }
    }
}
