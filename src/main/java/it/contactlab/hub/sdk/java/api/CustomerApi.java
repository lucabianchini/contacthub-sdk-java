package it.contactlab.hub.sdk.java;

import it.contactlab.hub.sdk.java.models.Customer;
import it.contactlab.hub.sdk.java.models.PostCustomer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.reflect.TypeToken;
import com.mashape.unirest.http.Unirest;
import org.json.JSONObject;

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

  private static String baseUrl = "https://api.contactlab.it/hub/v1";

  /**
   * Sends a generic GET request and returns the response JsonObject.
   */
  private static JSONObject doGet(Auth auth, String endpoint) {
    try {
      Unirest.setDefaultHeader("Authorization", "Bearer " + auth.token);
      String url = baseUrl + "/workspaces/" + auth.workspaceId + "/customers" + endpoint;

      JSONObject response = Unirest
          .get(url)
          .asJson()
          .getBody()
          .getObject();

      return response;
    } catch (Exception exception) {
      exception.printStackTrace();

      return null;
    }
  }

  /**
   * Sends a generic POST request and returns the response JsonObject.
   */
  private static JSONObject doPost(Auth auth, String endpoint, String payload) {
    try {
      Unirest.setDefaultHeader("Authorization", "Bearer " + auth.token);
      String url = baseUrl + "/workspaces/" + auth.workspaceId + "/customers" + endpoint;

      PostCustomer postCustomer = gson.fromJson(payload, PostCustomer.class);
      postCustomer.setNodeId(auth.nodeId);

      JSONObject response = Unirest
          .post(url)
          .header("Content-Type", "application/json")
          .body(gson.toJson(postCustomer))
          .asJson()
          .getBody()
          .getObject();

      return response;
    } catch (Exception exception) {
      exception.printStackTrace();

      return null;
    }
  }

  /**
   * Sends a generic DELETE request and returns true if successful.
   */
  private static boolean doDelete(Auth auth, String endpoint) {
    try {
      Unirest.setDefaultHeader("Authorization", "Bearer " + auth.token);
      String url = baseUrl + "/workspaces/" + auth.workspaceId + "/customers" + endpoint;

      int status = Unirest
          .delete(url)
          .asString()
          .getStatus();

      return (status == 200) ? true : false;
    } catch (Exception exception) {
      exception.printStackTrace();

      return false;
    }
  }

  /**
   * Retrieves all the Customers for a Node.
   *
   * @param auth A ContactHub Auth object.
   * @return     A List of Customer objects.
   */
  public static List<Customer> get(Auth auth) {
    try {
      String endpoint = "/?nodeId=" + auth.nodeId;
      JSONObject response = doGet(auth, endpoint);

      Type collectionType = new TypeToken<List<Customer>>(){}.getType();

      List<Customer> customers = gson.fromJson(response
          .getJSONObject("_embedded")
          .getJSONArray("customers")
          .toString(),
          collectionType);

      return customers;
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
      String endpoint = "/" + id;
      JSONObject response = doGet(auth, endpoint);

      return gson.fromJson(response.toString(), Customer.class);
    } catch (Exception exception) {
      exception.printStackTrace();

      return null;
    }
  }

  /**
   * Retrieves a Customer by externalId.
   *
   * @param auth       A ContactHub Auth object.
   * @param externalId The Customer externalId.
   * @return           A Customer object.
   */
  public static Customer getByExternalId(Auth auth, String externalId) {
    try {
      String endpoint = "/?nodeId=" + auth.nodeId + "&externalId=" + externalId;
      JSONObject response = doGet(auth, endpoint);

      Customer customer = gson.fromJson(response
          .getJSONObject("_embedded")
          .getJSONArray("customers")
          .getJSONObject(0)
          .toString(),
          Customer.class);

      return customer;
    } catch (Exception exception) {
      exception.printStackTrace();

      return null;
    }
  }

  /**
   * Adds a new Customer.
   *
   * @param auth     A ContactHub Auth object.
   * @param customer The Customer object.
   * @return         The stored Customer object, including its id.
   */
  public static Customer add(Auth auth, Customer customer) {
    try {
      String endpoint = "";
      String payload = gson.toJson(customer);
      JSONObject response = doPost(auth, endpoint, payload);

      return gson.fromJson(response.toString(), Customer.class);
    } catch (Exception exception) {
      exception.printStackTrace();

      return null;
    }
  }

  /**
   * Deletes a Customer.
   *
   * @param auth       A ContactHub Auth object.
   * @param customerId The id of the Customer to delete.
   * @return           True if the deletion was successful
   */
  public static boolean delete(Auth auth, String customerId) {
    try {
      String endpoint = "/" + customerId;
      boolean success = doDelete(auth, endpoint);

      return success;
    } catch (Exception exception) {
      exception.printStackTrace();

      return false;
    }
  }
}
