package guess_number.data;

import guess_number.models.Round;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.util.List;


@Repository
public class RoundDaoImpl implements RoundDao{

    private final JdbcTemplate jdbc;
    private final GameDao dao;

    @Autowired
    public RoundDaoImpl(JdbcTemplate jdbc, GameDao dao) {
        this.jdbc = jdbc;
        this.dao = dao;
    }

    @Override
    public Round add(Round round) {
        final String sql = "INSERT INTO round(guess, time, result, gameId) VALUES(?, ?, ?, ?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbc.update((Connection conn) -> {
            PreparedStatement statement = conn.prepareStatement(
                    sql, Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, round.getGuess());
            statement.setTime(2, round.getTime());
            statement.setString(3, round.getResult());
            statement.setInt(4, round.getGame().getGameId());
            return statement;

        }, keyHolder);

        round.setRoundId(keyHolder.getKey().intValue());
        return round;
    }

    @Override
    public List<Round> getAll() {
        final String sql = "SELECT * FROM round;";
        return jdbc.query(sql, new RoundMapper());
    }

    @Override
    public Round findById(int id) {
        final String sql = "SELECT * FROM round WHERE roundId = ?;";
        return jdbc.queryForObject(sql, new RoundMapper(), id);
    }

    @Override
    public boolean update(Round round) {
        final String sql = "UPDATE round SET " +
                "guess = ?, " +
                "time = ?, " +
                "result = ?, " +
                "gameId = ? " +
                "WHERE roundId = ?;";

        return jdbc.update(sql,
                round.getGuess(),
                round.getTime(),
                round.getResult(),
                round.getGame().getGameId(),
                round.getRoundId()) > 0;
    }

    @Override
    public boolean deleteById(int id) {
        final String sql = "DELETE FROM round WHERE roundId = ?;";
        return jdbc.update(sql, id) > 0;
    }

    private final class RoundMapper implements RowMapper<Round> {

        @Override
        public Round mapRow(ResultSet rs, int index) throws SQLException {
            Round round = new Round();
            round.setRoundId(rs.getInt("roundId"));
            round.setGuess(rs.getInt("guess"));
            round.setTime(rs.getTime("time"));
            round.setResult(rs.getString("result"));
            int id = rs.getInt("gameId");
            round.setGame(dao.findById(id));
            return round;
        }
    }
}
