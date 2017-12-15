package com.ddd.ask.infrastructure;

import com.ddd.ask.domain.query.Query;
import com.ddd.ask.domain.query.QueryId;
import com.ddd.ask.domain.query.QueryRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static java.lang.String.format;

public class InMemoryQueryRepository implements QueryRepository {

    private int resourceIdGenerator;
    private Set<Query> queries = new HashSet<>();

    public InMemoryQueryRepository(int resourceIdSeed) {
        this.resourceIdGenerator = validResourceIdSeed(resourceIdSeed);
    }

    @Override
    public QueryId nextIdentity() {
        return new QueryId(generateResourceId());
    }

    @Override
    public void save(Query query) {
        queries.add(query);
    }

    @Override
    public Optional<Query> find(QueryId id) {
        return queries.stream().filter(query -> query.id().equals(id)).findAny();
    }

    private String generateResourceId() {
        this.resourceIdGenerator++;
        String generatorString = String.valueOf(resourceIdGenerator);
        return format("a-%s-%s", generatorString.substring(0, 3), generatorString.substring(3, 7));
    }

    private int validResourceIdSeed(int resourceIdSeed) {
        if (resourceIdSeed > 0 && (int) (Math.log10(resourceIdSeed) + 1) == 7)
            return resourceIdSeed;
        else throw new InvalidResourceIdSeed();
    }
}
