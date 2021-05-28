package backskin.bankapi.dao;

import backskin.bankapi.domain.BankClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 * The type Bank client repo.
 */
@Repository
public class BankClientRepo extends BankClientDAO implements SqlRepo<BankClient> {
    /**
     * Instantiates a new Bank client repo.
     *
     * @param tableName the table name
     */
    public BankClientRepo(@Qualifier("bankClientsTableName") String tableName) {
        super(tableName);
    }
}
