package backskin.bankapi.services;

import backskin.bankapi.dao.DebitCardDAO;
import backskin.bankapi.dao.SqlDAO;
import backskin.bankapi.dao.SqlRepo;
import backskin.bankapi.domain.DebitCard;
import backskin.bankapi.presentation.DebitCardCredentials;
import backskin.bankapi.presentation.DebitCardInfo;
import backskin.bankapi.presentation.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * The type Debit card service.
 */
@Service
public class DebitCardService {

    private final DebitCardProducer cardProducer;
    private final SqlDAO<DebitCard> debitCardDAO;
    private final SqlRepo<DebitCard> debitCardSqlRepo;
    private final Supplier<TimeZone> timeZoneSupplier;
    private Mapper<DebitCardInfo, DebitCard> infoDebitCardMapper;

    /**
     * Instantiates a new Debit card service.
     *
     * @param cardProducer     the card producer
     * @param debitCardDAO     the debit card dao
     * @param debitCardSqlRepo the debit card sql repo
     * @param timeZoneSupplier the time zone supplier
     */
    public DebitCardService(DebitCardProducer cardProducer,
                            DebitCardDAO debitCardDAO,
                            SqlRepo<DebitCard> debitCardSqlRepo,
                            Supplier<TimeZone> timeZoneSupplier) {
        this.cardProducer = cardProducer;
        this.debitCardDAO = debitCardDAO;
        this.debitCardSqlRepo = debitCardSqlRepo;
        this.timeZoneSupplier = timeZoneSupplier;
    }

    /**
     * Sets info debit card mapper.
     *
     * @param infoDebitCardMapper the info debit card mapper
     */
    @Autowired
    public void setInfoDebitCardMapper(Mapper<DebitCardInfo, DebitCard> infoDebitCardMapper) {
        this.infoDebitCardMapper = infoDebitCardMapper;
    }

    /**
     * Gets all cards info.
     *
     * @return the all cards info
     * @throws SQLException the sql exception
     */
    public List<DebitCardInfo> getAllCardsInfo() throws SQLException {
        return debitCardSqlRepo.getAll().stream().map(infoDebitCardMapper::map).collect(Collectors.toList());
    }

    /**
     * Claim new debit card debit card credentials.
     *
     * @param requestDate the request date
     * @param accountId   the account id
     * @return the debit card credentials
     * @throws Exception the exception
     */
    public DebitCardCredentials claimNewDebitCard(Timestamp requestDate, Long accountId) throws Exception {
        Calendar cal = Calendar.getInstance(timeZoneSupplier.get());
        cal.setTime(new Date(requestDate.getTime()));
        return cardProducer.releaseDebitCard(accountId,requestDate);
    }

    /**
     * Save debit card debit card.
     *
     * @param cardCredentials the card credentials
     * @return the debit card
     * @throws SQLException the sql exception
     */
    public DebitCard saveDebitCard(DebitCardCredentials cardCredentials) throws SQLException {
        return debitCardDAO.create(DebitCard.builder().number(cardCredentials.getNumber())
                .bankAccountId(cardCredentials.getAccountId()).expirationDate(cardCredentials.getExpirationDate())
                .cvvCode(cardCredentials.getCvvCode()).build());
    }
}
