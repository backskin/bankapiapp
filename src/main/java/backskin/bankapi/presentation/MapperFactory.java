package backskin.bankapi.presentation;

import backskin.bankapi.domain.BankAccount;
import backskin.bankapi.domain.BankClient;
import backskin.bankapi.domain.DebitCard;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Calendar;

@Configuration
public class MapperFactory {

    @Bean
    Mapper<DebitCardInfo, DebitCard> infoDebitCardMapper(){
        return debitCard -> DebitCardInfo.builder().number(debitCard.getNumber()).Id(debitCard.getId()).build();
    }

    @Bean
    Mapper<String, Calendar> timeToCardDateMapper(){
        return calendar -> "'"+String.format("%02d",calendar.get(Calendar.MONTH))
                + "/" + String.valueOf(calendar.get(Calendar.YEAR)).substring(2)+"'";
    }

    @Bean
    Mapper<BankAccountInfo, BankAccount> infoBankAccountMapper(){
        return bankAccount -> BankAccountInfo.builder()
                .id(bankAccount.getId())
                .number(bankAccount.getNumber())
                .balance(bankAccount.getBalance())
                .build();
    }

    @Bean
    Mapper<BankClientInfo, BankClient> infoBankClientMapper(){
        return bankClient -> BankClientInfo.builder().fullName(bankClient.getFullName())
                .phoneNumber(bankClient.getPhoneNumber()).build();
    }
}
