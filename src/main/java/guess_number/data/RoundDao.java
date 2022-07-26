package guess_number.data;

import guess_number.models.Round;
import java.util.List;

public interface RoundDao {
    Round add(Round round);
    List<Round> getAll();
    Round findById(int id);
    boolean update(Round round);
    boolean deleteById(int id);
}
