package backskin.bankapi.dao.mappers;

import backskin.bankapi.domain.BankClient;
import org.jetbrains.annotations.NotNull;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BankClientMapper implements RowMapper<BankClient> {
    @Override
    public BankClient mapRow(@NotNull ResultSet resultSet, int i) throws SQLException {
        return
    }
}
