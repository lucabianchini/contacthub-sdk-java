package com.contactlab.hub.sdk.sync;

import com.contactlab.hub.Auth;
import com.contactlab.hub.api.CustomerApi;
import com.contactlab.hub.models.Customer;

import java.util.List;

/**
 * ContactHub Java SDK (Sync version).
 */
public class ContactHub {

  public Auth auth;

  public ContactHub(Auth auth) {
    this.auth = auth;
  }

  /**
   * Retrieve all the Customers of a Node.
   */
  public List<Customer> getCustomers() {
    return CustomerApi.get(this.auth);
  }

}
