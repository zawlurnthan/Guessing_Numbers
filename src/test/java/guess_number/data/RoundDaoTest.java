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
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Time;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
class RoundDaoTest {

    @Autowired
    RoundDao rDao;
    @Autowired
    GameDao gDao;
    Round first, second;

    @BeforeEach
    void setUp() {
        // create game
        Game game = new Game();
        game.setAnswer(8035);
        game.setStatus("In Progress");
        gDao.add(game);

        // create first round
        first = new Round();
        first.setGuess(3180);
        first.setTime(Time.valueOf(LocalTime.now()));
        first.setResult("e:0:p:3");
        first.setGame(game);

        // create second round
        second = new Round();
        second.setGuess(5901);
        second.setTime(Time.valueOf(LocalTime.now()));
        second.setResult("e:0:p:2");
        second.setGame(game);
    }

    @AfterEach
    void tearDown() {
        // delete all rounds
        List<Round> rounds = rDao.getAll();
        for (Round round : rounds) {
            rDao.deleteById(round.getRoundId());
        }

        // delete all games
        List<Game> games = gDao.getAll();
        for (Game game : games) {
            gDao.deleteById(game.getGameId());
        }
    }

    @Test
    void addAndGet() {
        Round round = rDao.add(first);
        Round retrieved = rDao.findById(round.getRoundId());
        assertEquals(round, retrieved, "check if objects are equal");
    }

    @Test
    void getAll() {
        rDao.add(first);
        rDao.add(second);
        List<Round> rounds = rDao.getAll();

        assertEquals(2, rounds.size(), "check the number of the list");
        assertTrue(rounds.contains(first), "check if it's first");
        assertTrue(rounds.contains(second), "check if it's second");
    }
}