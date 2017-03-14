package it.contactlab.hub.sdk.java.internal.api;

import it.contactlab.hub.sdk.java.Auth;
import it.contactlab.hub.sdk.java.exceptions.ApiException;
import it.contactlab.hub.sdk.java.exceptions.HttpException;
import it.contactlab.hub.sdk.java.exceptions.ServerException;
import it.contactlab.hub.sdk.java.gson.ContactHubGson;
import it.contactlab.hub.sdk.java.http.Request;
import it.contactlab.hub.sdk.java.models.Like;

import com.google.gson.Gson;

public class LikeApi {

  private static Gson gson = ContactHubGson.getInstance();

  /**
   * Add a new Like to a Customer.
   */
  public static Like add(Auth auth, String customerId, Like like)
      throws ApiException, ServerException, HttpException {

    String endpoint = "/customers/" + customerId + "/likes";
    String payload = gson.toJson(like);
    String response = Request.doPost(auth, endpoint, payload);

    return gson.fromJson(response, Like.class);
  }

  /**
   * Update an existing Like.
   */
  public static Like update(Auth auth, String customerId, Like like)
      throws ApiException, ServerException, HttpException {

    String endpoint = "/customers/" + customerId + "/likes/" + like.id();
    String payload = gson.toJson(like);
    String response = Request.doPut(auth, endpoint, payload);

    return gson.fromJson(response, Like.class);
  }

  /**
   * Remove a tag from a Customer.
   */
  public static boolean remove(Auth auth, String customerId, String likeId)
      throws ApiException, ServerException, HttpException {

    String endpoint = "/customers/" + customerId + "/likes/" + likeId;
    String response = Request.doDelete(auth, endpoint);

    return true;
  }
}
