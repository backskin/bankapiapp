package backskin.bankapi.dao;

import backskin.bankapi.dao.mappers.LocalTransactionSqlMapper;
import backskin.bankapi.domain.LocalTransaction;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Getter
@Repository
public class LocalTransactionDAO extends AbstractDAO<LocalTransaction> {

    private LocalTransactionSqlMapper mapper;
    private final String tableName;
    private Connection connection;

    @Autowired
    public void setMapper(LocalTransactionSqlMapper mapper) {
        this.mapper = mapper;
    }
    @Autowired
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public LocalTransactionDAO(String localTransactionsTableName) {
        this.tableName = localTransactionsTableName;
    }

    @Override
    public void update(LocalTransaction entity, Long aLong) throws SQLException {
        String sqlQuery = "UPDATE ? SET ?,?,?,? WHERE ?";
        PreparedStatement statement = connection.prepareStatement(sqlQuery);
        statement.setString(1, getTableName());
        statement.setString(2,
                mapper.getAccountIdValidator().validationRule(entity.getBankAccountId()));
        statement.setString(3,
                mapper.getDateValidator().validationRule(entity.getDate()));
        statement.setString(4,
                mapper.getDifferenceValidator().validationRule(entity.getTransactionDifference()));
        statement.setString(5,
                mapper.getDetailsValidator().validationRule(entity.getDetails()));
        statement.setString(6,
                mapper.getIdValidator().validationRule(entity.getId()));
        statement.execute();
        connection.commit();
    }
}
