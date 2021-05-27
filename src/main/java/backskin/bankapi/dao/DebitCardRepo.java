package backskin.bankapi.dao;

import backskin.bankapi.dao.mappers.DebitCardSqlMapper;
import backskin.bankapi.domain.DebitCard;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class DebitCardRepo extends DebitCardDAO implements SqlRepo<DebitCard>{
    public DebitCardRepo(@Qualifier("debitCardsTableName") String tableName, DebitCardSqlMapper mapper) {
        super(tableName, mapper);
    }
}
