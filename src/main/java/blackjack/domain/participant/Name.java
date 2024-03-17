package blackjack.domain.participant;

import java.util.Set;
import java.util.regex.Pattern;

public record Name(String name) {

    private static final Pattern PATTERN = Pattern.compile("^[a-zA-Z가-힣]+(?:\\s+[a-zA-Z가-힣]+)*$");
    private static final String INVALID_NAME_FORMAT_EXCEPTION = "이름 형식에 맞지 않습니다. 이름은 영어 또는 한글로 입력해주세요.";
    private static final String RESERVED_NAME_EXCEPTION = "%s라는 이름으로 지을 수 없습니다. 다른 이름을 입력해주세요.";
    private static final Set<String> reservedNames = Set.of("딜러");

    public Name {
        validate(name);
    }

    private void validate(final String name) {
        if (!PATTERN.matcher(name).matches()) {
            throw new IllegalArgumentException(INVALID_NAME_FORMAT_EXCEPTION);
        }
        if (reservedNames.contains(name)) {
            throw new IllegalArgumentException(String.format(RESERVED_NAME_EXCEPTION, name));
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
