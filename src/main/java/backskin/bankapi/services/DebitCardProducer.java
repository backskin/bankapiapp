package backskin.bankapi.services;

import backskin.bankapi.presentation.DebitCardCredentials;

import java.sql.Timestamp;

/**
 * The interface Debit card producer.
 */
public interface DebitCardProducer {
    /**
     * Release debit card debit card credentials.
     *
     * @param accountId     the account id
     * @param dateOfRelease the date of release
     * @return the debit card credentials
     * @throws Exception the exception
     */
    DebitCardCredentials releaseDebitCard(Long accountId, Timestamp dateOfRelease) throws Exception;
}
