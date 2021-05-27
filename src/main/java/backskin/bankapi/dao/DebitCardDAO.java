package backskin.bankapi.dao;

import backskin.bankapi.dao.mappers.DebitCardSqlMapper;
import backskin.bankapi.domain.DebitCard;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;

@Repository
@Qualifier("debitCardDAO")
@Getter
public class DebitCardDAO extends AbstractDAO<DebitCard> {

    private final String tableName;
    private final DebitCardSqlMapper mapper;
    private Connection connection;

    public DebitCardDAO(
            @Qualifier("debitCardsTableName") String tableName,
            DebitCardSqlMapper mapper) {
        this.tableName = tableName;
        this.mapper = mapper;
    }

    @Autowired
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void update(DebitCard entity, Long aLong) throws Exception {
        String sqlQuery = "UPDATE ? SET ?,?,?,? WHERE ?";
        PreparedStatement statement = getConnection().prepareStatement(sqlQuery);
        statement.setString(1, getTableName());
        statement.setString(2,
                mapper.getNumberValidator().validationRule(entity.getNumber()));
        statement.setString(3,
                mapper.getAccountIdValidator().validationRule(entity.getBankAccountId()));
        statement.setString(4,
                mapper.getCVVCodeValidator().validationRule(entity.getCvvCode()));
        statement.setString(5,
                mapper.getExpirationDateValidator().validationRule(entity.getExpirationDate()));
        statement.setString(6,
                mapper.getIdValidator().validationRule(entity.getId()));
        statement.execute();
        connection.commit();
    }
}
