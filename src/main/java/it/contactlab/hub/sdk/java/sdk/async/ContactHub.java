package it.contactlab.hub.sdk.java.async;

import it.contactlab.hub.sdk.java.Auth;
import it.contactlab.hub.sdk.java.CustomerApi;
import it.contactlab.hub.sdk.java.models.Customer;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.CompletionException;
import java.util.function.Supplier;

/**
 * ContactHub Java SDK (Async version).
 */

public class ContactHub {

  public Auth auth;

  public ContactHub(Auth auth) {
    this.auth = auth;
  }

  @FunctionalInterface
  private interface Thunk<T> {
    T apply() throws Exception;
  }

  private <T> CompletionStage<T> wrapAsync(Thunk<T> f) {
    return CompletableFuture.supplyAsync(() -> {
      try {
        return f.apply();
      } catch (Exception e) {
        throw new CompletionException(e);
      }
    });
  }

  /**
   * Retrieve all the Customers of a Node.
   *
   * @return   A {@link CompletionStage} of a List of {@link Customer} objects.
   */
  public CompletionStage<List<Customer>> getCustomers() {
    return wrapAsync(() -> CustomerApi.get(this.auth));
  }

  /**
   * Retrieves a Customer by id.
   *
   * @param id A Customer id.
   * @return   A CompletionStage of {@link Customer}.
   */
  public CompletionStage<Customer> getCustomer(String id) {
    return wrapAsync(() -> CustomerApi.get(this.auth, id));
  }

}
