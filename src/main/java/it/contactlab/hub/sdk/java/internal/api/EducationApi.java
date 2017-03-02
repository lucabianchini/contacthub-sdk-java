package it.contactlab.hub.sdk.java.internal.api;

import it.contactlab.hub.sdk.java.Auth;
import it.contactlab.hub.sdk.java.exceptions.ContactHubException;
import it.contactlab.hub.sdk.java.exceptions.HttpException;
import it.contactlab.hub.sdk.java.gson.ContactHubGson;
import it.contactlab.hub.sdk.java.http.Request;
import it.contactlab.hub.sdk.java.models.Education;

import com.google.gson.Gson;
import org.json.JSONObject;

public class EducationApi {

  private static Gson gson = ContactHubGson.getInstance();

  /**
   * Add a new Education to a Customer.
   */
  public static Education add(Auth auth, String customerId, Education education)
      throws ContactHubException, HttpException {

    String endpoint = "/customers/" + customerId + "/educations";
    String payload = gson.toJson(education);
    JSONObject response = Request.doPost(auth, endpoint, payload);

    return gson.fromJson(response.toString(), Education.class);
  }

  /**
   * Update an existing Education.
   */
  public static Education update(Auth auth, String customerId, Education education)
      throws ContactHubException, HttpException {

    String endpoint = "/customers/" + customerId + "/educations/" + education.id();
    String payload = gson.toJson(education);
    JSONObject response = Request.doPut(auth, endpoint, payload);

    return gson.fromJson(response.toString(), Education.class);
  }

  /**
   * Remove a tag from a Customer.
   */
  public static boolean remove(Auth auth, String customerId, String educationId)
      throws ContactHubException, HttpException {

    String endpoint = "/customers/" + customerId + "/educations/" + educationId;
    JSONObject response = Request.doDelete(auth, endpoint);

    return true;
  }

}
