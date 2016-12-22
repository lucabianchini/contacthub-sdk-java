package it.contactlab.hub.sdk.java;

import it.contactlab.hub.sdk.java.exceptions.HttpException;
import it.contactlab.hub.sdk.java.gson.ContactHubGson;
import it.contactlab.hub.sdk.java.models.Customer;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

public class CustomerApi {

  private static Gson gson = ContactHubGson.getInstance();

  private static String baseUrl = "https://api.contactlab.it/hub/v1";

  /**
   * Sends a generic GET request and returns the response JsonObject.
   */
  private static JSONObject doGet(Auth auth, String endpoint) throws HttpException {
    try {
      Unirest.setDefaultHeader("Authorization", "Bearer " + auth.token);
      String url = baseUrl + "/workspaces/" + auth.workspaceId + "/customers" + endpoint;

      HttpResponse<JsonNode> response = Unirest.get(url).asJson();

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
   * Sends a generic POST request and returns the response JsonObject.
   */
  private static JSONObject doPost(Auth auth, String endpoint, String payload)
      throws HttpException {
    try {
      Unirest.setDefaultHeader("Authorization", "Bearer " + auth.token);
      String url = baseUrl + "/workspaces/" + auth.workspaceId + "/customers" + endpoint;

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
  private static boolean doDelete(Auth auth, String endpoint) throws HttpException {
    try {
      Unirest.setDefaultHeader("Authorization", "Bearer " + auth.token);
      String url = baseUrl + "/workspaces/" + auth.workspaceId + "/customers" + endpoint;

      HttpResponse<JsonNode> response = Unirest.delete(url).asJson();

      if (response.getStatus() >= 400) {
        throw new HttpException(response.getBody().toString());
      }

      return true;
    } catch (HttpException httpException) {
      throw httpException;
    } catch (Exception exception) {
      exception.printStackTrace();

      return false;
    }
  }

  /**
   * Sends a generic PUT request and returns the response JsonObject.
   */
  private static JSONObject doPut(Auth auth, String endpoint, String payload) throws HttpException {
    try {
      Unirest.setDefaultHeader("Authorization", "Bearer " + auth.token);
      String url = baseUrl + "/workspaces/" + auth.workspaceId + "/customers" + endpoint;

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
  private static JSONObject doPatch(Auth auth, String endpoint, Customer patchCustomer)
      throws HttpException {
    try {
      Unirest.setDefaultHeader("Authorization", "Bearer " + auth.token);
      String url = baseUrl + "/workspaces/" + auth.workspaceId + "/customers" + endpoint;

      HttpResponse<JsonNode> response = Unirest
          .patch(url)
          .header("Content-Type", "application/json")
          .body(gson.toJson(patchCustomer))
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
   * Retrieves all the Customers for a Node.
   *
   * @param auth A ContactHub Auth object.
   * @return     A List of Customer objects.
   */
  public static List<Customer> get(Auth auth) throws HttpException {
    String endpoint = "/?nodeId=" + auth.nodeId;
    JSONObject response = doGet(auth, endpoint);

    Type collectionType = new TypeToken<List<Customer>>(){}.getType();

    List<Customer> customers = gson.fromJson(response
        .getJSONArray("elements")
        .toString(),
        collectionType);

    return customers;
  }

  /**
   * Retrieves a Customer by id.
   *
   * @param auth A ContactHub Auth object.
   * @param id   The Customer id.
   * @return     A Customer object.
   */
  public static Customer get(Auth auth, String id) throws HttpException {
    String endpoint = "/" + id;
    JSONObject response = doGet(auth, endpoint);

    return gson.fromJson(response.toString(), Customer.class);
  }

  /**
   * Retrieves a Customer by externalId.
   *
   * @param auth       A ContactHub Auth object.
   * @param externalId The Customer externalId.
   * @return           A list of matching Customer objects.
   */
  public static List<Customer> getByExternalId(Auth auth, String externalId) throws HttpException {
    String endpoint = "/?nodeId=" + auth.nodeId + "&externalId=" + externalId;
    JSONObject response = doGet(auth, endpoint);

    Type collectionType = new TypeToken<List<Customer>>(){}.getType();

    List<Customer> customers = gson.fromJson(response
        .getJSONArray("elements")
        .toString(),
        collectionType);

    return customers;
  }

  /**
   * Adds a new Customer.
   *
   * @param auth     A ContactHub Auth object.
   * @param customer The Customer object.
   * @return         The stored Customer object, including its id.
   */
  public static Customer add(Auth auth, Customer customer) throws HttpException {
    String endpoint = "";
    Customer expectedCustomer = customer.withNodeId(auth.nodeId);
    String payload = gson.toJson(expectedCustomer);
    JSONObject response = doPost(auth, endpoint, payload);

    return gson.fromJson(response.toString(), Customer.class);
  }

  /**
   * Deletes a Customer.
   *
   * @param auth       A ContactHub Auth object.
   * @param customerId The id of the Customer to delete.
   * @return           True if the deletion was successful
   */
  public static boolean delete(Auth auth, String customerId) throws HttpException {
    String endpoint = "/" + customerId;
    boolean success = doDelete(auth, endpoint);

    return success;
  }

  /**
   * Updates a Customer.
   *
   * @param auth     A ContactHub Auth object.
   * @param customer The Customer object.
   * @return         The updated Customer object
   */
  public static Customer update(Auth auth, Customer customer) throws HttpException {
    String endpoint = "/" + customer.id().get();
    Customer expectedCustomer = customer.withNodeId(auth.nodeId);
    String payload = gson.toJson(expectedCustomer);
    JSONObject response = doPut(auth, endpoint, payload);

    return gson.fromJson(response.toString(), Customer.class);
  }

  /**
   * Patches a Customer.
   *
   * @param auth          A ContactHub Auth object.
   * @param customerId    The id of the Customer to patch.
   * @param patchCustomer The CustomerPatch object.
   * @return              The updated Customer object
   */
  public static Customer patch(Auth auth, String customerId, Customer patchCustomer)
      throws HttpException {
    String endpoint = "/" + customerId;
    JSONObject response = doPatch(auth, endpoint, patchCustomer);

    return gson.fromJson(response.toString(), Customer.class);
  }

}
