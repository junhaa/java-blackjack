package blackjack.view;

import blackjack.view.validator.InputValidator;

import java.util.Scanner;

import static blackjack.utils.constants.ExpressionConstants.EXPRESSION_OF_HIT;
import static blackjack.utils.constants.ExpressionConstants.EXPRESSION_OF_STAY;

public class InputView {
    private static Scanner scanner = new Scanner(System.in);
    private final InputValidator inputValidator;

    public InputView(InputValidator inputValidator) {
        this.inputValidator = inputValidator;
    }

    public String readPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String input = scanner.nextLine();

        inputValidator.validatePlayerNames(input);
        return input;
    }

    public int readBattingAmount(String name) {
        System.out.printf("%n%s의 배팅 금액은?%n", name);
        String input = scanner.nextLine();

        inputValidator.validateBattingAmount(input);
        return Integer.parseInt(input);
    }

    public String readReceiveMoreCardOrNot(String name) {
        String message = String.format(
                "%s는 한장의 카드를 더 받겠습니까?(예는 %s, 아니오는 %s)", name, EXPRESSION_OF_HIT, EXPRESSION_OF_STAY);
        System.out.println(message);
        String input = scanner.nextLine();

        inputValidator.validateReceiveMoreCardOrNot(input);
        return input;
    }
}
