package com.ddd.ask.infrastructure.query;

import com.ddd.ask.domain.query.Query;
import com.ddd.ask.domain.query.QueryId;
import com.ddd.ask.domain.query.QueryRepository;
import org.hibernate.SessionFactory;

import java.math.BigInteger;
import java.util.Optional;

import static java.lang.String.format;

public class HibernateQueryRepository implements QueryRepository {

    private final SessionFactory sessionFactory;

    public HibernateQueryRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public QueryId nextIdentity() {
        Long nextValue = ((BigInteger) sessionFactory.getCurrentSession()
                .createSQLQuery("SELECT RESOURCE_ID_GENERATOR_SEQUENCE.NEXTVAL FROM DUAL")
                .list()
                .get(0)).longValue();
        return new QueryId(generateResourceId(nextValue));
    }

    @Override
    public void save(Query query) {
        sessionFactory.getCurrentSession().save(query);
    }

    @Override
    public Optional<Query> find(QueryId id) {
        final Query query = sessionFactory.getCurrentSession().get(Query.class, id);
        return Optional.ofNullable(query);
    }

    private String generateResourceId(Long nextResourceIdSequenceValue) {
        String generatorString = String.valueOf(nextResourceIdSequenceValue);
        return format("a-%s-%s", generatorString.substring(0, 3), generatorString.substring(3, 7));
    }
}
