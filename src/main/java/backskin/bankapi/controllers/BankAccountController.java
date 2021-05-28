package backskin.bankapi.controllers;

import backskin.bankapi.domain.BankAccount;
import backskin.bankapi.domain.DebitCard;
import backskin.bankapi.presentation.*;
import backskin.bankapi.services.BankAccountService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

/**
 * The type Bank account controller.
 */
@RestController
@RequestMapping(value = "/api/v1/accounts", produces = MediaType.APPLICATION_JSON_VALUE)
public class BankAccountController {

    private final BankAccountService bankAccountService;

    /**
     * Instantiates a new Bank account controller.
     *
     * @param bankAccountService the bank account service
     */
    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    /**
     * Gets full accounts data.
     *
     * @return the full accounts data
     * @throws SQLException the sql exception
     */
    @GetMapping
    public List<BankAccount> getFullAccountsData() throws SQLException{
        return bankAccountService.getFullAccountsData();
    }

    /**
     * Gets all accounts info.
     *
     * @return the all accounts info
     * @throws SQLException the sql exception
     */
    @GetMapping("info")
    public List<BankAccountInfo> getAllAccountsInfo() throws SQLException {
        return bankAccountService.getAllAccountsInfo();
    }

    /**
     * Gets account info by id.
     *
     * @param accountId the account id
     * @return the account info by id
     * @throws SQLException the sql exception
     */
    @GetMapping("{accountId}/info")
    public BankAccountInfo getAccountInfoById(@PathVariable Long accountId) throws SQLException{
        return bankAccountService.getAccountInfoById(accountId);
    }

    /**
     * Gets account.
     *
     * @param accountId the account id
     * @return the account
     * @throws SQLException the sql exception
     */
    @GetMapping("{accountId}")
    public BankAccount getAccount(@PathVariable Long accountId) throws SQLException {
        return bankAccountService.getBankAccount(accountId);
    }

    /**
     * Create new bank account bank account.
     *
     * @param credentials the credentials
     * @return the bank account
     * @throws Exception the exception
     */
    @PostMapping({"","create"})
    public BankAccount createNewBankAccount(@RequestBody BankAccountCredentials credentials) throws Exception {
        return bankAccountService.createNewBankAccount(credentials);
    }

    /**
     * Create new debit card for account debit card.
     *
     * @param accountId the account id
     * @return the debit card
     * @throws Exception the exception
     */
    @PostMapping("{accountId}/debit-cards/release")
    public DebitCard createNewDebitCardForAccount(@PathVariable Long accountId) throws Exception {
        return bankAccountService.createNewDebitCardForAccount(Timestamp.from(Instant.now()),accountId);
    }

    /**
     * Gets all debit cards info on account.
     *
     * @param accountId the account id
     * @return the all debit cards info on account
     * @throws Exception the exception
     */
    @GetMapping("{accountId}/debit-cards")
    public List<DebitCardInfo> getAllDebitCardsInfoOnAccount(@PathVariable Long accountId) throws Exception {
        return bankAccountService.getDebitCardsInfoOnAccount(accountId);
    }

    /**
     * Gets account balance.
     *
     * @param accountId the account id
     * @return the account balance
     * @throws Exception the exception
     */
    @GetMapping("{accountId}/balance")
    public String getAccountBalance(@PathVariable Long accountId) throws Exception {
        return bankAccountService.getAccountBalanceAsString(accountId);
    }

    /**
     * Make deposit deposit info.
     *
     * @param depositCredentials the deposit credentials
     * @param accountId          the account id
     * @return the deposit info
     * @throws Exception the exception
     */
    @PostMapping("{accountId}/balance/make-deposit")
    public DepositInfo makeDeposit(@RequestBody DepositCredentials depositCredentials, @PathVariable Long accountId) throws Exception {
        return bankAccountService.makeDeposit(depositCredentials, accountId);
    }
}
