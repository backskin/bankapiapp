package backskin.bankapi.dao.mappers;

import backskin.bankapi.validators.Validator;
import backskin.bankapi.domain.DebitCard;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class DebitCardSqlMapper extends AbstractSqlMapper<DebitCard> {
    @Getter
    private final Validator<DebitCard, Long> accountIdValidator;
    @Getter
    private final Validator<DebitCard, String> numberValidator;
    @Getter
    private final Validator<DebitCard, String> expirationDateValidator;
    @Getter
    private final Validator<DebitCard, String> CVVCodeValidator;

    public DebitCardSqlMapper(
            Validator<DebitCard, Long> accountIdValidator,
            @Qualifier("cardValidatorByNumber") Validator<DebitCard, String> numberValidator,
            @Qualifier("cardValidatorByExpirationDate") Validator<DebitCard, String> expirationDateValidator,
            @Qualifier("cardValidatorByCVVCode") Validator<DebitCard, String> cvvCodeValidator) {
        this.accountIdValidator = accountIdValidator;
        this.numberValidator = numberValidator;
        this.expirationDateValidator = expirationDateValidator;
        this.CVVCodeValidator = cvvCodeValidator;
    }

    @Override
    public DebitCard map(ResultSet resultSet) throws SQLException {
        return DebitCard.builder()
                .id(
                        resultSet.getLong(getIdValidator().tagName()))
                .bankAccountId(
                        resultSet.getLong(accountIdValidator.tagName()))
                .number(
                        resultSet.getString(numberValidator.tagName()))
                .expirationDate(
                        resultSet.getString(expirationDateValidator.tagName()))
                .cvvCode(
                        resultSet.getString(CVVCodeValidator.tagName()))
                .build();
    }
}
