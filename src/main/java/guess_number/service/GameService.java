package guess_number.service;

import guess_number.models.Game;
import guess_number.models.Round;

import java.util.List;

public interface GameService {
    Game addGame();
    Round addRound(int guess, int gameId);
    List<Game> getAllGames();
    Game findGameById(int id);
    List<Round> getAllRounds(int id);

    boolean update(Game game);
    boolean deleteById(int id);
}
