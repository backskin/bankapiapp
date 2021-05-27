package backskin.bankapi.dao;

import backskin.bankapi.domain.BankClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class BankClientDAO extends AbstractDAO<BankClient, Long> {

}
