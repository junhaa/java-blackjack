package domain.gamer;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public record Name(String name) {

    private static final Pattern pattern = Pattern.compile("^[^가-힣a-zA-Z-_0-9].*$");
    private static final int MAX_NAME_LENGTH = 5;

    public Name {
        validateName(name);
    }

    private void validateName(String name) {
        validateEmpty(name);
        validateLength(name);
        validateCharacter(name);
    }

    private void validateEmpty(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("이름에 공백이나 null을 넣을 수 없습니다.");
        }
    }

    private void validateLength(String name) {
        if (name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("이름의 길이는 5글자 이하여야 합니다.");
        }
    }

    private void validateCharacter(String name) {
        Matcher matcher = pattern.matcher(name);
        if (matcher.matches()) {
            throw new IllegalArgumentException("이름은 알파벳, 한글, 숫자, '_', '-'로만 이루어져야 합니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Name that = (Name) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
