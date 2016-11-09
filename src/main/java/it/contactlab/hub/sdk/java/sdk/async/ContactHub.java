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
   *
   * @return   A {@link CompletionStage} of a List of {@link Customer} objects.
   */
  public CompletionStage<List<Customer>> getCustomers() {
    return CompletableFuture.supplyAsync(() -> {
      return CustomerApi.get(this.auth);
    });
  }

  /**
   * Retrieves a Customer by id.
   *
   * @param id A Customer id.
   * @return   A CompletionStage of {@link Customer}.
   */
  public CompletionStage<Customer> getCustomer(String id) {
    return CompletableFuture.supplyAsync(() -> {
      return CustomerApi.get(this.auth, id);
    });
  }

}
