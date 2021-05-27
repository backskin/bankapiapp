package backskin.bankapi.validators;

import backskin.bankapi.dao.Validator;
import backskin.bankapi.domain.BankClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BankClientValidatorFactory extends ValidatorFactory {
    @Bean
    @Qualifier("fullName")
    Validator<BankClient, String> getFullNameValidator(){
        return new Validator<>() {
            @Override
            public boolean validateObject(BankClient object, String tag) {
                return object.getFullName().equals(tag);
            }

            @Override
            public String extractTag(BankClient bankClient) {
                return bankClient.getFullName();
            }

            @Override
            public String tagName() {
                return "full_name";
            }
        };
    }

    @Bean
    @Qualifier("phoneNumber")
    Validator<BankClient, String> getPhoneNumberValidator(){
        return new Validator<>() {
            @Override
            public boolean validateObject(BankClient object, String tag) {
                return object.getPhoneNumber().equals(tag);
            }

            @Override
            public String extractTag(BankClient bankClient) {
                return null;
            }

            @Override
            public String tagName() {
                return null;
            }
        };
    }

    @Bean
    @Qualifier("passportId")
    Validator<BankClient, Long> getPassportIdValidator(){
        return new Validator<>() {
            @Override
            public boolean validateObject(BankClient object, Long tag) {
                return object.getPassportId().equals(tag);
            }

            @Override
            public Long extractTag(BankClient bankClient) {
                return null;
            }

            @Override
            public String tagName() {
                return null;
            }
        };
    }
}
