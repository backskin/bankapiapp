package backskin.bankapi.services;

import backskin.bankapi.presentation.DebitCardCredentials;
import backskin.bankapi.presentation.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Calendar;
import java.util.Random;
import java.util.TimeZone;
import java.util.function.Supplier;

@Component
public class DebitCardProducerImpl implements DebitCardProducer {

    private Supplier<TimeZone> timeZoneSupplier;
    private final Mapper<String, Calendar> dateOnCardMapper;
    private Integer lifetimeInYears;

    private String generateNumber(int numberLength){
        Random random = new Random();
        String output = "";
        for (int i = 0; i < numberLength; i++) {
            Integer digit = random.nextInt(10);
            output = output.concat(digit.toString());
        }
        return "'"+ output +"'";
    };

    public DebitCardProducerImpl(Mapper<String, Calendar> dateOnCardMapper) {
        this.dateOnCardMapper = dateOnCardMapper;
    }

    @Autowired
    public void setTimeZoneSupplier(Supplier<TimeZone> timeZoneSupplier) {
        this.timeZoneSupplier = timeZoneSupplier;
    }

    @Autowired
    public void setCardLifetimeInYears(@Value("5") Integer cardLifetimeInYears) {
        this.lifetimeInYears = cardLifetimeInYears;
    }

    @Override
    public DebitCardCredentials releaseDebitCard(Long accountId, Timestamp dateOfRelease) {
        Calendar cal = Calendar.getInstance(timeZoneSupplier.get());
        cal.setTime(dateOfRelease);
        cal.add(Calendar.YEAR, lifetimeInYears);
        return DebitCardCredentials.builder().number(generateNumber(16)).accountId(accountId)
                .cvvCode(generateNumber(3)).expirationDate(dateOnCardMapper.map(cal)).build();
    }
}
