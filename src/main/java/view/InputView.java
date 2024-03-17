package view;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class InputView {

    private static final String DELIMITER = ",";
    private static final String COMMAND_YES = "y";
    private static final String COMMAND_NO = "n";
    private static final double MINIMUM_BETTING_AMOUNT = 1;

    public static List<String> readNames(Supplier<String> input) {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String result = input.get();
        validateEmpty(result);
        validateDelimiter(result);
        List<String> splitInput = Arrays.stream(result.split(DELIMITER))
                .map(String::trim)
                .toList();
        validateDuplicate(splitInput);
        return splitInput;
    }

    private static void validateDuplicate(List<String> splitInput) {
        if (splitInput.stream().distinct().count() != splitInput.size()) {
            throw new IllegalArgumentException("이름은 중복될 수 없습니다.");
        }
    }

    private static void validateEmpty(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("입력값에 공백이나 null을 넣을 수 없습니다.");
        }
    }

    private static void validateDelimiter(String input) {
        if (input.endsWith(DELIMITER)) {
            throw new IllegalArgumentException("입력값 끝에 쉼표(,)를 넣을 수 없습니다.");
        }
    }

    public static String readAnswer(Supplier<String> input, String name) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n", name);
        String result = input.get();
        validateEmpty(result);
        validateAnswer(result);
        return result;
    }

    private static void validateAnswer(String input) {
        if (!COMMAND_YES.equals(input) && !COMMAND_NO.equals(input)) {
            throw new IllegalArgumentException("y또는 n만 입력 받을 수 있습니다.");
        }
    }

    public static double readBettingMoney(Supplier<String> input, String name) {
        System.out.printf("%s의 배팅 금액은?%n", name);
        String result = input.get();
        validateEmpty(result);
        validateNumeric(result);
        double amount = Double.parseDouble(result);
        validateAmount(amount);
        return Double.parseDouble(result);
    }

    private static void validateNumeric(String amount) {
        try {
            Double.parseDouble(amount);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("금액과 관련된 연산은 숫자만 입력 가능합니다.");
        }
    }

    private static void validateAmount(double amount) {
        if (amount < MINIMUM_BETTING_AMOUNT) {
            throw new IllegalArgumentException("최소 금액보다 작은 금액으로 배팅할 수 없습니다.");
        }
    }
}
