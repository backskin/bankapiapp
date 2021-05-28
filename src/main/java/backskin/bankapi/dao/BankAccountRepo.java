package backskin.bankapi.dao;

import backskin.bankapi.domain.BankAccount;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 * The type Bank account repo.
 */
@Repository
public class BankAccountRepo extends BankAccountDAO implements SqlRepo<BankAccount> {
    /**
     * Instantiates a new Bank account repo.
     *
     * @param tableName the table name
     */
    public BankAccountRepo(@Qualifier("bankAccountsTableName") String tableName) {
        super(tableName);
    }
}
