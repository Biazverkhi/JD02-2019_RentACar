package by.fastrentcar.springdata.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@Import(SettingsConfig.class)
@EnableJpaRepositories(basePackages = {"by.fastrentcar.springdata.repository"})

public class HibernateConfig {

    private final SettingsConfig settingsConfig;

    public HibernateConfig(SettingsConfig settingsConfig) {
        this.settingsConfig = settingsConfig;
    }

    @Bean
    public DataSource dataSource() {

        final DatasourseSettings datasourseSettings = settingsConfig.datasourseSettings();
        final HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setJdbcUrl(datasourseSettings.getUrl());
        hikariDataSource.setUsername(datasourseSettings.getUser());
        hikariDataSource.setPassword(datasourseSettings.getPassword());
        hikariDataSource.setDriverClassName(datasourseSettings.getDriver());
        hikariDataSource.setMaximumPoolSize(20);
        return hikariDataSource;
    }

    @Bean

    public LocalSessionFactoryBean entityManagerFactory() {
        final LocalSessionFactoryBean entityManager = new LocalSessionFactoryBean();
        entityManager.setDataSource(dataSource());
        entityManager.setPackagesToScan("by.fastrentcar.springdata.entities");
        entityManager.setHibernateProperties(settingsConfig.hibernateProperties());
        return entityManager;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        HibernateTransactionManager transactionManager
                = new HibernateTransactionManager();
        transactionManager.setSessionFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

//    @Bean
//    public TransactionTemplate transactionTemplate() {
//        return new TransactionTemplate(hibernateTransactionManager());
//    }


}
