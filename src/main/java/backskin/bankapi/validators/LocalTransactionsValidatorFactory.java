package backskin.bankapi.validators;

import backskin.bankapi.domain.LocalTransaction;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Configuration
public class LocalTransactionsValidatorFactory {
    @Bean
    @Qualifier("transactionValidatorByAccountId")
    Validator<LocalTransaction, Long> byAccountId(){
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

    @Bean
    @Qualifier("validatorByDifference")
    Validator<LocalTransaction, BigDecimal> byDifference(){
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

    @Bean
    @Primary
    Validator<LocalTransaction, Timestamp> byDate(){
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

    @Bean
    Validator<LocalTransaction, Timestamp> byDateBefore(){
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

    @Bean
    @Primary
    Validator<LocalTransaction, String> byDetails(){
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

    @Bean
    Validator<LocalTransaction, String> byDetailsContains(){
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
