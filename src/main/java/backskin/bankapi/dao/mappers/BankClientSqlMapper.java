package backskin.bankapi.dao.mappers;

import backskin.bankapi.domain.BankClient;
import backskin.bankapi.validators.Validator;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The type Bank client sql mapper.
 */
@Component
public class BankClientSqlMapper extends AbstractSqlMapper<BankClient> {

    @Getter
    private final Validator<BankClient, String> fullNameValidator;
    @Getter
    private final Validator<BankClient, Long> passportIdValidator;
    @Getter
    private final Validator<BankClient, String> phoneNumberValidator;

    /**
     * Instantiates a new Bank client sql mapper.
     *
     * @param fullNameValidator    the full name validator
     * @param passportIdValidator  the passport id validator
     * @param phoneNumberValidator the phone number validator
     */
    public BankClientSqlMapper(@Qualifier("byFullName") Validator<BankClient, String> fullNameValidator,
                               Validator<BankClient, Long> passportIdValidator,
                               @Qualifier("byPhoneNumber") Validator<BankClient, String> phoneNumberValidator) {
        this.fullNameValidator = fullNameValidator;
        this.passportIdValidator = passportIdValidator;
        this.phoneNumberValidator = phoneNumberValidator;
    }

    @Override
    public BankClient map(ResultSet resultSet) throws SQLException {
        return BankClient.builder()
                .id(
                        resultSet.getLong(getIdValidator().tagName()))
                .fullName(
                        resultSet.getString(fullNameValidator.tagName()))
                .phoneNumber(
                        resultSet.getString(phoneNumberValidator.tagName()))
                .passportId(
                        resultSet.getLong(passportIdValidator.tagName()))
                .build();
    }
}
