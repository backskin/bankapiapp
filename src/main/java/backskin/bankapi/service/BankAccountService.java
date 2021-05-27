package backskin.bankapi.service;

import backskin.bankapi.dao.SqlRepo;
import backskin.bankapi.domain.DebitCard;
import backskin.bankapi.models.DebitCardInfo;
import backskin.bankapi.validators.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BankAccountService {

    private final SqlRepo<DebitCard> debitCardSqlRepo;
    private Validator<DebitCard, Long> accountIdValidator;

    public BankAccountService(SqlRepo<DebitCard> debitCardSqlRepo) {
        this.debitCardSqlRepo = debitCardSqlRepo;
    }

    @Autowired
    public void setAccountIdValidator(Validator<DebitCard, Long> accountIdValidator) {
        this.accountIdValidator = accountIdValidator;
    }

    List<DebitCardInfo> getCardsInfoOnAccount(Long accountId) throws SQLException {
        return debitCardSqlRepo.findAll(accountIdValidator,accountId)
                .stream().map(DebitCardInfo::fromDebitCard).collect(Collectors.toList());
    }

    boolean createNewDebitCard(Timestamp requestDate, Long AccountId){

    }
}
