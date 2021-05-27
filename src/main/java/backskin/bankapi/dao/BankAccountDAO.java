package backskin.bankapi.dao;

import backskin.bankapi.dao.mappers.AbstractMapper;
import backskin.bankapi.dao.mappers.BankAccountMapper;
import backskin.bankapi.domain.BankAccount;
import backskin.bankapi.validators.BankAccountValidatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
@Qualifier("bankAccountDAO")
public class BankAccountDAO extends AbstractDAO<BankAccount> {
    private final String tableName;
    @Override
    protected String getTableName() {
        return tableName;
    }

    @Autowired
    public BankAccountDAO(@Qualifier("bankAccountsTableName") String tableName) {
        this.tableName = tableName;
    }

    AbstractMapper<BankAccount> mapper;

    @Override
    public AbstractMapper<BankAccount> getMapper() {
        return new BankAccountMapper();
    }
    @Autowired
    public void setMapper(@Qualifier("bankAccount") AbstractMapper<BankAccount> mapper){
        this.mapper = mapper;
    }

    @Override
    public void update(BankAccount entity, Long id) throws SQLException {
        String sqlQuery = "UPDATE ? SET ?=?,?=?,?=? WHERE ?";
        PreparedStatement preparedStatement = getConnection().prepareStatement(sqlQuery);
        preparedStatement.setString(1, getTableName());
        preparedStatement.setString(2, );
        statement.executeQuery("UPDATE "+getTableName()+" SET "
                + String.join(", ",
                    BankAccountValidatorFactory.byNumber()
                            .validationRule(entity.getNumber()),
                    BankAccountValidatorFactory.byBalance()
                            .validationRule(entity.getBalance()),
                BankAccountValidatorFactory.byClientId()
                        .validationRule(entity.getBankClientId())
                )
                +" WHERE "+getIdColumnName()+" = "+id.toString()+";");
        getConnection().commit();
    }
}
