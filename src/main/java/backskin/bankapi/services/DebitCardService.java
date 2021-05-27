package backskin.bankapi.services;

import backskin.bankapi.dao.CRUD;
import backskin.bankapi.dao.DebitCardDAO;
import backskin.bankapi.domain.DebitCard;
import backskin.bankapi.presentation.DebitCardCredentials;
import backskin.bankapi.presentation.DebitCardInfo;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.function.Supplier;

@Service
public class DebitCardService {

    private DebitCardProducer cardProducer;
    private CRUD<DebitCard, Long> debitCardDAO;
    private Supplier<TimeZone> timeZoneSupplier;

    public DebitCardService(DebitCardProducer cardProducer, DebitCardDAO debitCardDAO, Supplier<TimeZone> timeZoneSupplier) {
        this.cardProducer = cardProducer;
        this.debitCardDAO = debitCardDAO;
        this.timeZoneSupplier = timeZoneSupplier;
    }

    public DebitCardCredentials claimNewDebitCard(Timestamp requestDate, Long accountId) throws Exception {
        Calendar cal = Calendar.getInstance(timeZoneSupplier.get());
        cal.setTime(new Date(requestDate.getTime()));
        return cardProducer.releaseDebitCard(accountId,requestDate);
    }

    public DebitCardInfo saveDebitCard(DebitCardCredentials cardCredentials) throws Exception {
        debitCardDAO.create(DebitCard.builder().number(cardCredentials.getNumber())
                .bankAccountId(cardCredentials.getAccountId()).expirationDate(cardCredentials.getExpirationDate())
                .cvvCode(cardCredentials.getCvvCode()).build());
    }
}
