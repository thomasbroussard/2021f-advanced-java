package fr.epita.jpa.tests;

import fr.epita.jpa.datamodel.Contact;
import fr.epita.jpa.datamodel.Job;
import fr.epita.jpa.services.data.ContactJPADAO;
import fr.epita.jpa.services.data.ContactRepository;
import fr.epita.jpa.services.data.JPADAO;
import fr.epita.jpa.services.data.JobJPADAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.dialect.H2Dialect;
import org.hibernate.dialect.PostgreSQL10Dialect;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class BaseTestConfig {


    @Bean("db.mainDatasource")
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "test", "test");

        //if necessary we can fill the data base with some initial data by getting a connection from the datasource

        return dataSource;


    }

    @Bean
    public LocalSessionFactoryBean sessionFactoryBean(@Autowired DataSource dataSource){
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan("fr.epita.jpa.datamodel");

        Properties properties = new Properties();
        properties.setProperty("hibernate.properties", PostgreSQL10Dialect.class.getName());
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.show_sql","true");
        sessionFactory.setHibernateProperties(properties);
        return sessionFactory;
    }


    @Bean
    public ContactJPADAO getContactDAO(SessionFactory sf){
        return new ContactJPADAO(sf);
    }

    @Bean
    public JobJPADAO getJobDAO(SessionFactory sf){
        return new JobJPADAO(sf);
    }


    @Bean
    ContactRepository repo(SessionFactory sf,
                           ContactJPADAO contactDao,
                           JobJPADAO jobDao){
        return new ContactRepository(sf, contactDao, jobDao);
    }


    @Bean
    public TransactionManager getTxManager(@Autowired SessionFactory sf){
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sf);
        return transactionManager;
    }


}
