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
import java.time.Instant;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Bank account service.
 */
@Service
public class BankAccountService {

    private final SqlRepo<DebitCard> debitCardSqlRepo;
    private Validator<DebitCard, Long> accountIdValidator;
    private final AbstractDAO<BankAccount> bankAccountSqlDAO;
    private final SqlRepo<BankAccount> bankAccountSqlRepo;
    private final DebitCardService debitCardService;

    private Mapper<DebitCardInfo, DebitCard> infoDebitCardMapper;
    private Mapper<BankAccountInfo, BankAccount> infoBankAccountMapper;

    /**
     * Instantiates a new Bank account service.
     *
     * @param debitCardSqlRepo   the debit card sql repo
     * @param bankAccountSqlDAO  the bank account sql dao
     * @param bankAccountSqlRepo the bank account sql repo
     * @param debitCardService   the debit card service
     */
    public BankAccountService(SqlRepo<DebitCard> debitCardSqlRepo,
                              @Qualifier("bankAccountDAO") AbstractDAO<BankAccount> bankAccountSqlDAO,
                              SqlRepo<BankAccount> bankAccountSqlRepo,
                              DebitCardService debitCardService) {
        this.debitCardSqlRepo = debitCardSqlRepo;
        this.bankAccountSqlDAO = bankAccountSqlDAO;
        this.bankAccountSqlRepo = bankAccountSqlRepo;
        this.debitCardService = debitCardService;
    }

    /**
     * Sets info debit card mapper.
     *
     * @param infoDebitCardMapper the info debit card mapper
     */
    @Autowired
    public void setInfoDebitCardMapper(Mapper<DebitCardInfo, DebitCard> infoDebitCardMapper) {
        this.infoDebitCardMapper = infoDebitCardMapper;
    }

    /**
     * Sets info bank account mapper.
     *
     * @param infoBankAccountMapper the info bank account mapper
     */
    @Autowired
    public void setInfoBankAccountMapper(Mapper<BankAccountInfo, BankAccount> infoBankAccountMapper) {
        this.infoBankAccountMapper = infoBankAccountMapper;
    }

    /**
     * Sets account id validator.
     *
     * @param accountIdValidator the account id validator
     */
    @Autowired
    public void setAccountIdValidator(Validator<DebitCard, Long> accountIdValidator) {
        this.accountIdValidator = accountIdValidator;
    }

    /**
     * Create new bank account bank account.
     *
     * @param credentials the credentials
     * @return the bank account
     * @throws Exception the exception
     */
    public BankAccount createNewBankAccount(BankAccountCredentials credentials) throws Exception {
        return bankAccountSqlDAO.create(BankAccount.builder()
                .bankClientId(credentials.getClientId())
                .balance(credentials.getBalance())
                .number(credentials.getNumber())
                .build());
    }

    /**
     * Gets debit cards info on account.
     *
     * @param accountId the account id
     * @return the debit cards info on account
     * @throws Exception the exception
     */
    public List<DebitCardInfo> getDebitCardsInfoOnAccount(Long accountId) throws Exception {
        List<DebitCard> foundDebitCards = debitCardSqlRepo.findAll(accountIdValidator,accountId);
        List<DebitCardInfo> output = new LinkedList<>();
        for (DebitCard card: foundDebitCards) {
            DebitCardInfo info = infoDebitCardMapper.map(card);
            output.add(info);
        }
        return output;
    }

    /**
     * Create new debit card for account debit card.
     *
     * @param requestDate the request date
     * @param accountId   the account id
     * @return the debit card
     * @throws Exception the exception
     */
    public DebitCard createNewDebitCardForAccount(Timestamp requestDate, Long accountId) throws Exception {
        System.out.println("1");
        return debitCardService.saveDebitCard(debitCardService.claimNewDebitCard(requestDate, accountId));
    }

    /**
     * Gets all accounts info.
     *
     * @return the all accounts info
     * @throws SQLException the sql exception
     */
    public List<BankAccountInfo> getAllAccountsInfo() throws SQLException {
        return bankAccountSqlRepo.getAll().stream().map(infoBankAccountMapper::map).collect(Collectors.toList());
    }

    /**
     * Gets full accounts data.
     *
     * @return the full accounts data
     * @throws SQLException the sql exception
     */
    public List<BankAccount> getFullAccountsData() throws SQLException {
        return bankAccountSqlRepo.getAll();
    }

    /**
     * Gets bank account.
     *
     * @param accountId the account id
     * @return the bank account
     * @throws SQLException the sql exception
     */
    public BankAccount getBankAccount(Long accountId) throws SQLException {
        return bankAccountSqlDAO.read(accountId);
    }

    /**
     * Gets account info by id.
     *
     * @param accountId the account id
     * @return the account info by id
     * @throws SQLException the sql exception
     */
    public BankAccountInfo getAccountInfoById(Long accountId) throws SQLException{
        return infoBankAccountMapper.map(bankAccountSqlDAO.read(accountId));
    }

    /**
     * Gets account balance as string.
     *
     * @param accountId the account id
     * @return the account balance as string
     * @throws SQLException the sql exception
     */
    public String getAccountBalanceAsString(Long accountId) throws SQLException{
        return bankAccountSqlDAO.read(accountId).getBalance().toString();
    }

    /**
     * Make deposit deposit info.
     *
     * @param depositCredentials the deposit credentials
     * @param accountId          the account id
     * @return the deposit info
     * @throws Exception the exception
     */
    public DepositInfo makeDeposit(DepositCredentials depositCredentials, Long accountId) throws Exception{
        if (!depositCredentials.getRecipientAccountId().equals(accountId)){
            throw new Exception("RECIPIENT_ACCOUNT_ID_DOES_NOT_MATCH_DEPOSIT_CREDENTIALS");
        }
        DepositInfo.DepositInfoBuilder builder =
                DepositInfo.builder().depositAmount(depositCredentials.getDepositAmount())
                        .dateOfOperation(Timestamp.from(Instant.now()));
        BankAccount account = bankAccountSqlDAO.read(accountId);
        account.setBalance(account.getBalance().add(depositCredentials.getDepositAmount()));

        return builder.currentBalanceAfterDeposit(account.getBalance())
                .accountNumber(account.getNumber())
                .build();
    }
}
