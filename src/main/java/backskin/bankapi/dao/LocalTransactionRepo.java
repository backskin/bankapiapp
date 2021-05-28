package backskin.bankapi.dao;

import backskin.bankapi.domain.LocalTransaction;
import org.springframework.stereotype.Repository;

/**
 * The type Local transaction repo.
 */
@Repository
public class LocalTransactionRepo extends LocalTransactionDAO implements SqlRepo<LocalTransaction> {
    /**
     * Instantiates a new Local transaction repo.
     *
     * @param localTransactionsTableName the local transactions table name
     */
    public LocalTransactionRepo(String localTransactionsTableName) {
        super(localTransactionsTableName);
    }
}
