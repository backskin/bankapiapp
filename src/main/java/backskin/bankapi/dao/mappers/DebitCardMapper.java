package backskin.bankapi.dao.mappers;

import backskin.bankapi.dao.Validator;
import backskin.bankapi.domain.DebitCard;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class DebitCardMapper extends AbstractMapper<DebitCard> {

    @Getter
    private final Validator<DebitCard, Long> accountIdValidator;
    @Getter
    private final Validator<DebitCard, String> numberValidator;
    @Getter
    private final Validator<DebitCard, String> expirationDateValidator;
    @Getter
    private final Validator<DebitCard, String> CVVCodeValidator;

    public DebitCardMapper(
            Validator<DebitCard, Long> accountIdValidator,
            @Qualifier("byNumber") Validator<DebitCard, String> numberValidator,
            @Qualifier("byExpirationDate") Validator<DebitCard, String> expirationDateValidator,
            @Qualifier("byCVVCode") Validator<DebitCard, String> CVVCodeValidator) {
        this.accountIdValidator = accountIdValidator;
        this.numberValidator = numberValidator;
        this.expirationDateValidator = expirationDateValidator;
        this.CVVCodeValidator = CVVCodeValidator;
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
