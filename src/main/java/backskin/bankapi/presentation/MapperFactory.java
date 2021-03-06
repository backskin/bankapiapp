package backskin.bankapi.presentation;

import backskin.bankapi.domain.BankAccount;
import backskin.bankapi.domain.BankClient;
import backskin.bankapi.domain.DebitCard;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Calendar;

/**
 * The Mappers' factory (Actually, it's a Mappers' _Bean_ factory.
 * And yet, not a pattern factory.).
 */
@Configuration
public class MapperFactory {

    /**
     * Info debit card mapper mapper.
     *
     * @return the mapper
     */
    @Bean
    public Mapper<DebitCardInfo, DebitCard> infoDebitCardMapper(){
        return debitCard -> DebitCardInfo.builder().number(debitCard.getNumber()).Id(debitCard.getId()).build();
    }

    /**
     * Time to card date mapper mapper.
     *
     * @return the mapper
     */
    @Bean
    public Mapper<String, Calendar> timeToCardDateMapper(){
        return calendar -> "'"+String.format("%02d",calendar.get(Calendar.MONTH))
                + "/" + String.valueOf(calendar.get(Calendar.YEAR)).substring(2)+"'";
    }

    /**
     * Info bank account mapper mapper.
     *
     * @return the mapper
     */
    @Bean
    public Mapper<BankAccountInfo, BankAccount> infoBankAccountMapper(){
        return bankAccount -> BankAccountInfo.builder()
                .id(bankAccount.getId())
                .number(bankAccount.getNumber())
                .balance(bankAccount.getBalance())
                .build();
    }

    /**
     * Info bank client mapper mapper.
     *
     * @return the mapper
     */
    @Bean
    public Mapper<BankClientInfo, BankClient> infoBankClientMapper(){
        return bankClient -> BankClientInfo.builder().fullName(bankClient.getFullName())
                .phoneNumber(bankClient.getPhoneNumber()).build();
    }
}
