package backskin.bankapi.controllers;

import backskin.bankapi.domain.BankClient;
import backskin.bankapi.presentation.BankAccountInfo;
import backskin.bankapi.presentation.BankClientCredentials;
import backskin.bankapi.presentation.BankClientInfo;
import backskin.bankapi.services.BankClientService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/clients")
public class BankClientController {

    private final BankClientService bankClientService;

    public BankClientController(BankClientService bankClientService) {
        this.bankClientService = bankClientService;
    }

    @GetMapping
    public List<BankClientInfo> getAllClientsInfo() throws SQLException {
        return bankClientService.getAllBankClients();
    }
    @GetMapping({"{clientId}", "{clientId}/info"})
    public BankClientInfo getClientInfoById(@PathVariable Long clientId) throws Exception {
        return bankClientService.getClientById(clientId);
    }
    @GetMapping({"{clientId}/accounts","{clientId}/accounts/all"})
    public List<BankAccountInfo> getBankAccountsOnClient(@PathVariable Long clientId) throws Exception{
        return bankClientService.getBankAccountsOnClient(clientId);
    }
    @PostMapping("registration")
    public BankClient createNewBankClient(@RequestBody BankClientCredentials credentials) throws Exception{
        return bankClientService.createNewBankClient(credentials);
    }
}
