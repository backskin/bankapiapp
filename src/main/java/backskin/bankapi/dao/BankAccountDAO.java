package backskin.bankapi.dao;

import backskin.bankapi.dao.mappers.AbstractSqlMapper;
import backskin.bankapi.dao.mappers.BankAccountSqlMapper;
import backskin.bankapi.domain.BankAccount;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Getter
@Repository
public class BankAccountDAO extends AbstractDAO<BankAccount> {
    private final String tableName;
    BankAccountSqlMapper mapper;
    private Connection connection;

    @Autowired
    public void setConnection(Connection connection){
        this.connection = connection;
    }

    @Override
    public String getTableName() {
        return tableName;
    }

    @Override
    public AbstractSqlMapper<BankAccount> getMapper() {
        return mapper;
    }

    @Autowired
    public BankAccountDAO(String bankAccountsTableName) {
        this.tableName = bankAccountsTableName;
    }

    @Autowired
    public void setMapper(BankAccountSqlMapper mapper){
        this.mapper = mapper;
    }

    @Override
    public void update(BankAccount entity, Long id) throws SQLException {
        String sqlQuery = String.format("UPDATE %s SET %s,%s,%s WHERE %s",
                getTableName(),
                mapper.getNumberValidator().validationRule(entity.getNumber()),
                mapper.getBalanceValidator().validationRule(entity.getBalance()),
                mapper.getClientIdValidator().validationRule(entity.getBankClientId()),
                mapper.getIdValidator().validationRule(entity.getId()));
        PreparedStatement statement = getConnection().prepareStatement(sqlQuery);
        statement.execute();
        connection.commit();
    }
}
