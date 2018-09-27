package org.superbiz.moviefun;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
public class MoviesDatabaseConfig {

    @Bean
    public DataSource movieDataSource(DatabaseServiceCredentials serviceCredentials) {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setURL(serviceCredentials.jdbcUrl("movies-mysql"));
        return dataSource;
    }

    @Bean
    LocalContainerEntityManagerFactoryBean moviesEntityManagerFactory(DataSource movieDataSource, HibernateJpaVendorAdapter jpaVendorAdapter){

        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(movieDataSource);
        factoryBean.setJpaVendorAdapter(jpaVendorAdapter);
        factoryBean.setPackagesToScan(DatabaseConfig.class.getPackage().getName());
        factoryBean.setPersistenceUnitName("movies");
        return factoryBean;
    }

    @Bean
    PlatformTransactionManager moviesTransactionManager(EntityManagerFactory moviesEntityManagerFactory) {
        return new JpaTransactionManager(moviesEntityManagerFactory);
    }

}
