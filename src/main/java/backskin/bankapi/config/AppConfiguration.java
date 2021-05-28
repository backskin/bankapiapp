package backskin.bankapi.config;

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

/**
 * The type App configuration.
 */
@Configuration
public class AppConfiguration {

    /**
     * Data source data source.
     *
     * @return the data source
     */
    @Bean
    @Scope("singleton")
    DataSource dataSource(){
        return new EmbeddedDatabaseBuilder().setName("bank_database")
                .setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:DB/schema.sql")
                .addScript("classpath:DB/data.sql").build();
    }

    /**
     * Connection connection.
     *
     * @param dataSource the data source
     * @return the connection
     * @throws SQLException the sql exception
     */
    @Bean
    @Autowired
    @Synchronized
    Connection connection(DataSource dataSource) throws SQLException {
        Connection connection = dataSource.getConnection();
        connection.setAutoCommit(false);
        return connection;
    }

    /**
     * Bank clients table name string.
     *
     * @return the string
     */
    @Bean
    String bankClientsTableName(){
        return "bank_clients";
    }

    /**
     * Bank accounts table name string.
     *
     * @return the string
     */
    @Bean
    String bankAccountsTableName(){
        return "bank_accounts";
    }

    /**
     * Debit cards table name string.
     *
     * @return the string
     */
    @Bean
    String debitCardsTableName(){
        return "debit_cards";
    }

    /**
     * Local transactions table name string.
     *
     * @return the string
     */
    @Bean
    String localTransactionsTableName(){
        return "local_transactions";
    }
}
