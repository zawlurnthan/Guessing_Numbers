//package guess_number.service;
//
//import guess_number.TestApplicationConfiguration;
//import guess_number.data.GameDao;
//import guess_number.data.RoundDao;
//import guess_number.models.Game;
//import guess_number.models.Round;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.context.TestConfiguration;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.sql.Time;
//import java.time.LocalTime;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = TestApplicationConfiguration.class)
//class GameServiceTest {
//
//    @Autowired
//    GameService service;
//    GameDaoStub gDao;
//    RoundDaoStub rDao;
//
//
//    @BeforeEach
//    void setUp() {
//    }
//
//    @AfterEach
//    void tearDown() {
//        // delete all rounds
//        List<Round> rounds = rDao.getAll();
//        for (Round round : rounds) {
//            rDao.deleteById(round.getRoundId());
//        }
//        // delete all games
//        List<Game> games = gDao.getAll();
//        for (Game game : games) {
//            gDao.deleteById(game.getGameId());
//        }
//    }
//
//    @Test
//    void addGame() {
//        Game newGame = service.addGame();
//        Game retrieved = service.findGameById(newGame.getGameId());
//        assertEquals(newGame, retrieved, "check if two games are equal");
//    }
//
//    @Test
//    void addRound() {
//        Game newGame = service.addGame();
//        Round newRound = service.addRound(3058, newGame.getGameId());
//
//        Round retrieved = rDao.findById(newRound.getRoundId());
//        assertEquals(newRound, retrieved, "check if two rounds are equal");
//    }
//
//    @Test
//    void getAllGames() {
//    }
//
//    @Test
//    void findGameById() {
//    }
//
//    @Test
//    void getAllRounds() {
//    }
//
//    @Test
//    void update() {
//    }
//
//    @Test
//    void deleteById() {
//    }
//}