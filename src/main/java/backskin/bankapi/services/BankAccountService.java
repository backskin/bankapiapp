package backskin.bankapi.services;

import backskin.bankapi.dao.AbstractDAO;
import backskin.bankapi.dao.SqlRepo;
import backskin.bankapi.domain.BankAccount;
import backskin.bankapi.domain.DebitCard;
import backskin.bankapi.presentation.*;
import backskin.bankapi.validators.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BankAccountService {

    private final SqlRepo<DebitCard> debitCardSqlRepo;
    private Validator<DebitCard, Long> accountIdValidator;
    private final AbstractDAO<BankAccount> bankAccountSqlDAO;
    private final SqlRepo<BankAccount> bankAccountSqlRepo;
    private final DebitCardService debitCardService;

    private Mapper<DebitCardInfo, DebitCard> infoDebitCardMapper;
    private Mapper<BankAccountInfo, BankAccount> infoBankAccountMapper;

    public BankAccountService(SqlRepo<DebitCard> debitCardSqlRepo,
                              @Qualifier("bankAccountDAO") AbstractDAO<BankAccount> bankAccountSqlDAO,
                              SqlRepo<BankAccount> bankAccountSqlRepo,
                              DebitCardService debitCardService) {
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
    public void setInfoBankAccountMapper(Mapper<BankAccountInfo, BankAccount> infoBankAccountMapper) {
        this.infoBankAccountMapper = infoBankAccountMapper;
    }

    @Autowired
    public void setAccountIdValidator(Validator<DebitCard, Long> accountIdValidator) {
        this.accountIdValidator = accountIdValidator;
    }

    public BankAccount createNewBankAccount(BankAccountCredentials credentials) throws Exception {
        return bankAccountSqlDAO.create(BankAccount.builder()
                .bankClientId(credentials.getClientId())
                .balance(credentials.getBalance())
                .number(credentials.getNumber())
                .build());
    }

    public List<DebitCardInfo> getDebitCardsInfoOnAccount(Long accountId) throws Exception {
        List<DebitCard> foundDebitCards = debitCardSqlRepo.findAll(accountIdValidator,accountId);
        List<DebitCardInfo> output = new LinkedList<>();
        for (DebitCard card: foundDebitCards) {
            DebitCardInfo info = infoDebitCardMapper.map(card);
            output.add(info);
        }
        return output;
    }

    public DebitCard createNewDebitCardForAccount(Timestamp requestDate, Long accountId) throws Exception {
        System.out.println("1");
        return debitCardService.saveDebitCard(debitCardService.claimNewDebitCard(requestDate, accountId));
    }

    public List<BankAccountInfo> getAllAccounts() throws SQLException {
        return bankAccountSqlRepo.getAll().stream().map(infoBankAccountMapper::map).collect(Collectors.toList());
    }

    public BankAccountInfo getAccountById(Long accountId) throws SQLException{
        return infoBankAccountMapper.map(bankAccountSqlDAO.read(accountId));
    }

    public String getAccountBalanceAsString(Long accountId) throws SQLException{
        return bankAccountSqlDAO.read(accountId).getBalance().toString();
    }
}
