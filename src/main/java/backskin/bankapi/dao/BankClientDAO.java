package backskin.bankapi.dao;

import backskin.bankapi.dao.mappers.BankClientSqlMapper;
import backskin.bankapi.domain.BankClient;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


@Getter
@Repository
public class BankClientDAO extends AbstractDAO<BankClient> {

    private Connection connection;
    private final String tableName;
    private BankClientSqlMapper mapper;

    @Autowired
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    @Autowired
    public void setMapper(BankClientSqlMapper mapper) {
        this.mapper = mapper;
    }

    public BankClientDAO(String bankClientsTableName) {
        this.tableName = bankClientsTableName;
    }

    @Override
    public void update(BankClient entity, Long aLong) throws SQLException {
        String sqlQuery = "UPDATE ? SET ?,?,? WHERE ?";
        PreparedStatement statement = getConnection().prepareStatement(sqlQuery);

        statement.setString(1, getTableName());
        statement.setString(2,
                mapper.getFullNameValidator().validationRule(entity.getFullName()));
        statement.setString(3,
                mapper.getPassportIdValidator().validationRule(entity.getPassportId()));
        statement.setString(4,
                mapper.getPhoneNumberValidator().validationRule(entity.getPhoneNumber()));
        statement.setString(5,
                mapper.getIdValidator().validationRule(entity.getId()));
        statement.execute();
        connection.commit();
    }
}
