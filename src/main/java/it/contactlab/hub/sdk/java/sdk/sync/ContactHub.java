package it.contactlab.hub.sdk.java.sync;

import it.contactlab.hub.sdk.java.Auth;
import it.contactlab.hub.sdk.java.CustomerApi;
import it.contactlab.hub.sdk.java.models.Customer;

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

  /**
   * Retrieves a Customer by id.
   */
  public Customer getCustomer(String id) {
    return CustomerApi.get(this.auth, id);
  }

  /**
   * Retrieves a Customer by external id.
   */
  public Customer getCustomerByExternalId(String externalId) {
    return CustomerApi.getByExternalId(this.auth, externalId);
  }

  /**
   * Adds a new Customer.
   */
  public Customer addCustomer(Customer customer) {
    return CustomerApi.add(this.auth, customer);
  }

  /**
   * Deletes a Customer.
   */
  public boolean deleteCustomer(String customerId) {
    return CustomerApi.delete(this.auth, customerId);
  }

}
