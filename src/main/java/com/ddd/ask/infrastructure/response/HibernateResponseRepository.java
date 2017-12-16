package com.ddd.ask.infrastructure.response;

import com.ddd.ask.domain.query.QueryId;
import com.ddd.ask.domain.response.Response;
import com.ddd.ask.domain.response.ResponseId;
import com.ddd.ask.domain.response.ResponseRepository;
import org.hibernate.SessionFactory;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public class HibernateResponseRepository implements ResponseRepository {

    private SessionFactory sessionFactory;

    public HibernateResponseRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public ResponseId nextIdentity() {
        Long nextValue = ((BigInteger) sessionFactory.getCurrentSession()
                .createSQLQuery("SELECT RESPONSE_ID_GENERATOR_SEQUENCE.NEXTVAL FROM DUAL")
                .list()
                .get(0)).longValue();
        return new ResponseId(nextValue);
    }

    @Override
    public void save(Response response) {
        sessionFactory.getCurrentSession().save(response);
    }

    @Override
    public Optional<Response> find(ResponseId id) {
        final Response response = sessionFactory.getCurrentSession().get(Response.class, id);
        return Optional.ofNullable(response);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Response> find(QueryId queryId) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Response where queryId = :queryId")
                .setParameter("queryId", queryId)
                .list();
    }
}
