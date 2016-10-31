package com.contactlab.hub.api;

import com.contactlab.hub.Auth;
import com.contactlab.hub.models.Customer;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mashape.unirest.http.Unirest;

import java.lang.reflect.Type;
import java.util.List;

public class CustomerApi {

  private static Gson gson = new Gson();

  private static String baseUrl = "http://api.contactlab.it/hub/v1";

  /**
   * Retrieves all the Customers for a Node.
   *
   * @param auth A ContactHub Auth object.
   * @return     A List of Customer objects.
   */
  public static List<Customer> get(Auth auth) {
    try {
      Unirest.setDefaultHeader("Authorization", "Bearer " + auth.token);
      String endpoint = baseUrl + "/workspaces/" + auth.workspaceId
           + "/customers" + "?nodeId=" + auth.nodeId;
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
