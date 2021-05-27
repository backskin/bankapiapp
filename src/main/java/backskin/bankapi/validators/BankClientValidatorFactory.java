package backskin.bankapi.validators;

import backskin.bankapi.domain.BankClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BankClientValidatorFactory extends ValidatorFactory {
    @Bean
    Validator<BankClient, String> byFullName(){
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
    Validator<BankClient, String> byPhoneNumber(){
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
    Validator<BankClient, Long> byPassportId(){
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
