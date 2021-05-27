package backskin.bankapi.dao.mappers;

import backskin.bankapi.validators.Validator;
import backskin.bankapi.domain.BankAccount;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class BankAccountSqlMapper extends AbstractSqlMapper<BankAccount> {

    @Getter
    private final Validator<BankAccount, String> numberValidator;
    @Getter
    private final Validator<BankAccount, Long> clientIdValidator;
    @Getter
    private final Validator<BankAccount, BigDecimal> balanceValidator;

    public BankAccountSqlMapper(
            Validator<BankAccount, String> numberValidator,
            Validator<BankAccount, Long> clientIdValidator,
            @Qualifier("byBalance") Validator<BankAccount, BigDecimal> balanceValidator) {
        this.numberValidator = numberValidator;
        this.clientIdValidator = clientIdValidator;
        this.balanceValidator = balanceValidator;
    }

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
