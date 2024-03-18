package domain.betting;

import static domain.game.PlayerResult.BLACKJACK;
import static domain.game.PlayerResult.LOSE;
import static domain.game.PlayerResult.TIE;
import static domain.game.PlayerResult.WIN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MoneyTest {

    @ParameterizedTest
    @ValueSource(ints = {1_000, 1_000_000_000})
    @DisplayName("생성 성공: 경계값(1_000, 1_000_000_000)")
    void money_NoException(int money) {
        assertThatCode(
            () -> Money.withBetAmount(money)
        ).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 999, 1_000_000_001})
    @DisplayName("생성 실패: 값 범위 위반")
    void money_Exception_ExceedMax(int money) {
        assertThatThrownBy(() -> Money.withBetAmount(money))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("[ERROR] 1,000원 이상 1,000,000,000원 이하로 베팅해 주세요.");
    }

    @Test
    @DisplayName("생성 실패: 1,000원 단위 위반")
    void money_Exception_NoThousands() {
        assertThatThrownBy(() -> Money.withBetAmount(1100))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("[ERROR] 1,000원 단위로 베팅해 주세요.");
    }

    @Test
    @DisplayName("플레이어가 이긴 경우 1배의 수익 발생")
    void findPlayerProfitWhenPlayerWin() {
        Money money = Money.withBetAmount(1_000_000_000);
        assertThat(money.calculateProfit(WIN).toInt()).isEqualTo(1_000_000_000);
    }

    @Test
    @DisplayName("비긴 경우 0의 수익 발생")
    void findPlayerProfitWhenTie() {
        Money money = Money.withBetAmount(1_000_000_000);
        assertThat(money.calculateProfit(TIE).toInt()).isEqualTo(0);
    }

    @Test
    @DisplayName("플레이어가 진 경우 1배의 손해 발생")
    void findPlayerProfitWhenPlayerLose() {
        Money money = Money.withBetAmount(1_000_000_000);
        assertThat(money.calculateProfit(LOSE).toInt()).isEqualTo(-1_000_000_000);
    }

    @Test
    @DisplayName("플레이어가 블랙잭인 경우 1.5배의 수익 발생")
    void findPlayerProfitWhenPlayerBlackjack() {
        Money money = Money.withBetAmount(1_000_000_000);
        assertThat(money.calculateProfit(BLACKJACK).toInt()).isEqualTo(1_500_000_000);
    }

    @Test
    @DisplayName("돈을 더할 수 있다")
    void add() {
        Money money = Money.withBetAmount(1000);
        Money addedMoney = money.add(Money.withBetAmount(1000));
        assertThat(addedMoney.toInt()).isEqualTo(2000);
    }

    @Test
    @DisplayName("돈의 음수를 구할 수 있다")
    void negative() {
        Money money = Money.valueOf(1000);
        Money negativeMoney = money.negative();
        assertThat(negativeMoney.toInt()).isEqualTo(-1000);
    }
}

