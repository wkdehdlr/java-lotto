package step2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;
import step2.domain.Game;

public class GameTest {

    @Test
    void generateGame() {
        int length = 6;
        Game game = new Game(length);

        Set<Number> numbers = new HashSet<>(game.numbers);

        assertEquals(6, numbers.size());
    }
}
