package backskin.bankapi.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The interface Sql mapper.
 *
 * @param <T> the type parameter
 */
public interface SqlMapper<T> {
    /**
     * Map t.
     *
     * @param set the set
     * @return the t
     * @throws SQLException the sql exception
     */
    T map(ResultSet set) throws SQLException;
}
