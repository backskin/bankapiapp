package backskin.bankapi.services;

import backskin.bankapi.dao.SqlDAO;
import backskin.bankapi.dao.SqlRepo;
import backskin.bankapi.domain.BankAccount;
import backskin.bankapi.domain.BankClient;
import backskin.bankapi.presentation.BankAccountInfo;
import backskin.bankapi.presentation.BankClientCredentials;
import backskin.bankapi.presentation.BankClientInfo;
import backskin.bankapi.presentation.Mapper;
import backskin.bankapi.validators.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Bank client service.
 */
@Service
public class BankClientService {
    private final SqlRepo<BankClient> bankClientSqlRepo;
    private final SqlDAO<BankClient> bankClientDAO;
    private final SqlRepo<BankAccount> bankAccountSqlRepo;
    private Mapper<BankClientInfo, BankClient> infoBankClientMapper;
    private Validator<BankAccount, Long> bankAccountValidatorByClientId;
    private Mapper<BankAccountInfo, BankAccount> infoBankAccountMapper;

    /**
     * Sets info bank client mapper.
     *
     * @param infoBankClientMapper the info bank client mapper
     */
    @Autowired
    public void setInfoBankClientMapper(Mapper<BankClientInfo, BankClient> infoBankClientMapper) {
        this.infoBankClientMapper = infoBankClientMapper;
    }

    /**
     * Sets bank account validator by client id.
     *
     * @param bankAccountValidatorByClientId the bank account validator by client id
     */
    @Autowired
    public void setBankAccountValidatorByClientId(Validator<BankAccount, Long> bankAccountValidatorByClientId) {
        this.bankAccountValidatorByClientId = bankAccountValidatorByClientId;
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
     * Instantiates a new Bank client service.
     *
     * @param bankClientSqlRepo  the bank client sql repo
     * @param bankClientDAO      the bank client dao
     * @param bankAccountSqlRepo the bank account sql repo
     */
    public BankClientService(SqlRepo<BankClient> bankClientSqlRepo,
                             SqlDAO<BankClient> bankClientDAO,
                             SqlRepo<BankAccount> bankAccountSqlRepo) {
        this.bankClientSqlRepo = bankClientSqlRepo;
        this.bankClientDAO = bankClientDAO;
        this.bankAccountSqlRepo = bankAccountSqlRepo;
    }

    /**
     * Gets all bank clients.
     *
     * @return the all bank clients
     * @throws SQLException the sql exception
     */
    public List<BankClientInfo> getAllBankClients() throws SQLException {
        return bankClientSqlRepo.getAll().stream()
                .map(infoBankClientMapper::map).collect(Collectors.toList());
    }

    /**
     * Gets client by id.
     *
     * @param clientId the client id
     * @return the client by id
     * @throws Exception the exception
     */
    public BankClientInfo getClientById(Long clientId) throws Exception {
        return infoBankClientMapper.map(bankClientDAO.read(clientId));
    }

    /**
     * Gets bank accounts on client.
     *
     * @param clientId the client id
     * @return the bank accounts on client
     * @throws Exception the exception
     */
    public List<BankAccountInfo> getBankAccountsOnClient(Long clientId) throws Exception {
        return bankAccountSqlRepo.findAll(bankAccountValidatorByClientId, clientId)
                .stream().map(infoBankAccountMapper::map).collect(Collectors.toList());
    }

    /**
     * Create new bank client bank client.
     *
     * @param credentials the credentials
     * @return the bank client
     * @throws Exception the exception
     */
    public BankClient createNewBankClient(BankClientCredentials credentials) throws Exception{
        return bankClientDAO.create(BankClient.builder().fullName(credentials.getFullName())
                .phoneNumber(credentials.getPhoneNumber()).passportId(credentials.getPassportId()).build());
    }
}


