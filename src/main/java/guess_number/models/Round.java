package guess_number.models;

import java.sql.Time;

public class Round {
    private int roundId;
    private int guess;
    private Time time;
    private String result;
    private Game game;

    public int getRoundId() {
        return roundId;
    }

    public void setRoundId(int roundId) {
        this.roundId = roundId;
    }

    public int getGuess() {
        return guess;
    }

    public void setGuess(int guess) {
        this.guess = guess;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Round round)) return false;

        if (getRoundId() != round.getRoundId()) return false;
        if (getGuess() != round.getGuess()) return false;
        if (!getTime().equals(round.getTime())) return false;
        if (!getResult().equals(round.getResult())) return false;
        return getGame().equals(round.getGame());
    }

    @Override
    public int hashCode() {
        int result1 = getRoundId();
        result1 = 31 * result1 + getGuess();
        result1 = 31 * result1 + getTime().hashCode();
        result1 = 31 * result1 + getResult().hashCode();
        result1 = 31 * result1 + getGame().hashCode();
        return result1;
    }
}
