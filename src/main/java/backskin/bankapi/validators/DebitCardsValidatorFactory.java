package backskin.bankapi.validators;

import backskin.bankapi.domain.DebitCard;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DebitCardsValidatorFactory {
    @Bean
    Validator<DebitCard, Long> cardValidatorByAccountId(){
        return new Validator<>() {
            @Override
            public boolean validateObject(DebitCard object, Long tag) {
                return object.getBankAccountId().equals(tag);
            }

            @Override
            public Long extractTag(DebitCard debitCard) {
                return debitCard.getBankAccountId();
            }

            @Override
            public String tagName() {
                return "bank_account_id";
            }
        };
    }

    @Bean
    @Qualifier("cardValidatorByNumber")
    Validator<DebitCard, String> cardValidatorByNumber(){
        return new Validator<>() {
            @Override
            public boolean validateObject(DebitCard object, String tag) {
                return object.getNumber().equals(tag);
            }

            @Override
            public String extractTag(DebitCard debitCard) {
                return debitCard.getNumber();
            }

            @Override
            public String tagName() {
                return "number";
            }
        };
    }

    @Bean
    @Qualifier("cardValidatorByExpirationDate")
    Validator<DebitCard, String> cardValidatorByExpirationDate(){
        return new Validator<>() {
            @Override
            public boolean validateObject(DebitCard object, String tag) {
                return object.getExpirationDate().equals(tag);
            }

            @Override
            public String extractTag(DebitCard debitCard) {
                return debitCard.getExpirationDate();
            }

            @Override
            public String tagName() {
                return "expiration_date";
            }
        };
    }

    @Bean
    @Qualifier("cardValidatorByCVVCode")
    Validator<DebitCard, String> cardValidatorByCVVCode(){
        return new Validator<>() {
            @Override
            public boolean validateObject(DebitCard object, String tag) {
                return object.getCvvCode().equals(tag);
            }

            @Override
            public String extractTag(DebitCard debitCard) {
                return debitCard.getCvvCode();
            }

            @Override
            public String tagName() {
                return "cvv_code";
            }
        };
    }
}
