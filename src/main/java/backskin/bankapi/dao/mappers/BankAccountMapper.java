package backskin.bankapi.dao.mappers;

import backskin.bankapi.dao.Validator;
import backskin.bankapi.domain.BankAccount;
import backskin.bankapi.models.AbstractModel;
import backskin.bankapi.validators.ValidatorFactory;
import backskin.bankapi.validators.BankAccountValidatorFactory;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@Qualifier("bankAccount")
public class BankAccountMapper extends AbstractMapper<BankAccount> {
    @Qualifier("byNumber")
    @Getter
    private Validator<BankAccount, String> numberValidator;
    @Qualifier("byClientId")
    @Getter
    private Validator<BankAccount, Long> clientIdValidator;
    @Qualifier("byBalance")
    @Getter
    private Validator<BankAccount, BigDecimal> balanceValidator;
    @Override
    public BankAccount map(ResultSet resultSet) throws SQLException {
        return BankAccount.builder()
                .id(
                        resultSet.getLong(getIdValidator().tagName()))
                .number(
                        resultSet.getString(numberValidator.tagName()))
                .bankClientId(
                        resultSet.getLong(clientIdValidator.tagName()))
                .balance(
                        resultSet.getBigDecimal(balanceValidator.tagName()))
                .build();
    }
}
