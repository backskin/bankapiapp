package backskin.bankapi.dao.mappers;

import backskin.bankapi.dao.Validator;
import backskin.bankapi.domain.BankClient;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class BankClientMapper extends AbstractMapper<BankClient> {

    @Getter
    private final Validator<BankClient, String> fullNameValidator;
    @Getter
    private final Validator<BankClient, Long> passportIdValidator;
    @Getter
    private final Validator<BankClient, String> phoneNumberValidator;

    public BankClientMapper(@Qualifier("byFullName") Validator<BankClient, String> fullNameValidator,
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
