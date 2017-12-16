package com.ddd.ask.presentation.query.converters;

import com.ddd.ask.application.service.query.commands.CreateQueryCommand;
import com.ddd.ask.domain.question.Question;
import com.ddd.ask.domain.subscriber.SubscriberId;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.lang.reflect.Type;

public class CreateQueryCommandJsonConverter implements JsonDeserializer<CreateQueryCommand> {

    @Override
    public CreateQueryCommand deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
        final JsonObject root = json.getAsJsonObject();
        final String subscriberId = root.get("subscriberId").getAsString();
        final String question = root.get("question").getAsString();
        return new CreateQueryCommand(new SubscriberId(subscriberId), new Question(question));
    }
}
