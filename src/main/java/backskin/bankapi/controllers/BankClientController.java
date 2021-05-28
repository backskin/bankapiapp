package backskin.bankapi.controllers;

import backskin.bankapi.domain.BankClient;
import backskin.bankapi.presentation.BankAccountInfo;
import backskin.bankapi.presentation.BankClientCredentials;
import backskin.bankapi.presentation.BankClientInfo;
import backskin.bankapi.services.BankClientService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

/**
 * The type Bank client controller.
 */
@RestController
@RequestMapping("/api/v1/clients")
public class BankClientController {

    private final BankClientService bankClientService;

    /**
     * Instantiates a new Bank client controller.
     *
     * @param bankClientService the bank client service
     */
    public BankClientController(BankClientService bankClientService) {
        this.bankClientService = bankClientService;
    }

    /**
     * Gets all clients info.
     *
     * @return the all clients info
     * @throws SQLException the sql exception
     */
    @GetMapping
    public List<BankClientInfo> getAllClientsInfo() throws SQLException {
        return bankClientService.getAllBankClients();
    }

    /**
     * Gets client info by id.
     *
     * @param clientId the client id
     * @return the client info by id
     * @throws Exception the exception
     */
    @GetMapping({"{clientId}", "{clientId}/info"})
    public BankClientInfo getClientInfoById(@PathVariable Long clientId) throws Exception {
        return bankClientService.getClientById(clientId);
    }

    /**
     * Gets bank accounts on client.
     *
     * @param clientId the client id
     * @return the bank accounts on client
     * @throws Exception the exception
     */
    @GetMapping({"{clientId}/accounts","{clientId}/accounts/all"})
    public List<BankAccountInfo> getBankAccountsOnClient(@PathVariable Long clientId) throws Exception{
        return bankClientService.getBankAccountsOnClient(clientId);
    }

    /**
     * Create new bank client bank client.
     *
     * @param credentials the credentials
     * @return the bank client
     * @throws Exception the exception
     */
    @PostMapping("registration")
    public BankClient createNewBankClient(@RequestBody BankClientCredentials credentials) throws Exception{
        return bankClientService.createNewBankClient(credentials);
    }
}
