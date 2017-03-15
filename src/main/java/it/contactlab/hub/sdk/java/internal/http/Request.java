package it.contactlab.hub.sdk.java.http;

import it.contactlab.hub.sdk.java.Auth;
import it.contactlab.hub.sdk.java.exceptions.ApiException;
import it.contactlab.hub.sdk.java.exceptions.HttpException;
import it.contactlab.hub.sdk.java.exceptions.ServerException;
import it.contactlab.hub.sdk.java.models.ApiErrorResponse;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Request {

  /**
   * Sends a generic GET request and returns the response String.
   */
  public static String doGet(
      Auth auth, String endpoint, Map<String, Object> queryString
  ) throws ApiException, ServerException, HttpException {
    String url = auth.apiUrl + "/workspaces/" + auth.workspaceId + endpoint;

    try {
      HttpResponse<String> response = Unirest
          .get(url)
          .header("Authorization", "Bearer " + auth.token)
          .queryString(queryString)
          .asString();

      if (response.getStatus() >= 400) {
        ApiErrorResponse error = ApiException.parseApiErrorResponse(
            response.getStatus(), response.getBody());
        throw new ApiException(response.getStatus(), error);
      }

      return response.getBody().toString();
    } catch (UnirestException ex) {
      throw new HttpException(ex);
    }
  }

  public static String doGet(Auth auth, String endpoint)
      throws ApiException, ServerException, HttpException {
    return doGet(auth, endpoint, Collections.emptyMap());
  }

  /**
   * Sends a generic POST request and returns the response String.
   */
  public static String doPost(Auth auth, String endpoint, String payload)
      throws ApiException, ServerException, HttpException {
    try {
      String url = auth.apiUrl + "/workspaces/" + auth.workspaceId + endpoint;

      HttpResponse<String> response = Unirest
          .post(url)
          .header("Authorization", "Bearer " + auth.token)
          .header("Content-Type", "application/json")
          .body(payload)
          .asString();

      if (response.getStatus() >= 400) {
        ApiErrorResponse error = ApiException.parseApiErrorResponse(
            response.getStatus(), response.getBody());
        throw new ApiException(response.getStatus(), error);
      }

      return response.getBody().toString();
    } catch (UnirestException ex) {
      throw new HttpException(ex);
    }
  }

  /**
   * Sends a generic DELETE request and returns the response String.
   */
  public static String doDelete(Auth auth, String endpoint)
      throws ApiException, ServerException, HttpException {
    try {
      String url = auth.apiUrl + "/workspaces/" + auth.workspaceId + endpoint;

      HttpResponse<String> response = Unirest
          .delete(url)
          .header("Authorization", "Bearer " + auth.token)
          .asString();

      if (response.getStatus() >= 400) {
        ApiErrorResponse error = ApiException.parseApiErrorResponse(
            response.getStatus(), response.getBody());
        throw new ApiException(response.getStatus(), error);
      }

      return response.getBody().toString();
    } catch (UnirestException ex) {
      throw new HttpException(ex);
    }
  }

  /**
   * Sends a generic PUT request and returns the response String.
   */
  public static String doPut(Auth auth, String endpoint, String payload)
      throws ApiException, ServerException, HttpException {
    try {
      String url = auth.apiUrl + "/workspaces/" + auth.workspaceId + endpoint;

      HttpResponse<String> response = Unirest
          .put(url)
          .header("Authorization", "Bearer " + auth.token)
          .header("Content-Type", "application/json")
          .body(payload)
          .asString();

      if (response.getStatus() >= 400) {
        ApiErrorResponse error = ApiException.parseApiErrorResponse(
            response.getStatus(), response.getBody());
        throw new ApiException(response.getStatus(), error);
      }

      return response.getBody().toString();
    } catch (UnirestException ex) {
      throw new HttpException(ex);
    }
  }

  /**
   * Sends a generic PATCH request and returns the response String.
   */
  public static String doPatch(Auth auth, String endpoint, String payload)
      throws ApiException, ServerException, HttpException {
    try {
      String url = auth.apiUrl + "/workspaces/" + auth.workspaceId + endpoint;

      HttpResponse<String> response = Unirest
          .patch(url)
          .header("Authorization", "Bearer " + auth.token)
          .header("Content-Type", "application/json")
          .body(payload)
          .asString();

      if (response.getStatus() >= 400) {
        ApiErrorResponse error = ApiException.parseApiErrorResponse(
            response.getStatus(), response.getBody());
        throw new ApiException(response.getStatus(), error);
      }

      return response.getBody().toString();
    } catch (UnirestException ex) {
      throw new HttpException(ex);
    }
  }

}
