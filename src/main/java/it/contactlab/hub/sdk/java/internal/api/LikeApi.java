package it.contactlab.hub.sdk.java.internal.api;

import it.contactlab.hub.sdk.java.Auth;
import it.contactlab.hub.sdk.java.exceptions.ContactHubException;
import it.contactlab.hub.sdk.java.exceptions.HttpException;
import it.contactlab.hub.sdk.java.gson.ContactHubGson;
import it.contactlab.hub.sdk.java.http.Request;
import it.contactlab.hub.sdk.java.models.Like;

import com.google.gson.Gson;
import org.json.JSONObject;

public class LikeApi {

  private static Gson gson = ContactHubGson.getInstance();

  /**
   * Add a new Like to a Customer.
   */
  public static Like add(Auth auth, String customerId, Like like)
      throws ContactHubException, HttpException {

    String endpoint = "/customers/" + customerId + "/likes";
    String payload = gson.toJson(like);
    JSONObject response = Request.doPost(auth, endpoint, payload);

    return gson.fromJson(response.toString(), Like.class);
  }

  /**
   * Update an existing Like.
   */
  public static Like update(Auth auth, String customerId, Like like)
      throws ContactHubException, HttpException {

    String endpoint = "/customers/" + customerId + "/likes/" + like.id();
    String payload = gson.toJson(like);
    JSONObject response = Request.doPut(auth, endpoint, payload);

    return gson.fromJson(response.toString(), Like.class);
  }

  /**
   * Remove a tag from a Customer.
   */
  public static boolean remove(Auth auth, String customerId, String likeId)
      throws ContactHubException, HttpException {

    String endpoint = "/customers/" + customerId + "/likes/" + likeId;
    JSONObject response = Request.doDelete(auth, endpoint);

    return true;
  }
}
