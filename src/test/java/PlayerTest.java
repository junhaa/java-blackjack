import static org.assertj.core.api.Assertions.assertThat;

import domain.BlackjackDeckGenerator;
import domain.CardValue;
import domain.Deck;
import domain.DrawPolicy;
import domain.Player;
import domain.Suit;
import domain.TrumpCard;
import domain.strategy.TestDrawStrategy;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class PlayerTest {

    @Test
    void 카드의_합이_21_이하면_버스트가_아니다() {
        Deque<TrumpCard> trumpCards = new LinkedList<>(
                List.of(new TrumpCard(Suit.DIAMOND, CardValue.EIGHT), new TrumpCard(Suit.DIAMOND, CardValue.J)));
        Deck deck = new Deck(new BlackjackDeckGenerator(), new TestDrawStrategy(trumpCards));
        Player player = new Player(new DrawPolicy());
        player.addDraw(deck.drawCard());
        assertThat(player.isBurst()).isFalse();
    }
}
