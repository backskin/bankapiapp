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
 * Base App configuration class
 */
@Configuration
public class AppConfiguration {

    /**
     * Bean - Data source  producer
     *
     * @return the data source
     */
    @Bean
    @Scope("singleton")
    public DataSource dataSource(){
        return new EmbeddedDatabaseBuilder().setName("bank_database")
                .setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:DB/schema.sql")
                .addScript("classpath:DB/data.sql").build();
    }

    /**
     * Bean - Production of a connection to the DB
     *
     * @param dataSource the data source
     * @return the connection
     * @throws SQLException the sql exception
     */
    @Bean
    @Autowired
    @Synchronized
    public Connection connection(DataSource dataSource) throws SQLException {
        Connection connection = dataSource.getConnection();
        connection.setAutoCommit(false);
        return connection;
    }

    /**
     * Bean - Bank clients table name string.
     *
     * @return Bank clients table name
     */
    @Bean
    public String bankClientsTableName(){
        return "bank_clients";
    }

    /**
     * Bean - Bank accounts table name string.
     *
     * @return Bank accounts table name
     */
    @Bean
    public String bankAccountsTableName(){
        return "bank_accounts";
    }

    /**
     * Bean -  Debit cards table name string.
     *
     * @return Debit cards table name
     */
    @Bean
    public String debitCardsTableName(){
        return "debit_cards";
    }

    /**
     * Bean - Local transactions table name string.
     *
     * @return Local transactions table name
     */
    @Bean
    public String localTransactionsTableName(){
        return "local_transactions";
    }
}
