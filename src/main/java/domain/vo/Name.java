package domain.vo;

public record Name(String value) {

    public Name {
        validateNotBlank(value);
    }

    public Name(final Name name) {
        this(name.value);
    }

    private void validateNotBlank(final String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("플레이어 이름을 입력해주세요. 예) 포비, 호티, 제우스");
        }
    }
}
