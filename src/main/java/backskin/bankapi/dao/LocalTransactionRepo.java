package backskin.bankapi.dao;

import backskin.bankapi.domain.LocalTransaction;
import org.springframework.stereotype.Repository;

@Repository
public class LocalTransactionRepo extends LocalTransactionDAO implements SqlRepo<LocalTransaction> {
    public LocalTransactionRepo(String localTransactionsTableName) {
        super(localTransactionsTableName);
    }
}
