package org.superbiz.moviefun;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
public class DatabaseConfig {

    @Bean
    DatabaseServiceCredentials getVCAP(@Value("${VCAP_SERVICES}") String vcap_services){
        return new DatabaseServiceCredentials(vcap_services);
    }

    @Bean
    HibernateJpaVendorAdapter myHbAdapter() {
        HibernateJpaVendorAdapter myHbAdapter = new HibernateJpaVendorAdapter();
        myHbAdapter.setGenerateDdl(true);
        myHbAdapter.setDatabase(Database.MYSQL);
        myHbAdapter.setDatabasePlatform("org.hibernate.dialect.MySQL5Dialect");
        return myHbAdapter;
    }

}
