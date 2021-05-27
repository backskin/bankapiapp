package backskin.bankapi.dao.mappers;

import java.sql.SQLException;

public interface SqlMapper<T, K> {
    T map(K set) throws SQLException;
}
