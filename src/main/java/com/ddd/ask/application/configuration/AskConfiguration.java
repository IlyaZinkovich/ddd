package com.ddd.ask.application.configuration;

import com.ddd.ask.application.service.query.QueryApplicationService;
import com.ddd.ask.application.service.response.ResponseApplicationService;
import com.ddd.ask.domain.events.DomainEventPublisher;
import com.ddd.ask.domain.query.QueryRepository;
import com.ddd.ask.domain.response.ResponseRepository;
import com.ddd.ask.infrastructure.events.InMemoryDomainEventPublisher;
import com.ddd.ask.infrastructure.query.HibernateQueryRepository;
import com.ddd.ask.infrastructure.response.HibernateResponseRepository;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;

import javax.sql.DataSource;

import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.H2;

@Configuration
public class AskConfiguration {

    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder().setType(H2)
                .setName("db;DATABASE_TO_UPPER=false;MODE=Oracle")
                .addScript("schema.sql")
                .build();
    }

    @Bean
    public QueryRepository queryRepository(SessionFactory sessionFactory) {
        return new HibernateQueryRepository(sessionFactory);
    }

    @Bean
    public SessionFactory sessionFactory(DataSource dataSource) {
        return new LocalSessionFactoryBuilder(dataSource)
                .configure("hibernate/hibernate.cfg.xml")
                .buildSessionFactory();
    }

    @Bean
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }

    @Bean
    public DomainEventPublisher domainEventPublisher() {
        return new InMemoryDomainEventPublisher();
    }

    @Bean
    public ResponseRepository responseRepository(SessionFactory sessionFactory) {
        return new HibernateResponseRepository(sessionFactory);
    }

    @Bean
    public QueryApplicationService queryApplicationService(QueryRepository repository,
                                                           DomainEventPublisher eventPublisher) {
        return new QueryApplicationService(repository, eventPublisher);
    }

    @Bean
    public ResponseApplicationService responseApplicationService(ResponseRepository responseRepository,
                                                                 DomainEventPublisher eventPublisher) {
        return new ResponseApplicationService(responseRepository, eventPublisher);
    }
}
