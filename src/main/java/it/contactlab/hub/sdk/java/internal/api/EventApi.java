package it.contactlab.hub.sdk.java.internal.api;

import it.contactlab.hub.sdk.java.Auth;
import it.contactlab.hub.sdk.java.exceptions.HttpException;
import it.contactlab.hub.sdk.java.gson.ContactHubGson;
import it.contactlab.hub.sdk.java.http.Request;
import it.contactlab.hub.sdk.java.models.Event;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

public class EventApi {

  private static Gson gson = ContactHubGson.getInstance();

  private static String baseUrl = "https://api.contactlab.it/hub/v1";

  /**
   * Add a new Event.
   */
  public static Boolean add(Auth auth, Event event) throws HttpException {
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

    JSONObject response = Request.doPost(auth, endpoint, payload);

    return true;
  }

  /**
   * Retrieves an Event by id.
   */
  public static Event get(Auth auth, String id) throws HttpException {
    String endpoint = "/events/" + id;
    JSONObject response = Request.doGet(auth, endpoint);

    return gson.fromJson(response.toString(), Event.class);
  }

  /**
   * Retrieves all Events for a Customer.
   */
  public static List<Event> getByCustomer(Auth auth, String customerId) throws HttpException {
    String endpoint = "/events?customerId=" + customerId;
    JSONObject response = Request.doGet(auth, endpoint);

    Type collectionType = new TypeToken<List<Event>>(){}.getType();

    List<Event> events = gson.fromJson(response
        .getJSONArray("elements")
        .toString(),
        collectionType);

    return events;
  }

}
