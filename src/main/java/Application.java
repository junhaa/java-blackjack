import blackjack.controller.BlackjackController;
import blackjack.domain.Blackjack;

public class Application {
    public static void main(final String[] args) {
        final BlackjackController blackjackController = new BlackjackController(new Blackjack());
        blackjackController.playBlackJack();
    }
}
