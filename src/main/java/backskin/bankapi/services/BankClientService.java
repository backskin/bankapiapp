package backskin.bankapi.services;

import backskin.bankapi.dao.SqlDAO;
import backskin.bankapi.dao.SqlRepo;
import backskin.bankapi.domain.BankAccount;
import backskin.bankapi.domain.BankClient;
import backskin.bankapi.presentation.BankAccountInfo;
import backskin.bankapi.presentation.BankClientInfo;
import backskin.bankapi.presentation.Mapper;
import backskin.bankapi.validators.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BankClientService {
    private final SqlRepo<BankClient> bankClientSqlRepo;
    private final SqlDAO<BankClient> bankClientDAO;
    private final SqlRepo<BankAccount> bankAccountSqlRepo;
    private Mapper<BankClientInfo, BankClient> infoBankClientMapper;
    private Validator<BankAccount, Long> bankAccountValidatorByClientId;
    private Mapper<BankAccountInfo, BankAccount> infoBankAccountMapper;

    @Autowired
    public void setInfoBankClientMapper(Mapper<BankClientInfo, BankClient> infoBankClientMapper) {
        this.infoBankClientMapper = infoBankClientMapper;
    }

    @Autowired
    public void setBankAccountValidatorByClientId(Validator<BankAccount, Long> bankAccountValidatorByClientId) {
        this.bankAccountValidatorByClientId = bankAccountValidatorByClientId;
    }

    @Autowired
    public void setInfoBankAccountMapper(Mapper<BankAccountInfo, BankAccount> infoBankAccountMapper) {
        this.infoBankAccountMapper = infoBankAccountMapper;
    }

    public BankClientService(SqlRepo<BankClient> bankClientSqlRepo,
                             SqlDAO<BankClient> bankClientDAO,
                             SqlRepo<BankAccount> bankAccountSqlRepo) {
        this.bankClientSqlRepo = bankClientSqlRepo;
        this.bankClientDAO = bankClientDAO;
        this.bankAccountSqlRepo = bankAccountSqlRepo;
    }

    public List<BankClientInfo> getAllBankClients() throws SQLException {
        return bankClientSqlRepo.getAll().stream()
                .map(infoBankClientMapper::map).collect(Collectors.toList());
    }

    public BankClientInfo getClientById(Long clientId) throws Exception {
        return infoBankClientMapper.map(bankClientDAO.read(clientId));
    }

    public List<BankAccountInfo> getBankAccountsOnClient(Long clientId) throws Exception {
        return bankAccountSqlRepo.findAll(bankAccountValidatorByClientId, clientId)
                .stream().map(infoBankAccountMapper::map).collect(Collectors.toList());
    }
}


