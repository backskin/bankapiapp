package backskin.bankapi.services;

import backskin.bankapi.dao.CRUD;
import backskin.bankapi.dao.DebitCardDAO;
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

@Service
public class DebitCardService {

    private final DebitCardProducer cardProducer;
    private final CRUD<DebitCard, Long> debitCardDAO;
    private final SqlRepo<DebitCard> debitCardSqlRepo;
    private final Supplier<TimeZone> timeZoneSupplier;
    private Mapper<DebitCardInfo, DebitCard> infoDebitCardMapper;

    public DebitCardService(DebitCardProducer cardProducer,
                            DebitCardDAO debitCardDAO,
                            SqlRepo<DebitCard> debitCardSqlRepo,
                            Supplier<TimeZone> timeZoneSupplier) {
        this.cardProducer = cardProducer;
        this.debitCardDAO = debitCardDAO;
        this.debitCardSqlRepo = debitCardSqlRepo;
        this.timeZoneSupplier = timeZoneSupplier;
    }

    @Autowired
    public void setInfoDebitCardMapper(Mapper<DebitCardInfo, DebitCard> infoDebitCardMapper) {
        this.infoDebitCardMapper = infoDebitCardMapper;
    }

    public List<DebitCardInfo> getAllCardsInfo() throws SQLException {
        return debitCardSqlRepo.getAll().stream().map(infoDebitCardMapper::map).collect(Collectors.toList());
    }

    public DebitCardCredentials claimNewDebitCard(Timestamp requestDate, Long accountId) throws Exception {
        Calendar cal = Calendar.getInstance(timeZoneSupplier.get());
        cal.setTime(new Date(requestDate.getTime()));
        return cardProducer.releaseDebitCard(accountId,requestDate);
    }

    public DebitCardInfo saveDebitCard(DebitCardCredentials cardCredentials) throws Exception {
        DebitCard brandNewCard = debitCardDAO.create(DebitCard.builder().number(cardCredentials.getNumber())
                .bankAccountId(cardCredentials.getAccountId()).expirationDate(cardCredentials.getExpirationDate())
                .cvvCode(cardCredentials.getCvvCode()).build());
        return infoDebitCardMapper.map(brandNewCard);
    }
}
