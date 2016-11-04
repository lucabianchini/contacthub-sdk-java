package it.contactlab.hub.sdk.java;

import it.contactlab.hub.sdk.java.models.Customer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.reflect.TypeToken;
import com.mashape.unirest.http.Unirest;

import java.lang.reflect.Type;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CustomerApi {

  private static DateTimeFormatter contactlabDateFormatter =
      DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");

  private static JsonDeserializer<OffsetDateTime> dateTimeJsonDeserializer =
      (json, typeOfT, context) ->
      json == null ? null : OffsetDateTime.parse(json.getAsString(), contactlabDateFormatter);
  private static Gson gson =
      new GsonBuilder().registerTypeAdapter(
          OffsetDateTime.class,dateTimeJsonDeserializer
          ).create();

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

  /**
   * Retrieves a Customer by id.
   *
   * @param auth A ContactHub Auth object.
   * @param id   The Customer id.
   * @return     A Customer object.
   */
  public static Customer get(Auth auth, String id) {
    try {
      Unirest.setDefaultHeader("Authorization", "Bearer " + auth.token);
      String endpoint = baseUrl + "/workspaces/" + auth.workspaceId
           + "/customers/" + id;
      String raw = Unirest
          .get(endpoint)
          .asJson()
          .getBody()
          .getObject()
          .toString();

      return gson.fromJson(raw, Customer.class);
    } catch (Exception exception) {
      exception.printStackTrace();

      return null;
    }
  }

}
