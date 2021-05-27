package backskin.bankapi.dao;

import backskin.bankapi.domain.LocalTransaction;
import org.springframework.jdbc.core.RowMapper;

public class LocalTransactionDAO extends AbstractDAO<LocalTransaction> {
    @Override
    protected String getTableName() {
        return "account_local_transactions";
    }

    @Override
    protected RowMapper<LocalTransaction> getMapper() {
        return null;
    }

    @Override
    public void update(LocalTransaction entity, Long aLong) throws Exception {

    }
}
