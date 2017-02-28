package it.contactlab.hub.sdk.java.http;

import it.contactlab.hub.sdk.java.Auth;
import it.contactlab.hub.sdk.java.exceptions.HttpException;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Request {

  private static String baseUrl = "https://api.contactlab.it/hub/v1";

  /**
   * Sends a generic GET request and returns the response JsonObject.
   */
  public static JSONObject doGet(
      Auth auth, String endpoint, Map<String, Object> queryString
  ) throws HttpException {
    try {
      Unirest.setDefaultHeader("Authorization", "Bearer " + auth.token);
      String url = baseUrl + "/workspaces/" + auth.workspaceId + endpoint;

      HttpResponse<JsonNode> response = Unirest.get(url)
                                               .queryString(queryString)
                                               .asJson();

      if (response.getStatus() >= 400) {
        throw new HttpException(response.getBody().toString());
      }

      return response.getBody().getObject();
    } catch (HttpException httpException) {
      throw httpException;
    } catch (Exception exception) {
      exception.printStackTrace();

      return null;
    }
  }

  public static JSONObject doGet(Auth auth, String endpoint)
      throws HttpException {
    return doGet(auth, endpoint, new HashMap<String, Object>());
  }

  /**
   * Sends a generic POST request and returns the response JsonObject.
   */
  public static JSONObject doPost(Auth auth, String endpoint, String payload)
      throws HttpException {
    try {
      Unirest.setDefaultHeader("Authorization", "Bearer " + auth.token);
      String url = baseUrl + "/workspaces/" + auth.workspaceId + endpoint;

      HttpResponse<JsonNode> response = Unirest
          .post(url)
          .header("Content-Type", "application/json")
          .body(payload)
          .asJson();

      if (response.getStatus() >= 400) {
        throw new HttpException(response.getBody().toString());
      }

      return response.getBody().getObject();
    } catch (HttpException httpException) {
      throw httpException;
    } catch (Exception exception) {
      exception.printStackTrace();

      return null;
    }
  }

  /**
   * Sends a generic DELETE request and returns true if successful.
   */
  public static JSONObject doDelete(Auth auth, String endpoint) throws HttpException {
    try {
      Unirest.setDefaultHeader("Authorization", "Bearer " + auth.token);
      String url = baseUrl + "/workspaces/" + auth.workspaceId + endpoint;

      HttpResponse<JsonNode> response = Unirest.delete(url).asJson();

      if (response.getStatus() >= 400) {
        throw new HttpException(response.getBody().toString());
      }

      return response.getBody().getObject();
    } catch (HttpException httpException) {
      throw httpException;
    } catch (Exception exception) {
      exception.printStackTrace();

      return null;
    }
  }

  /**
   * Sends a generic PUT request and returns the response JsonObject.
   */
  public static JSONObject doPut(Auth auth, String endpoint, String payload) throws HttpException {
    try {
      Unirest.setDefaultHeader("Authorization", "Bearer " + auth.token);
      String url = baseUrl + "/workspaces/" + auth.workspaceId + endpoint;

      HttpResponse<JsonNode> response = Unirest
          .put(url)
          .header("Content-Type", "application/json")
          .body(payload)
          .asJson();

      if (response.getStatus() >= 400) {
        throw new HttpException(response.getBody().toString());
      }

      return response.getBody().getObject();
    } catch (HttpException httpException) {
      throw httpException;
    } catch (Exception exception) {
      exception.printStackTrace();

      return null;
    }
  }

  /**
   * Sends a generic PATCH request and returns the response JsonObject.
   */
  public static JSONObject doPatch(Auth auth, String endpoint, String payload)
      throws HttpException {
    try {
      Unirest.setDefaultHeader("Authorization", "Bearer " + auth.token);
      String url = baseUrl + "/workspaces/" + auth.workspaceId + endpoint;

      HttpResponse<JsonNode> response = Unirest
          .patch(url)
          .header("Content-Type", "application/json")
          .body(payload)
          .asJson();

      if (response.getStatus() >= 400) {
        throw new HttpException(response.getBody().toString());
      }

      return response.getBody().getObject();
    } catch (HttpException httpException) {
      throw httpException;
    } catch (Exception exception) {
      exception.printStackTrace();

      return null;
    }
  }

}
