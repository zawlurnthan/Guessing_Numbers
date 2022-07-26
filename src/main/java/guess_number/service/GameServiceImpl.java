package guess_number.service;

import guess_number.data.GameDao;
import guess_number.data.RoundDao;
import guess_number.models.Game;
import guess_number.models.Round;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Time;
import java.time.LocalTime;
import java.util.*;


@Service
public class GameServiceImpl implements GameService {

    private final GameDao gDao;
    private final RoundDao rDao;

    @Autowired
    public GameServiceImpl(GameDao gDao, RoundDao rDao) {
        this.gDao = gDao;
        this.rDao = rDao;
    }

    @Override
    public Game addGame() {
        // create game
        Game game = new Game();
        game.setAnswer(generateNumber());
        game.setStatus("In Progress");
        // add game to the database
        Game gameToShow = gDao.add(game);
        // hide answer
        gameToShow.setAnswer(0);
        return gameToShow;
    }


    @Override
    public Round addRound(int guess, int gameId) {
        // get the game by id
        Game game = gDao.findById(gameId);

        if (game != null) {
            int answer = game.getAnswer();
            // create a round
            Round round = new Round();
            // set round properties
            round.setGuess(guess);
            round.setTime(getCurrentTime());
            round.setResult(getResult(guess, answer));

            // reset game status as finished if guess is correct
            if (guess == answer) {
                game.setStatus("Finished");
                gDao.update(game);
            }
            // set the game of the round
            round.setGame(game);
            // add round to database
            Round roundToShow = rDao.add(round);
            // hide answer if game is in progress
            if (guess != answer) {
                roundToShow.getGame().setAnswer(0);
            }
            return roundToShow;
        } else {
            return null;
        }
    }

    @Override
    public List<Game> getAllGames() {
        List<Game> games = gDao.getAll();
        // hide answer if games are in progress
        return games.stream().peek(x -> {
            if (x.getStatus().equals("In Progress")) {
                x.setAnswer(0);
            }
        }).toList();
    }

    @Override
    public Game findGameById(int id) {
        Game game = gDao.findById(id);
        // hide answer if game is in progress
        if (game != null && game.getStatus().equals("In Progress")) {
            game.setAnswer(0);
        }
        return game;
    }

    @Override
    public List<Round> getAllRounds(int id) {
        // get all rounds of a game
        List<Round> rounds = rDao.getAll()
                .stream()
                .filter(x -> x.getGame().getGameId() == id)
                .toList();

        return rounds.stream().peek(x -> {
            // set answer o if game is in progress
            Game game = x.getGame();
            if (game.getStatus().equals("In Progress")) {
                game.setAnswer(0);
            }
        }).toList();
    }

    private int generateNumber() {
        int number;
        // loop if there is a duplicated digits
        do {
            int min = 1000, max = 10000;
            int range = max - min;
            Random random = new Random();
            // generate 4-digit number from 1000 to 9999 (10000 is not inclusive.)
            number = random.nextInt(range) + min;
            // check whether number is a distinct 4-digits
        } while (countUniqueDigits(number) != 4);
        return number;
    }

    private long countUniqueDigits(int num) {
        // convert number to string
        String input = Integer.toString(num);
        // split string, keep it in an array and count distinct digits
        return Arrays.stream(input.split("")).distinct().count();
    }

    private String getResult(int input, int generate) {
        // convert number into a string array
        String[] genStr = Integer.toString(generate).split("");
        String[] inStr = Integer.toString(input).split("");
        // declare memory locations and a container
        long match, exact, partial;
        Map<Integer, Integer> map = new LinkedHashMap<>();

        // count similarity between two number
        match = Arrays
                .stream(genStr)
                .filter(x -> Integer.toString(input).contains(x))
                .count();

        // get indexes of each match and keep them in a map
        for (int i = 0; i < inStr.length; i++) {
            for (int g = 0; g < genStr.length; g++) {
                if (inStr[i].equals(genStr[g])){
                    map.put(i,g);
                }
            }
        }
        // count exact match
        exact = map.entrySet().stream().filter(x -> x.getKey().equals(x.getValue())).count();
        // get partial match
        partial = match - exact;

        return String.format("e:%d:p:%d", exact, partial);
    }

    private Time getCurrentTime() {
        return Time.valueOf(LocalTime.now());
    }

    @Override
    public boolean update(Game game) {
        return gDao.update(game);
    }

    @Override
    public boolean deleteById(int id) {
        return gDao.deleteById(id);
    }

}
