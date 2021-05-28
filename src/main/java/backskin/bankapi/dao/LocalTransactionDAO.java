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
        String sqlQuery = String.format("UPDATE %s SET %s,%s,%s,%s WHERE %s",
                getTableName(),
                mapper.getAccountIdValidator().validationRule(entity.getBankAccountId()),
                mapper.getDateValidator().validationRule(entity.getDate()),
                mapper.getDifferenceValidator().validationRule(entity.getTransactionDifference()),
                mapper.getDetailsValidator().validationRule(entity.getDetails()),
                mapper.getIdValidator().validationRule(entity.getId()));

        PreparedStatement statement = connection.prepareStatement(sqlQuery);
        statement.execute();
        connection.commit();
    }
}
