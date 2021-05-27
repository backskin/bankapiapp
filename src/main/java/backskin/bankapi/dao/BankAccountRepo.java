package backskin.bankapi.dao;

import backskin.bankapi.domain.BankAccount;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class BankAccountRepo extends BankAccountDAO implements SqlRepo<BankAccount> {
    public BankAccountRepo(@Qualifier("bankAccountsTableName") String tableName) {
        super(tableName);
    }
}
