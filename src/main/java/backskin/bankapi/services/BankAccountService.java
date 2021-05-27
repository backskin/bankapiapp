package backskin.bankapi.services;

import backskin.bankapi.dao.AbstractDAO;
import backskin.bankapi.dao.SqlRepo;
import backskin.bankapi.domain.BankAccount;
import backskin.bankapi.domain.DebitCard;
import backskin.bankapi.presentation.BankAccountInfo;
import backskin.bankapi.presentation.DebitCardCredentials;
import backskin.bankapi.presentation.DebitCardInfo;
import backskin.bankapi.presentation.Mapper;
import backskin.bankapi.validators.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

@Service
public class BankAccountService {

    private final SqlRepo<DebitCard> debitCardSqlRepo;
    private Validator<DebitCard, Long> accountIdValidator;
    private final AbstractDAO<BankAccount> bankAccountSqlDAO;
    private final SqlRepo<BankAccount> bankAccountSqlRepo;
    private final DebitCardService debitCardService;

    private Mapper<DebitCardInfo, DebitCard> infoDebitCardMapper;

    public BankAccountService(SqlRepo<DebitCard> debitCardSqlRepo, AbstractDAO<BankAccount> bankAccountSqlDAO, SqlRepo<BankAccount> bankAccountSqlRepo, DebitCardService debitCardService) {
        this.debitCardSqlRepo = debitCardSqlRepo;
        this.bankAccountSqlDAO = bankAccountSqlDAO;
        this.bankAccountSqlRepo = bankAccountSqlRepo;
        this.debitCardService = debitCardService;
    }

    @Autowired
    public void setInfoDebitCardMapper(Mapper<DebitCardInfo, DebitCard> infoDebitCardMapper) {
        this.infoDebitCardMapper = infoDebitCardMapper;
    }

    @Autowired
    public void setAccountIdValidator(Validator<DebitCard, Long> accountIdValidator) {
        this.accountIdValidator = accountIdValidator;
    }

    List<DebitCardInfo> getCardsInfoOnAccount(Long accountId) throws Exception {
        List<DebitCard> foundDebitCards = debitCardSqlRepo.findAll(accountIdValidator,accountId);
        List<DebitCardInfo> output = new LinkedList<>();
        for (DebitCard card: foundDebitCards) {
            DebitCardInfo info = infoDebitCardMapper.map(card).orElseThrow(
                    ()->new Exception("Mapping Exception")
            );
            output.add(info);
        }
        return output;
    }

    DebitCardInfo createNewDebitCardForAccount(Timestamp requestDate, Long accountId) throws Exception {
        DebitCardCredentials cardCredentials = debitCardService.claimNewDebitCard(requestDate, accountId);
        return debitCardService.saveDebitCard(cardCredentials);
    }

    List<BankAccountInfo> getAllAccounts() throws SQLException {
        return bankAccountSqlRepo.getAll().stream().map();
    }


}
