package backskin.bankapi.dao;

import backskin.bankapi.domain.DebitCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("debitCardDAO")
public class DebitCardDAO extends AbstractDAO<DebitCard> {

    private final String tableName;

    @Autowired
    public DebitCardDAO(@Qualifier("debitCardsTableName") String tableName) {
        this.tableName = tableName;
    }

    @Override
    protected String getTableName() {
        return tableName;
    }

    @Override
    protected RowMapper<DebitCard> getMapper() {
        return null;
    }

    @Override
    public void update(DebitCard entity, Long aLong) throws Exception {

    }
}
