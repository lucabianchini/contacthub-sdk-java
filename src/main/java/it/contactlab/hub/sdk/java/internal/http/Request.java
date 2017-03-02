package it.contactlab.hub.sdk.java.http;

import it.contactlab.hub.sdk.java.Auth;
import it.contactlab.hub.sdk.java.exceptions.ContactHubException;
import it.contactlab.hub.sdk.java.exceptions.HttpException;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Request {

  /**
   * Sends a generic GET request and returns the response JsonObject.
   */
  public static JSONObject doGet(
      Auth auth, String endpoint, Map<String, Object> queryString
  ) throws ContactHubException, HttpException {
    Unirest.setDefaultHeader("Authorization", "Bearer " + auth.token);
    String url = auth.apiUrl + "/workspaces/" + auth.workspaceId + endpoint;

    try {
      HttpResponse<JsonNode> response = Unirest.get(url)
          .queryString(queryString)
          .asJson();

      if (response.getStatus() >= 400) {
        throw new ContactHubException(response.getStatus(),
                                      response.getBody().toString());
      }

      return response.getBody().getObject();
    } catch (UnirestException ex) {
      throw new HttpException(ex);
    }
  }

  public static JSONObject doGet(Auth auth, String endpoint)
      throws ContactHubException, HttpException {
    return doGet(auth, endpoint, Collections.emptyMap());
  }

  /**
   * Sends a generic POST request and returns the response JsonObject.
   */
  public static JSONObject doPost(Auth auth, String endpoint, String payload)
      throws ContactHubException, HttpException {
    try {
      Unirest.setDefaultHeader("Authorization", "Bearer " + auth.token);
      String url = auth.apiUrl + "/workspaces/" + auth.workspaceId + endpoint;

      HttpResponse<JsonNode> response = Unirest
          .post(url)
          .header("Content-Type", "application/json")
          .body(payload)
          .asJson();

      if (response.getStatus() >= 400) {
        throw new ContactHubException(response.getStatus(),
                                      response.getBody().toString());
      }

      return response.getBody().getObject();
    } catch (UnirestException ex) {
      throw new HttpException(ex);
    }
  }

  /**
   * Sends a generic DELETE request and returns true if successful.
   */
  public static JSONObject doDelete(Auth auth, String endpoint)
      throws ContactHubException, HttpException {
    try {
      Unirest.setDefaultHeader("Authorization", "Bearer " + auth.token);
      String url = auth.apiUrl + "/workspaces/" + auth.workspaceId + endpoint;

      HttpResponse<JsonNode> response = Unirest.delete(url).asJson();

      if (response.getStatus() >= 400) {
        throw new ContactHubException(response.getStatus(),
                                      response.getBody().toString());
      }

      return response.getBody().getObject();
    } catch (UnirestException ex) {
      throw new HttpException(ex);
    }
  }

  /**
   * Sends a generic PUT request and returns the response JsonObject.
   */
  public static JSONObject doPut(Auth auth, String endpoint, String payload)
      throws ContactHubException, HttpException {
    try {
      Unirest.setDefaultHeader("Authorization", "Bearer " + auth.token);
      String url = auth.apiUrl + "/workspaces/" + auth.workspaceId + endpoint;

      HttpResponse<JsonNode> response = Unirest
          .put(url)
          .header("Content-Type", "application/json")
          .body(payload)
          .asJson();

      if (response.getStatus() >= 400) {
        throw new ContactHubException(response.getStatus(),
                                      response.getBody().toString());
      }

      return response.getBody().getObject();
    } catch (UnirestException ex) {
      throw new HttpException(ex);
    }
  }

  /**
   * Sends a generic PATCH request and returns the response JsonObject.
   */
  public static JSONObject doPatch(Auth auth, String endpoint, String payload)
      throws ContactHubException, HttpException {
    try {
      Unirest.setDefaultHeader("Authorization", "Bearer " + auth.token);
      String url = auth.apiUrl + "/workspaces/" + auth.workspaceId + endpoint;

      HttpResponse<JsonNode> response = Unirest
          .patch(url)
          .header("Content-Type", "application/json")
          .body(payload)
          .asJson();

      if (response.getStatus() >= 400) {
        throw new ContactHubException(response.getStatus(),
                                      response.getBody().toString());
      }

      return response.getBody().getObject();
    } catch (UnirestException ex) {
      throw new HttpException(ex);
    }
  }

}
