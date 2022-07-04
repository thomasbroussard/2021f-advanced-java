package fr.epita.jpa.tests;

import fr.epita.jpa.datamodel.Contact;
import fr.epita.jpa.services.data.ContactJPADAO;
import fr.epita.jpa.services.data.JPADAO;
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
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class BaseTestConfig {


    @Bean("db.mainDatasource")
    public DataSource dataSource() {
        return new DriverManagerDataSource("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "test", "test");
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
    public JPADAO<Contact> getContactDAO(SessionFactory sf){
        return new ContactJPADAO(sf);
    }

//  Anonymous class instantiation
//    @Bean
//    public JPADAO<Contact> getContactDAOOverridden(SessionFactory sf){
//        return new JPADAO<Contact>(sf) {
//            @Override
//            public Query<Contact> getQuery(Contact criteria, Session session) {
//                return null;
//            }
//        };
//    }

}
