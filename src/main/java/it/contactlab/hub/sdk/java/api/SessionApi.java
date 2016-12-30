package it.contactlab.hub.sdk.java.api;

import it.contactlab.hub.sdk.java.Auth;
import it.contactlab.hub.sdk.java.exceptions.HttpException;
import it.contactlab.hub.sdk.java.gson.ContactHubGson;
import it.contactlab.hub.sdk.java.http.Request;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.json.JSONObject;

import java.util.UUID;

public class SessionApi {

  private static Gson gson = ContactHubGson.getInstance();

  private static String baseUrl = "https://api.contactlab.it/hub/v1";

  /**
   * Generate a new SessionId.
   */
  public static String generate() {
    return UUID.randomUUID().toString();
  }

  /**
   * Reconcile a SessionId with a Customer.
   */
  public static boolean reconcile(Auth auth, String customerId, String sessionId)
      throws HttpException {
    String endpoint = "/customers/" + customerId + "/sessions";
    JsonObject session = new JsonObject();
    session.addProperty("value", sessionId);

    String payload = gson.toJson(session);
    JSONObject response = Request.doPost(auth, endpoint, payload);

    return true;
  }

}
