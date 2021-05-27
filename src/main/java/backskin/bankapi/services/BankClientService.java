package backskin.bankapi.services;

import backskin.bankapi.dao.SqlDAO;
import backskin.bankapi.dao.SqlRepo;
import backskin.bankapi.domain.BankClient;
import backskin.bankapi.presentation.BankClientInfo;
import backskin.bankapi.presentation.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BankClientService {
    private final SqlRepo<BankClient> bankClientSqlRepo;
    private final SqlDAO<BankClient> bankClientDAO;
    private Mapper<BankClientInfo, BankClient> infoBankClientMapper;

    public BankClientService(SqlRepo<BankClient> bankClientSqlRepo,
                             SqlDAO<BankClient> bankClientDAO) {
        this.bankClientSqlRepo = bankClientSqlRepo;
        this.bankClientDAO = bankClientDAO;
    }

    public List<BankClientInfo> getAllBankClients() throws SQLException {
        return bankClientSqlRepo.getAll().stream()
                .map(infoBankClientMapper::map).collect(Collectors.toList());
    }

    public BankClientInfo getClientById(Long clientId) throws Exception {
        return infoBankClientMapper.map(bankClientDAO.read(clientId));
    }

    @Autowired
    public void setInfoBankClientMapper(Mapper<BankClientInfo, BankClient> infoBankClientMapper) {
        this.infoBankClientMapper = infoBankClientMapper;
    }
}
