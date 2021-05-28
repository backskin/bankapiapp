package backskin.bankapi.dao;

import backskin.bankapi.domain.DebitCard;
import org.springframework.stereotype.Repository;

/**
 * The type Debit card repo.
 */
@Repository
public class DebitCardRepo extends DebitCardDAO implements SqlRepo<DebitCard>{
    /**
     * Instantiates a new Debit card repo.
     *
     * @param debitCardsTableName the debit cards table name
     */
    public DebitCardRepo(String debitCardsTableName) {
        super(debitCardsTableName);
    }
}
