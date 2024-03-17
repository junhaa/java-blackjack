package domain.gamer;

import domain.money.Money;
import domain.result.GamerResult;

public class Player extends Gamer {

    private static final double BLACKJACK_BONUS_RATIO = 1.5;

    private final Money money;

    Player(Name name) {
        super(name);
        this.money = new Money(0);
    }

    public Player(Name name, Money money) {
        super(name);
        this.money = money;
    }

    @Override
    public boolean canHit() {
        return !this.isBust();
    }

    public Money bet(Dealer dealer) {
        GamerResult result = judge(dealer);
        if (GamerResult.WIN.equals(result)) {
            return gainMoney();
        }
        if (GamerResult.LOSE.equals(result)) {
            return loseMoney();
        }
        return money;
    }

    public GamerResult judge(Dealer opponent) {
        return judgeBust(opponent);
    }

    private GamerResult judgeBust(Dealer opponent) {
        if (this.isBust()) {
            return GamerResult.LOSE;
        }
        if (opponent.isBust()) {
            return GamerResult.WIN;
        }
        return judgeBlackJack(opponent);
    }

    private GamerResult judgeBlackJack(Dealer opponent) {
        if (this.isBlackJack() && opponent.isBlackJack()) {
            return GamerResult.DRAW;
        }
        if (this.isBlackJack()) {
            return GamerResult.WIN;
        }
        if (opponent.isBlackJack()) {
            return GamerResult.LOSE;
        }
        return judgeScore(opponent);
    }

    private GamerResult judgeScore(Dealer opponent) {
        if (this.getTotalScore() > opponent.getTotalScore()) {
            return GamerResult.WIN;
        }
        if (this.getTotalScore() < opponent.getTotalScore()) {
            return GamerResult.LOSE;
        }
        return GamerResult.DRAW;
    }

    private Money gainMoney() {
        if (isBlackJack()) {
            return money.multiply(BLACKJACK_BONUS_RATIO);
        }
        return money;
    }

    private Money loseMoney() {
        return money.negative();
    }
}
