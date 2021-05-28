package backskin.bankapi.services;

import backskin.bankapi.presentation.DebitCardCredentials;
import backskin.bankapi.presentation.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Random;
import java.util.TimeZone;
import java.util.function.Supplier;

/**
 * The type Debit card producer.
 */
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

    /**
     * Instantiates a new Debit card producer.
     *
     * @param dateOnCardMapper the date on card mapper
     */
    public DebitCardProducerImpl(Mapper<String, Calendar> dateOnCardMapper) {
        this.dateOnCardMapper = dateOnCardMapper;
    }

    /**
     * Sets time zone supplier.
     *
     * @param timeZoneSupplier the time zone supplier
     */
    @Autowired
    public void setTimeZoneSupplier(Supplier<TimeZone> timeZoneSupplier) {
        this.timeZoneSupplier = timeZoneSupplier;
    }

    /**
     * Sets card lifetime in years.
     *
     * @param cardLifetimeInYears the card lifetime in years
     */
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
