package guess_number.data;

import guess_number.models.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.util.List;


@Repository
public class GameDaoImpl implements GameDao{

    private final JdbcTemplate jdbc;

    @Autowired
    public GameDaoImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Game add(Game game) {
        final String sql = "INSERT INTO game(answer, status) VALUES(?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbc.update((Connection conn) -> {
            PreparedStatement statement = conn.prepareStatement(
                    sql, Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, game.getAnswer());
            statement.setString(2, game.getStatus());
            return statement;

        }, keyHolder);

        game.setGameId(keyHolder.getKey().intValue());
        return game;
    }

    @Override
    public List<Game> getAll() {
        final String sql = "SELECT * FROM game;";
        return jdbc.query(sql, new GameMapper());
    }

    @Override
    public Game findById(int id) {
        try {
            final String sql = "SELECT * FROM game WHERE gameId = ?;";
            return jdbc.queryForObject(sql, new GameMapper(), id);
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public boolean update(Game game) {
        final String sql = "UPDATE game SET status = ? WHERE gameId = ?;";
        return jdbc.update(sql, game.getStatus(), game.getGameId()) > 0;
    }

    @Override
    public boolean deleteById(int id) {
        final String sql = "DELETE FROM game WHERE gameId = ?;";
        return jdbc.update(sql, id) > 0;
    }

    private static final class GameMapper implements RowMapper<Game> {
        @Override
        public Game mapRow(ResultSet rs, int index) throws SQLException {
            Game game = new Game();
            game.setGameId(rs.getInt("gameId"));
            game.setAnswer(rs.getInt("answer"));
            game.setStatus(rs.getString("status"));
            return game;
        }
    }
}
