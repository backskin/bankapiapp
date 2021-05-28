package backskin.bankapi.dao.mappers;

import backskin.bankapi.domain.LocalTransaction;
import backskin.bankapi.validators.Validator;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * The type Local transaction sql mapper.
 */
@Component
public class LocalTransactionSqlMapper extends AbstractSqlMapper<LocalTransaction> {
    @Getter
    private final Validator<LocalTransaction, Long> accountIdValidator;
    @Getter
    private final Validator<LocalTransaction, BigDecimal> differenceValidator;
    @Getter
    private final Validator<LocalTransaction, Timestamp> dateValidator;
    @Getter
    private final Validator<LocalTransaction, String> detailsValidator;

    /**
     * Instantiates a new Local transaction sql mapper.
     *
     * @param accountIdValidator  the account id validator
     * @param differenceValidator the difference validator
     * @param dateValidator       the date validator
     * @param detailsValidator    the details validator
     */
    public LocalTransactionSqlMapper(Validator<LocalTransaction, Long> accountIdValidator,
                                     Validator<LocalTransaction, BigDecimal> differenceValidator,
                                     Validator<LocalTransaction, Timestamp> dateValidator,
                                     Validator<LocalTransaction, String> detailsValidator) {
        this.accountIdValidator = accountIdValidator;
        this.differenceValidator = differenceValidator;
        this.dateValidator = dateValidator;
        this.detailsValidator = detailsValidator;
    }

    @Override
    public LocalTransaction map(ResultSet resultSet) throws SQLException {
        return LocalTransaction.builder().id(resultSet.getLong(getIdValidator().tagName()))
                .transactionDifference(
                        resultSet.getBigDecimal(differenceValidator.tagName()))
                .bankAccountId(
                        resultSet.getLong(accountIdValidator.tagName()))
                .date(
                        resultSet.getTimestamp(dateValidator.tagName()))
                .details(
                        resultSet.getString(detailsValidator.tagName()))
                .build();
    }
}
