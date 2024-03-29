package com.ddd.ask.application.service.query.commands;

import com.ddd.ask.domain.editor.EditorId;
import com.ddd.ask.domain.query.QueryId;

public class AssignQueryCommand {

    private final QueryId queryId;
    private final EditorId editorId;

    public AssignQueryCommand(QueryId queryId, EditorId editorId) {
        this.queryId = queryId;
        this.editorId = editorId;
    }

    public QueryId queryId() {
        return queryId;
    }

    public EditorId editorId() {
        return editorId;
    }
}
