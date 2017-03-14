package it.contactlab.hub.sdk.java.internal.api;

import it.contactlab.hub.sdk.java.Auth;
import it.contactlab.hub.sdk.java.exceptions.ApiException;
import it.contactlab.hub.sdk.java.exceptions.ContactHubException;
import it.contactlab.hub.sdk.java.exceptions.HttpException;
import it.contactlab.hub.sdk.java.exceptions.ServerException;
import it.contactlab.hub.sdk.java.gson.ContactHubGson;
import it.contactlab.hub.sdk.java.http.Request;
import it.contactlab.hub.sdk.java.models.AsyncPaginated;
import it.contactlab.hub.sdk.java.models.Event;
import it.contactlab.hub.sdk.java.models.EventFilters;
import it.contactlab.hub.sdk.java.models.Paged;
import it.contactlab.hub.sdk.java.models.Paginated;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;

public class EventApi {

  private static Gson gson = ContactHubGson.getInstance();

  private static String baseUrl = "https://api.contactlab.it/hub/v1";

  /**
   * Add a new Event.
   */
  public static void add(Auth auth, Event event)
      throws ApiException, ServerException, HttpException {
    final String endpoint = "/events";
    String payload = "";

    JsonObject jsonPayload = (JsonObject) gson.toJsonTree(event);

    // `externalId` and `sessionId` must be wrapped in a `bringBackProperties`
    // object before being sent to the API.
    jsonPayload.remove("externalId");
    jsonPayload.remove("sessionId");

    if (event.customerId().isPresent()) {

      payload = jsonPayload.toString();

    } else if (event.externalId().isPresent()) {

      JsonObject bringBackProperties = new JsonObject();
      bringBackProperties.addProperty("type", "EXTERNAL_ID");
      bringBackProperties.addProperty("value", event.externalId().get());
      bringBackProperties.addProperty("nodeId", auth.nodeId);

      jsonPayload.add("bringBackProperties", bringBackProperties);
      payload = jsonPayload.toString();

    } else if (event.sessionId().isPresent()) {

      JsonObject bringBackProperties = new JsonObject();
      bringBackProperties.addProperty("type", "SESSION_ID");
      bringBackProperties.addProperty("value", event.sessionId().get());
      bringBackProperties.addProperty("nodeId", auth.nodeId);

      jsonPayload.add("bringBackProperties", bringBackProperties);
      payload = jsonPayload.toString();

    } else {

      throw new RuntimeException("You must specify a customerId or an externalId or a sessionId");

    }

    Request.doPost(auth, endpoint, payload);
  }

  private static Paged<Event> getPaged(
      Auth auth, String customerId, EventFilters filters
  ) throws ApiException, ServerException, HttpException {
    final String endpoint = "/events";

    Map<String, Object> queryString = new HashMap<>();

    queryString.put("customerId", customerId);

    filters.page().ifPresent(page -> queryString.put("page", page.toString()));
    filters.type().ifPresent(type -> queryString.put("type", type.toString()));
    filters.context().ifPresent(context -> queryString.put("context", context.toString()));
    filters.mode().ifPresent(mode -> queryString.put("mode", mode.toString()));
    filters.dateFrom().ifPresent(date -> queryString.put(
          "dateFrom", ContactHubGson.formatDate(date)
    ));
    filters.dateTo().ifPresent(date -> queryString.put(
          "dateTo", ContactHubGson.formatDate(date)
    ));

    String response = Request.doGet(auth, endpoint, queryString);

    Type pagedEventType = new TypeToken<Paged<Event>>(){}.getType();
    Paged<Event> pagedEvents = gson.fromJson(response, pagedEventType);

    return pagedEvents;
  }

  /**
   * Async version of get.
   */
  public static CompletionStage<AsyncPaginated<Event>> asyncGet(
      Auth auth, String customerId, EventFilters filters
  ) {
    Function<Integer, CompletionStage<AsyncPaginated<Event>>>
        requestFunction = (Integer pageNumber) ->
            asyncGet(auth, customerId, filters.withPage(pageNumber));

    return CompletableFuture.supplyAsync(() -> {
      try {
        Paged<Event> pagedEvents = getPaged(auth, customerId, filters);

        return new AsyncPaginated<Event>(pagedEvents, requestFunction);
      } catch (Exception ex) {
        throw new CompletionException(ex);
      }
    });
  }

  /**
   * Retrieves all Events for a Customer, with filters.
   */
  public static Paginated<Event> get(
      Auth auth, String customerId, EventFilters filters
  ) throws ApiException, ServerException, HttpException {
    Paged<Event> pagedEvents = getPaged(auth, customerId, filters);

    Function<Integer, Paginated<Event>> requestFunction = (Integer pageNumber) -> {
      try {
        return get(auth, customerId, filters.withPage(pageNumber));
      } catch (ContactHubException exception) {
        throw new RuntimeException(exception);
      }
    };

    return new Paginated<Event>(pagedEvents, requestFunction);
  }

  /**
   * Retrieves an Event by id.
   */
  public static Event getById(Auth auth, String id)
      throws ApiException, ServerException, HttpException {
    String endpoint = "/events/" + id;
    String response = Request.doGet(auth, endpoint);

    return gson.fromJson(response, Event.class);
  }

}
