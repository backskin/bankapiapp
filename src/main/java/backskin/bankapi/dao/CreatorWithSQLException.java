package backskin.bankapi.dao;
import java.sql.SQLException;

interface CreatorWithSQLException<T> {
    T create() throws SQLException;
}
