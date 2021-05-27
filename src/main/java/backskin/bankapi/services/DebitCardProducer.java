package backskin.bankapi.services;

import backskin.bankapi.presentation.DebitCardCredentials;

import java.sql.Timestamp;

public interface DebitCardProducer {
    DebitCardCredentials releaseDebitCard(Long accountId, Timestamp dateOfRelease) throws Exception;
}
