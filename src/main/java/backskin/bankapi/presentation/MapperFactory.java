package backskin.bankapi.presentation;

import backskin.bankapi.domain.BankAccount;
import backskin.bankapi.domain.DebitCard;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Calendar;
import java.util.Optional;

@Configuration
public class MapperFactory {

    @Bean
    Mapper<DebitCardInfo, DebitCard> infoDebitCardMapper(){
        return debitCard -> Optional.of(DebitCardInfo.builder().number(debitCard.getNumber()).Id(debitCard.getId()).build());
    }

    @Bean
    Mapper<String, Calendar> timeToCardDateMapper(){
        return calendar -> Optional.of(calendar.get(Calendar.MONTH) + "//" + calendar.get(Calendar.YEAR))
    }

    @Bean
    Mapper<BankAccountInfo, BankAccount> infoBankAccountMapper(){
        return bankAccount -> Optional.of(BankAccountInfo.builder().number(bankAccount.getNumber()).balance(bankAccount.getBalance()).build());
    }
}
