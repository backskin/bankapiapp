package backskin.bankapi.config;

import backskin.bankapi.domain.DebitCard;
import backskin.bankapi.service.DebitCardProducer;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Configuration
public class AppConfiguration {

    @Bean
    @Scope("singleton")
    DataSource dataSource(){
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:DB/schema.sql")
                .addScript("classpath:DB/data.sql").build();
    }

    @Bean
    @Autowired
    @Synchronized
    Connection connection(DataSource dataSource) throws SQLException {
        Connection connection = dataSource.getConnection();
        connection.setAutoCommit(false);
        return connection;
    }
    @Bean
    DebitCardProducer debitCardProducer(){
        return new DebitCardProducer() {
            @Override
            public DebitCard releaseDebitCard() {
//                return DebitCard.builder().;
            }
        }
    }
    @Bean
    String bankClientsTableName(){
        return "bank_clients";
    }

    @Bean
    String bankAccountsTableName(){
        return "bank_accounts";
    }

    @Bean
    String debitCardsTableName(){
        return "debit_cards";
    }

    @Bean
    String localTransactionsTableName(){
        return "local_transactions";
    }
}
