package guess_number.models;

public class Game {
    private int gameId;
    private int answer;
    private String status;

    public Game() {
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Game game)) return false;

        if (getGameId() != game.getGameId()) return false;
        if (getAnswer() != game.getAnswer()) return false;
        return getStatus().equals(game.getStatus());
    }

    @Override
    public int hashCode() {
        int result = getGameId();
        result = 31 * result + getAnswer();
        result = 31 * result + getStatus().hashCode();
        return result;
    }
}
