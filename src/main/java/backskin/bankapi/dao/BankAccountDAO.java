package backskin.bankapi.dao;

import backskin.bankapi.domain.BankAccount;
import org.jetbrains.annotations.NotNull;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BankAccountDAO extends AbstractDAO<BankAccount> {
    @Override
    protected String getTableName() {
        return null;
    }

    @Override
    protected RowMapper<BankAccount> getMapper() {
        return new BankAccountMapper();
    }

    @Override
    public BankAccount read(Long id) throws SQLException {
        return null;
    }

    @Override
    public void update(BankAccount entity, Long aLong) throws SQLException {

    }
}
