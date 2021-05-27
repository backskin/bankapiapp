package backskin.bankapi.dao;

import backskin.bankapi.domain.BankClient;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class BankClientDAO extends AbstractDAO<BankClient> {

    private final String tableName;

    @Override
    protected String getTableName() {
        return tableName;
    }


    public BankClientDAO(@Qualifier("bankClientsTableName") String tableName) {
        this.tableName = tableName;
    }

    @Override
    protected RowMapper<BankClient> getMapper() {
        return new BankClientMapper();
    }

    @Override
    public void update(BankClient entity, Long aLong) throws Exception {

    }
}
