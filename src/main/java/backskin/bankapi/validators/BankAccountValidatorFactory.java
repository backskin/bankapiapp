package backskin.bankapi.validators;

import backskin.bankapi.domain.BankAccount;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

/**
 * The Bank account validator factory.
 */
@Configuration
public class BankAccountValidatorFactory {
    /**
     * By number validator.
     *
     * @return the validator
     */
    @Bean
    public Validator<BankAccount, String> byNumber(){
        return new Validator<>() {
            @Override
            public boolean validateObject(BankAccount object, String tag) {
                return object.getNumber().equals(tag);
            }

            @Override
            public String extractTag(BankAccount bankAccount) {
                return bankAccount.getNumber();
            }

            @Override
            public String tagName() {
                return "number";
            }
        };
    }

    /**
     * By client id validator.
     *
     * @return the validator
     */
    @Bean
    public Validator<BankAccount, Long> byClientId(){
        return new Validator<>() {
            @Override
            public boolean validateObject(BankAccount object, Long tag) {
                return object.getBankClientId().equals(tag);
            }

            @Override
            public Long extractTag(BankAccount bankAccount) {
                return bankAccount.getBankClientId();
            }

            @Override
            public String tagName() {
                return "bank_client_id";
            }
        };
    }

    /**
     * By balance more or equal validator.
     *
     * @return the validator
     */
    @Bean
    public Validator<BankAccount, BigDecimal> byBalanceMoreOrEqual(){
         return new Validator<>() {
             @Override
             public boolean validateObject(BankAccount object, BigDecimal tag) {
                 return object.getBalance().compareTo(tag) > 0;
             }

             @Override
             public BigDecimal extractTag(BankAccount bankAccount) {
                 return bankAccount.getBalance();
             }

             @Override
             public String tagName() {
                 return "balance";
             }

             @Override
             public String validationRule(BigDecimal tag) {
                 return tagName() + " >= "+tag.toString();
             }
         };
    }

    /**
     * By balance validator.
     *
     * @return the validator
     */
    @Bean
    public Validator<BankAccount, BigDecimal> byBalance(){
        return new Validator<>() {
            @Override
            public boolean validateObject(BankAccount object, BigDecimal tag) {
                return object.getBalance().equals(tag);
            }

            @Override
            public BigDecimal extractTag(BankAccount bankAccount) {
                return bankAccount.getBalance();
            }

            @Override
            public String tagName() {
                return "balance";
            }
        };
    }
}
