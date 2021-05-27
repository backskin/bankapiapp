package backskin.bankapi.dao;

import backskin.bankapi.dao.mappers.AbstractMapper;
import backskin.bankapi.dao.mappers.BankAccountMapper;
import backskin.bankapi.domain.BankAccount;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Getter
@Repository
public class BankAccountDAO extends AbstractDAO<BankAccount> {
    private final String tableName;
    BankAccountMapper mapper;
    private Connection connection;

    @Autowired
    public void setConnection(Connection connection){
        this.connection = connection;
    }

    @Override
    public AbstractMapper<BankAccount> getMapper() {
        return mapper;
    }

    @Autowired
    public BankAccountDAO(@Qualifier("bankAccountsTableName") String tableName) {
        this.tableName = tableName;
    }

    @Autowired
    public void setMapper(BankAccountMapper mapper){
        this.mapper = mapper;
    }

    @Override
    public void update(BankAccount entity, Long id) throws SQLException {
        String sqlQuery = "UPDATE ? SET ?,?,? WHERE ?";
        PreparedStatement statement = getConnection().prepareStatement(sqlQuery);
        statement.setString(1, getTableName());
        statement.setString(2,
               mapper.getNumberValidator().validationRule(entity.getNumber()));
        statement.setString(3,
                mapper.getBalanceValidator().validationRule(entity.getBalance()));
        statement.setString(4,
                mapper.getClientIdValidator().validationRule(entity.getBankClientId()));
        statement.setString(5,
                mapper.getIdValidator().validationRule(entity.getId()));
        statement.execute();
        connection.commit();
    }
}
