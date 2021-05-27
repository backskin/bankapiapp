package backskin.bankapi.controllers;

import backskin.bankapi.presentation.BankClientInfo;
import backskin.bankapi.services.BankClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/clients")
public class BankClientController {

    private BankClientService bankClientService;

    public BankClientController(BankClientService bankClientService) {
        this.bankClientService = bankClientService;
    }

    @GetMapping
    public List<BankClientInfo> getAllClientsInfo() throws SQLException {
        return bankClientService.getAllBankClients();
    }
    @GetMapping("?id={clientId}")
    public BankClientInfo getClientInfoById(@PathVariable Long clientId) throws Exception {
        return bankClientService.getClientById(clientId);
    }
}
