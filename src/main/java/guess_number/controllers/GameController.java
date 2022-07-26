package guess_number.controllers;

import guess_number.models.Game;
import guess_number.models.Round;
import guess_number.service.GameServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api")
public class GameController {

    private final GameServiceImpl service;

    @Autowired
    public GameController(GameServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/begin")
    @ResponseStatus(HttpStatus.CREATED)
    public Game begin() {
        return service.addGame();
    }

    @PostMapping("/guess")
    public Round guess(@RequestParam int guess, int gameId) {
        return service.addRound(guess, gameId);
    }

    @GetMapping("/game")
    public List<Game> all() {
        return service.getAllGames();
    }

    @GetMapping("/game/{id}")
    public Game getGame(@PathVariable int id) {
        return service.findGameById(id);
    }

    @GetMapping("/rounds/{id}")
    public List<Round> getAllRounds(@PathVariable int id) {
        return service.getAllRounds(id);
    }
}
