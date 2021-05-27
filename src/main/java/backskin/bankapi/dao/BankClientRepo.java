package backskin.bankapi.dao;

import backskin.bankapi.domain.BankClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class BankClientRepo extends BankClientDAO implements SqlRepo<BankClient> {
    public BankClientRepo(@Qualifier("bankClientsTableName") String tableName) {
        super(tableName);
    }
}
