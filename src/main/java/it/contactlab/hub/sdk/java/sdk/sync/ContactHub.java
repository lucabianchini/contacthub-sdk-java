package it.contactlab.hub.sdk.java.sync;

import it.contactlab.hub.sdk.java.Auth;
import it.contactlab.hub.sdk.java.CustomerApi;
import it.contactlab.hub.sdk.java.models.Customer;
import it.contactlab.hub.sdk.java.exceptions.HttpException;

import java.util.List;

/**
 * ContactHub Java SDK (Sync version).
 */
public class ContactHub {

  private final Auth auth;

  public ContactHub(Auth auth) {
    this.auth = auth;
  }

  /**
   * Retrieve all the Customers of a Node.
   *
   * @return     A List of {@link Customer} objects.
   */
  public List<Customer> getCustomers() throws HttpException {
    return CustomerApi.get(this.auth);
  }

  /**
   * Retrieves a Customer by id.
   *
   * @param id A Customer id.
   * @return   A {@link Customer}.
   */
  public Customer getCustomer(String id) throws HttpException {
    return CustomerApi.get(this.auth, id);
  }

  /**
   * Retrieves a Customer by external id.
   *
   * @param externalId A Customer external id.
   * @return           A {@link Customer}.
   */
  public Customer getCustomerByExternalId(String externalId) throws HttpException {
    return CustomerApi.getByExternalId(this.auth, externalId);
  }

  /**
   * Adds a new Customer.
   *
   * @param customer The {@link Customer} to create.
   * @return         A newly created {@link Customer}.
   */
  public Customer addCustomer(Customer customer) throws HttpException {
    return CustomerApi.add(this.auth, customer);
  }

  /**
   * Deletes a Customer.
   *
   * @param id A Customer id.
   * @return   Whether the Customer was successfully deleted.
   */
  public boolean deleteCustomer(String id) throws HttpException {
    return CustomerApi.delete(this.auth, id);
  }

  /**
   * Updates an existing Customer.
   *
   * @param customer The {@link Customer} to update.
   * @return         An updated {@link Customer}.
   */
  public Customer updateCustomer(Customer customer) throws HttpException {
    return CustomerApi.update(this.auth, customer);
  }

}
