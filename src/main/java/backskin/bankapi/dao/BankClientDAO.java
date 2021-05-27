package backskin.bankapi.dao;

import backskin.bankapi.dao.mappers.BankClientMapper;
import backskin.bankapi.domain.BankClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("bankClientDAO")
public class BankClientDAO extends AbstractDAO<BankClient> {

    private final String tableName;

    @Override
    protected String getTableName() {
        return tableName;
    }


    public BankClientDAO(@Qualifier("bankClientsTableName") String tableName) {
        this.tableName = tableName;
    }

    @Override
    protected RowMapper<BankClient> getMapper() {
        return new BankClientMapper();
    }

    @Override
    public void update(BankClient entity, Long aLong) throws Exception {

    }
}
