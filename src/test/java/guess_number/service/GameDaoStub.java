//package guess_number.service;
//
//import guess_number.TestApplicationConfiguration;
//import guess_number.data.GameDao;
//import guess_number.models.Game;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = TestApplicationConfiguration.class)
//public class GameDaoStub implements GameDao {
//
//    Game stubGame;
//
//    public GameDaoStub() {
//        // create a game
//        stubGame = new Game();
//        stubGame.setAnswer(8035);
//        stubGame.setStatus("In Progress");
//    }
//
//    @Override
//    public Game add(Game game) {
//    return game.getGameId() == stubGame.getGameId() ? stubGame : null;
//    }
//
//    @Override
//    public List<Game> getAll() {
//        List<Game> games = new ArrayList<>();
//        games.add(stubGame);
//        return games;
//    }
//
//    @Override
//    public Game findById(int id) {
//        return id == stubGame.getGameId() ? stubGame : null;
//    }
//
//    @Override
//    public boolean update(Game game) {
//        return game == stubGame;
//    }
//
//    @Override
//    public boolean deleteById(int id) {
//        return id == stubGame.getGameId();
//    }
//}
