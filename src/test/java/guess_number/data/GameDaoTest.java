package guess_number.data;

import guess_number.TestApplicationConfiguration;
import guess_number.models.Game;
import guess_number.models.Round;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
class GameDaoTest {

    @Autowired
    GameDao gDao;

    Game first, second;

    @BeforeEach
    void setUp() {
        // create games
        first = new Game();
        first.setAnswer(1583);
        first.setStatus("In Progress");

        second = new Game();
        second.setAnswer(3190);
        second.setStatus("In Progress");
    }

    @AfterEach
    void tearDown() {
        // delete all games
        List<Game> games = gDao.getAll();
        for (Game game : games) {
            gDao.deleteById(game.getGameId());
        }
    }

    @Test
    void addAndGet() {
        Game game = gDao.add(first);
        Game retrieved = gDao.findById(game.getGameId());
        assertEquals(game, retrieved, "check if two games are equal");
    }

    @Test
    void getAll() {
        gDao.add(first);
        gDao.add(second);
        List<Game> games = gDao.getAll();

        assertEquals(2, games.size(), "check the size of list");
        assertTrue(games.contains(first), "check if first contains");
        assertTrue(games.contains(second), "Check if second contains");

    }
}