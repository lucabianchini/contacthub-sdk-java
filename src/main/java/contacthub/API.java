package com.contactlab.contacthub;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;

import com.contactlab.contacthub.models.Customer;

public class API {

  private static String token = "97841617075b4b5f8ea88c30a8d2aec7647b7181df2c483fa78138c8d58aed4d";
  private static String baseUrl = "http://api.contactlab.it/hub/v1";
  private static Gson gson = new Gson();

  static {
    Unirest.setDefaultHeader("Authorization", "Bearer " + token);
  }

  public static List<Customer> getCustomers(String workspaceId, String nodeId) {
    try {
      String endpoint = baseUrl + "/workspaces/" + workspaceId + "/customers"+ "?nodeId=" + nodeId;
      String raw = Unirest
        .get(endpoint)
        .asJson()
        .getBody()
        .getObject().getJSONObject("_embedded")
        .getJSONArray("customers")
        .toString();

      Type collectionType = new TypeToken<List<Customer>>(){}.getType();
      return gson.fromJson(raw, collectionType);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

}
