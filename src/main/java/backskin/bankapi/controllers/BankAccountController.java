package backskin.bankapi.controllers;

import backskin.bankapi.domain.BankAccount;
import backskin.bankapi.presentation.BankAccountCredentials;
import backskin.bankapi.presentation.BankAccountInfo;
import backskin.bankapi.services.BankAccountService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/accounts", produces = MediaType.APPLICATION_JSON_VALUE)
public class BankAccountController {

    private final BankAccountService bankAccountService;

    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @GetMapping({"all", ""})
    @ResponseBody
    public List<BankAccountInfo> getAllAccounts() throws SQLException {
        return bankAccountService.getAllAccounts();
    }
    @GetMapping("account?id={accountId}")
    @ResponseBody
    public BankAccountInfo getAccountInfoById(@PathVariable Long accountId) throws  SQLException{
        return bankAccountService.getAccountById(accountId);
    }

    @PostMapping("creaction?number={number}&client_id={clientId}&init_balance={initBalance}")
    @ResponseBody
    public BankAccount createNewBankAccount(@PathVariable String number,
                                            @PathVariable Long clientId,
                                            @PathVariable BigDecimal initBalance) throws Exception {
        return bankAccountService.createNewBankAccount(
                BankAccountCredentials.builder()
                        .number(number)
                        .clientId(clientId).balance(initBalance)
                        .build());
    }
}
