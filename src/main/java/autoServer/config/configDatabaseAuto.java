//package autoServer.config;
//
//import autoServer.Utils.contains;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//import org.springframework.transaction.support.TransactionTemplate;
//
//import javax.sql.DataSource;
//import java.util.HashMap;
//
//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(basePackages = "autoServer.repository",
//                        entityManagerFactoryRef = "entityManagerFactory",
//                        transactionManagerRef = "transactionManager")
//public class configDatabaseAuto {
//    @Bean("datasourceAuto")
//    @ConfigurationProperties(prefix = "spring.datasource")
//    public DataSource datasourceAuto() {
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean("entityManagerFactory")
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Qualifier("datasourceAuto") DataSource datasource) {
//        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
//        em.setDataSource(datasource);
//        em.setPersistenceUnitName(contains.enityManagerName);
//        em.setPackagesToScan("autoServer.Entity");
//        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        em.setJpaVendorAdapter(vendorAdapter);
//        HashMap<String, Object> properties = new HashMap<>();
//        properties.put("hibernate.dialect", "org.hibernate.dialect.MariaDB53Dialect");
//        em.setJpaPropertyMap(properties);
//        return em;
//    }
//    @Bean("transactionManager")
//    public JpaTransactionManager transactionManagerV1(@Qualifier("entityManagerFactory") LocalContainerEntityManagerFactoryBean entityManagerV1) {
//        JpaTransactionManager transactionManager = new JpaTransactionManager();
//        transactionManager.setEntityManagerFactory(entityManagerV1.getObject());
//        return transactionManager;
//    }
//
//    @Bean("transactionTemplate")
//    public TransactionTemplate transactionTemplate(@Qualifier("transactionManager") JpaTransactionManager transactionManagerV1) {
//        return new TransactionTemplate(transactionManagerV1);
//    }
//
//    @Bean("jdbcTemplate")
//    public JdbcTemplate jdbcTemplate(@Qualifier("datasourceAuto") DataSource datasourceV1) {
//        return new JdbcTemplate(datasourceV1);
//    }
//}
