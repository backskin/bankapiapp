package backskin.bankapi.dao;

import backskin.bankapi.dao.mappers.DebitCardSqlMapper;
import backskin.bankapi.domain.DebitCard;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * The type Debit card dao.
 */
@Repository
@Qualifier("debitCardDAO")
@Getter
public class DebitCardDAO extends AbstractDAO<DebitCard> {

    private final String tableName;
    private DebitCardSqlMapper mapper;
    private Connection connection;

    /**
     * Instantiates a new Debit card dao.
     *
     * @param debitCardsTableName the debit cards table name
     */
    public DebitCardDAO(String debitCardsTableName) {
        this.tableName = debitCardsTableName;
    }

    /**
     * Sets mapper.
     *
     * @param mapper the mapper
     */
    @Autowired
    public void setMapper(DebitCardSqlMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * Sets connection.
     *
     * @param connection the connection
     */
    @Autowired
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void update(DebitCard entity, Long aLong) throws SQLException {
        String sqlQuery = String.format("UPDATE %s SET %s,%s,%s,%s WHERE %s",
                getTableName(),
                mapper.getNumberValidator().validationRule(entity.getNumber()),
                mapper.getAccountIdValidator().validationRule(entity.getBankAccountId()),
                mapper.getCVVCodeValidator().validationRule(entity.getCvvCode()),
                mapper.getExpirationDateValidator().validationRule(entity.getExpirationDate()),
                mapper.getIdValidator().validationRule(entity.getId()));

        PreparedStatement statement = getConnection().prepareStatement(sqlQuery);
        statement.execute();
        connection.commit();
    }
}
