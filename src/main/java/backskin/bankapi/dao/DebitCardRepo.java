package backskin.bankapi.dao;

import backskin.bankapi.domain.DebitCard;
import org.springframework.stereotype.Repository;

@Repository
public class DebitCardRepo extends DebitCardDAO implements SqlRepo<DebitCard>{
    public DebitCardRepo(String debitCardsTableName) {
        super(debitCardsTableName);
    }
}
