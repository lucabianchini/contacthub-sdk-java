package it.contactlab.hub.sdk.java.async;

import it.contactlab.hub.sdk.java.Auth;
import it.contactlab.hub.sdk.java.CustomerApi;
import it.contactlab.hub.sdk.java.models.Customer;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

/**
 * ContactHub Java SDK (Async version).
 */

public class ContactHub {

  public Auth auth;

  public ContactHub(Auth auth) {
    this.auth = auth;
  }

  /**
   * Retrieve all the Customers of a Node.
   */
  public CompletionStage<List<Customer>> getCustomers() {
    return CompletableFuture.supplyAsync(() -> {
      return CustomerApi.get(this.auth);
    });
  }

  /**
   * Retrieves a Customer by id.
   */
  public CompletionStage<Customer> getCustomer(String id) {
    return CompletableFuture.supplyAsync(() -> {
      return CustomerApi.get(this.auth, id);
    });
  }

}
