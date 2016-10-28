package com.contactlab.hub.sdk.async;

import com.contactlab.hub.Auth;
import com.contactlab.hub.api.CustomerApi;
import com.contactlab.hub.models.Customer;

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

}
