//package guess_number.service;
//
//import guess_number.TestApplicationConfiguration;
//import guess_number.data.RoundDao;
//import guess_number.models.Round;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.sql.Time;
//import java.time.LocalTime;
//import java.util.ArrayList;
//import java.util.List;
//
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = TestApplicationConfiguration.class)
//public class RoundDaoStub implements RoundDao {
//
//    GameDaoStub gDao;
//    Round first;
//
//    public RoundDaoStub() {
//        // create a round
//        first = new Round();
//        first.setGuess(3180);
//        first.setTime(Time.valueOf(LocalTime.now()));
//        first.setResult("e:0:p:3");
//        first.setGame(gDao.stubGame);
//    }
//
//    @Override
//    public Round add(Round round) {
//        return round.getRoundId() == first.getRoundId() ? first : null;
//    }
//
//    @Override
//    public List<Round> getAll() {
//        List<Round> rounds = new ArrayList<>();
//        rounds.add(first);
//        return rounds;
//    }
//
//    @Override
//    public Round findById(int id) {
//        return id == first.getRoundId() ? first : null;
//    }
//
//    @Override
//    public boolean update(Round round) {
//        return round == first;
//    }
//
//    @Override
//    public boolean deleteById(int id) {
//        return id == first.getRoundId();
//    }
//}
