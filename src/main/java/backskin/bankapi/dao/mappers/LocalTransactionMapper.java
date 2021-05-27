package backskin.bankapi.dao.mappers;

import backskin.bankapi.domain.LocalTransaction;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class LocalTransactionMapper implements RowMapper<LocalTransaction> {
    @Override
    public LocalTransaction mapRow(ResultSet resultSet, int i) throws SQLException {
        return null;
    }
}
