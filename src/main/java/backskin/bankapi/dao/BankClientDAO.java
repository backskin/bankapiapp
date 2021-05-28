package backskin.bankapi.dao;

import backskin.bankapi.dao.mappers.BankClientSqlMapper;
import backskin.bankapi.domain.BankClient;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 * The type Bank client dao.
 */
@Getter
@Repository
public class BankClientDAO extends AbstractDAO<BankClient> {

    private Connection connection;
    private final String tableName;
    private BankClientSqlMapper mapper;

    /**
     * Sets connection.
     *
     * @param connection the connection
     */
    @Autowired
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    /**
     * Sets mapper.
     *
     * @param mapper the mapper
     */
    @Autowired
    public void setMapper(BankClientSqlMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * Instantiates a new Bank client dao.
     *
     * @param bankClientsTableName the bank clients table name
     */
    public BankClientDAO(String bankClientsTableName) {
        this.tableName = bankClientsTableName;
    }

    @Override
    public void update(BankClient entity, Long aLong) throws SQLException {
        String sqlQuery = String.format("UPDATE %s SET %s,%s,%s WHERE %s",
                getTableName(),
                mapper.getFullNameValidator().validationRule(entity.getFullName()),
                mapper.getPassportIdValidator().validationRule(entity.getPassportId()),
                mapper.getPhoneNumberValidator().validationRule(entity.getPhoneNumber()),
                mapper.getIdValidator().validationRule(entity.getId())
        );
        PreparedStatement statement = getConnection().prepareStatement(sqlQuery);
        statement.execute();
        connection.commit();
    }
}
