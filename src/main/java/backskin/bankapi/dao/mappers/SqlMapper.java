package backskin.bankapi.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface SqlMapper<T> {
    T map(ResultSet set) throws SQLException;
}
