package backskin.bankapi.dao;

import backskin.bankapi.domain.BankAccount;
import org.jetbrains.annotations.NotNull;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BankAccountMapper implements RowMapper<BankAccount> {
    @Override
    public BankAccount mapRow(@NotNull ResultSet resultSet, int i) throws SQLException {
        return null;
    }
}
