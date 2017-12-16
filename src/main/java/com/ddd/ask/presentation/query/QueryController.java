package com.ddd.ask.presentation.query;

import com.ddd.ask.application.service.query.QueryApplicationService;
import com.ddd.ask.application.service.query.commands.CreateQueryCommand;
import com.ddd.ask.application.service.query.finders.QueryByIdFinder;
import com.ddd.ask.domain.query.Query;
import com.ddd.ask.domain.query.QueryId;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class QueryController {

    private final QueryApplicationService queryApplicationService;
    private final Gson jsonTransformer;

    public QueryController(QueryApplicationService queryApplicationService, Gson gson) {
        this.queryApplicationService = queryApplicationService;
        this.jsonTransformer = gson;
    }

    @GetMapping(path = "/queries/{id}")
    public ResponseEntity<String> getQueryById(@PathVariable String id) {
        final Optional<Query> query = queryApplicationService.findQueryById(new QueryByIdFinder(new QueryId(id)));
        return query.map(QueryResource::new).map(jsonTransformer::toJson)
                .map(queryJson -> new ResponseEntity<>(queryJson, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(path = "/queries")
    public ResponseEntity<String> createQuery(@RequestBody String requestJson) {
        final CreateQueryCommand createQueryCommand = jsonTransformer.fromJson(requestJson, CreateQueryCommand.class);
        queryApplicationService.createQuery(createQueryCommand);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
