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

@RestController
@RequestMapping(value = "/api/v1/accounts", produces = MediaType.APPLICATION_JSON_VALUE)
public class BankAccountController {

    private final BankAccountService bankAccountService;

    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @GetMapping
    public List<BankAccount> getFullAccountsData() throws SQLException{
        return bankAccountService.getFullAccountsData();
    }

    @GetMapping("info")
    public List<BankAccountInfo> getAllAccountsInfo() throws SQLException {
        return bankAccountService.getAllAccountsInfo();
    }

    @GetMapping("{accountId}/info")
    public BankAccountInfo getAccountInfoById(@PathVariable Long accountId) throws SQLException{
        return bankAccountService.getAccountInfoById(accountId);
    }

    @GetMapping("{accountId}")
    public BankAccount getAccount(@PathVariable Long accountId) throws SQLException {
        return bankAccountService.getBankAccount(accountId);
    }

    @PostMapping({"","create"})
    public BankAccount createNewBankAccount(@RequestBody BankAccountCredentials credentials) throws Exception {
        return bankAccountService.createNewBankAccount(credentials);
    }

    @PostMapping("{accountId}/debit-cards/release")
    public DebitCard createNewDebitCardForAccount(@PathVariable Long accountId) throws Exception {
        return bankAccountService.createNewDebitCardForAccount(Timestamp.from(Instant.now()),accountId);
    }

    @GetMapping("{accountId}/debit-cards")
    public List<DebitCardInfo> getAllDebitCardsInfoOnAccount(@PathVariable Long accountId) throws Exception {
        return bankAccountService.getDebitCardsInfoOnAccount(accountId);
    }
    @GetMapping("{accountId}/balance")
    public String getAccountBalance(@PathVariable Long accountId) throws Exception {
        return bankAccountService.getAccountBalanceAsString(accountId);
    }

    @PostMapping("{accountId}/balance/make-deposit")
    public DepositInfo makeDeposit(@RequestBody DepositCredentials depositCredentials, @PathVariable Long accountId) throws Exception {
        return bankAccountService.makeDeposit(depositCredentials, accountId);
    }
}
