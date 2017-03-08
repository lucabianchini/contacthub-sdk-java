package it.contactlab.hub.sdk.java;

import it.contactlab.hub.sdk.java.Auth;
import it.contactlab.hub.sdk.java.internal.api.CustomerApi;
import it.contactlab.hub.sdk.java.models.Customer;
import it.contactlab.hub.sdk.java.models.Paginated;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.CompletionStage;

/**
 * ContactHub Java SDK (Async version).
 */

public class AsyncContactHub {

  public Auth auth;

  public AsyncContactHub(Auth auth) {
    this.auth = auth;
  }

  @FunctionalInterface
  private interface Thunk<T> {
    T apply() throws Exception;
  }

  private <T> CompletionStage<T> wrapAsync(Thunk<T> fun) {
    return CompletableFuture.supplyAsync(() -> {
      try {
        return fun.apply();
      } catch (Exception ex) {
        throw new CompletionException(ex);
      }
    });
  }

  /**
   * Retrieve all the Customers of a Node.
   *
   * @return   A {@link Paginated} List of {@link Customer} objects.
   */
  public CompletionStage<Paginated<Customer>> getCustomers() {
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

  /**
   * Adds a new Customer.
   *
   * @param customer The {@link Customer} to create.
   * @return         A CompletionStage of the newly created {@link Customer}.
   */
  public CompletionStage<Customer> addCustomer(Customer customer) {
    return wrapAsync(() -> CustomerApi.add(this.auth, customer));
  }


}
