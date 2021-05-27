package backskin.bankapi.dao;

import backskin.bankapi.dao.mappers.AbstractMapper;
import backskin.bankapi.dao.mappers.LocalTransactionMapper;
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

    private final LocalTransactionMapper mapper;
    private final String tableName;
    private Connection connection;

    @Autowired
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public LocalTransactionDAO(
            @Qualifier("localTransactionsTableName") String tableName,
            LocalTransactionMapper mapper) {
        this.mapper = mapper;
        this.tableName = tableName;
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
