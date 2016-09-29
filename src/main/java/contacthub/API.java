package com.contactlab.hub;

import com.contactlab.hub.models.Customer;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

/**
 * REST HTTP API interface.
 */
public class Api {

  private static String token = "97841617075b4b5f8ea88c30a8d2aec7647b7181df2c483fa78138c8d58aed4d";
  private static String baseUrl = "http://api.contactlab.it/hub/v1";
  private static Gson gson = new Gson();

  static {
    Unirest.setDefaultHeader("Authorization", "Bearer " + token);
  }

  /**
   * Retrieve all the Customers of a Node.
   */
  public static List<Customer> getCustomers(String workspaceId, String nodeId) {
    try {
      String endpoint = baseUrl + "/workspaces/" + workspaceId + "/customers" + "?nodeId=" + nodeId;
      String raw = Unirest
          .get(endpoint)
          .asJson()
          .getBody()
          .getObject().getJSONObject("_embedded")
          .getJSONArray("customers")
          .toString();

      Type collectionType = new TypeToken<List<Customer>>(){}.getType();
      return gson.fromJson(raw, collectionType);
    } catch (Exception exception) {
      exception.printStackTrace();
      return null;
    }
  }

}
