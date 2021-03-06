package com.microfocus.ring2parkms.users;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

/**
 * The users Spring configuration.
 *
 * @author Kevin A. Lee
 */
@Configuration
@ComponentScan
@EntityScan("com.microfocus.ring2parkms.users")
@EnableJpaRepositories("com.microfocus.ring2parkms.users")
@PropertySource("classpath:db-config.properties")
public class UsersConfiguration {

    protected Logger logger;

    public UsersConfiguration() {
        logger = Logger.getLogger(getClass().getName());
    }

    /**
     * Creates an in-memory "users" database populated with test data for fast
     * testing
     */
    @Bean
    public DataSource dataSource() {
        logger.info("dataSource() invoked");

        // Create an in-memory H2 relational database containing some demo
        // userse.
        DataSource dataSource = (new EmbeddedDatabaseBuilder()).addScript("classpath:testdb/schema.sql")
                .addScript("classpath:testdb/data.sql").build();

        logger.info("dataSource = " + dataSource);

        // Sanity check
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Map<String, Object>> users = jdbcTemplate.queryForList("SELECT username FROM T_USER");
        logger.info("System has " + users.size() + " users");

        // Populate with random balances
        /*Random rand = new Random();

        for (Map<String, Object> item : users) {
            String number = (String) item.get("number");
            BigDecimal balance = new BigDecimal(rand.nextInt(10000000) / 100.0).setScale(2, BigDecimal.ROUND_HALF_UP);
            jdbcTemplate.update("UPDATE T_ACCOUNT SET balance = ? WHERE number = ?", balance, number);
        }*/

        return dataSource;
    }
}

