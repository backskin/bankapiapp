package backskin.bankapi.controllers;

import backskin.bankapi.domain.BankAccount;
import backskin.bankapi.domain.DebitCard;
import backskin.bankapi.presentation.BankAccountCredentials;
import backskin.bankapi.presentation.BankAccountInfo;
import backskin.bankapi.presentation.DebitCardInfo;
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
    public List<BankAccountInfo> getAllAccounts() throws SQLException {
        return bankAccountService.getAllAccounts();
    }

    @GetMapping("{accountId}/info")
    public BankAccountInfo getAccountInfoById(@PathVariable Long accountId) throws  SQLException{
        return bankAccountService.getAccountById(accountId);
    }

    @PostMapping({"","create"})
    public BankAccount createNewBankAccount(@RequestBody BankAccountCredentials credentials) throws Exception {
        return bankAccountService.createNewBankAccount(credentials);
    }

    @PostMapping("{accountId}/debitcards/release")
    public DebitCard createNewDebitCardForAccount(@PathVariable Long accountId) throws Exception {
        return bankAccountService.createNewDebitCardForAccount(Timestamp.from(Instant.now()),accountId);
    }

    @GetMapping("{accountId}/debitcards")
    public List<DebitCardInfo> getAllDebitCardsInfoOnAccount(@PathVariable Long accountId) throws Exception {
        return bankAccountService.getDebitCardsInfoOnAccount(accountId);
    }
    @GetMapping("{accountId}/balance")
    public String getAccountBalance(@PathVariable Long accountId) throws Exception {
        return bankAccountService.getAccountBalanceAsString(accountId);
    }
}
