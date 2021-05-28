package backskin.bankapi.validators;

import backskin.bankapi.domain.LocalTransaction;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * The type Local transactions validator factory.
 */
@Configuration
public class LocalTransactionsValidatorFactory {
    /**
     * By account id validator.
     *
     * @return the validator
     */
    @Bean
    @Qualifier("transactionValidatorByAccountId")
    public Validator<LocalTransaction, Long> byAccountId(){
        return new Validator<>() {
            @Override
            public boolean validateObject(LocalTransaction object, Long tag) {
                return object.getBankAccountId().equals(tag);
            }

            @Override
            public Long extractTag(LocalTransaction localTransaction) {
                return localTransaction.getBankAccountId();
            }

            @Override
            public String tagName() {
                return "bank_account_id";
            }
        };
    }

    /**
     * By difference validator.
     *
     * @return the validator
     */
    @Bean
    @Qualifier("validatorByDifference")
    public Validator<LocalTransaction, BigDecimal> byDifference(){
        return new Validator<>() {
            @Override
            public boolean validateObject(LocalTransaction object, BigDecimal tag) {
                return object.getTransactionDifference().equals(tag);
            }

            @Override
            public BigDecimal extractTag(LocalTransaction localTransaction) {
                return localTransaction.getTransactionDifference();
            }

            @Override
            public String tagName() {
                return "difference";
            }
        };
    }

    /**
     * By date validator.
     *
     * @return the validator
     */
    @Bean
    @Primary
    public Validator<LocalTransaction, Timestamp> byDate(){
        return new Validator<>() {
            @Override
            public boolean validateObject(LocalTransaction object, Timestamp tag) {
                return object.getDate().equals(tag);
            }

            @Override
            public Timestamp extractTag(LocalTransaction localTransaction) {
                return localTransaction.getDate();
            }

            @Override
            public String tagName() {
                return "date";
            }
        };
    }

    /**
     * By date before validator.
     *
     * @return the validator
     */
    @Bean
    public Validator<LocalTransaction, Timestamp> byDateBefore(){
        return new Validator<>() {
            @Override
            public boolean validateObject(LocalTransaction object, Timestamp tag) {
                return object.getDate().compareTo(tag) < 0;
            }

            @Override
            public Timestamp extractTag(LocalTransaction localTransaction) {
                return null;
            }

            @Override
            public String tagName() {
                return null;
            }

            @Override
            public String validationRule(Timestamp tag) {
                return tagName() + " < " + tag.toString();
            }
        };
    }

    /**
     * By details validator.
     *
     * @return the validator
     */
    @Bean
    @Primary
    public Validator<LocalTransaction, String> byDetails(){
        return new Validator<>() {
            @Override
            public boolean validateObject(LocalTransaction object, String tag) {
                return object.getDetails().equals(tag);
            }
            @Override
            public String extractTag(LocalTransaction localTransaction) {
                return localTransaction.getDetails();
            }
            @Override
            public String tagName() {
                return "details";
            }
        };
    }

    /**
     * By details contains validator.
     *
     * @return the validator
     */
    @Bean
    public Validator<LocalTransaction, String> byDetailsContains(){
        return new Validator<>() {
            @Override
            public boolean validateObject(LocalTransaction object, String tag) {
                return object.getDetails().contains(tag);
            }

            @Override
            public String extractTag(LocalTransaction localTransaction) {
                return localTransaction.getDetails();
            }

            @Override
            public String tagName() {
                return "details";
            }
        };
    }
}
