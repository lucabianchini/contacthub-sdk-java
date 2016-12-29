package it.contactlab.hub.sdk.java.sync;

import it.contactlab.hub.sdk.java.Auth;
import it.contactlab.hub.sdk.java.api.CustomerApi;
import it.contactlab.hub.sdk.java.api.EventApi;
import it.contactlab.hub.sdk.java.exceptions.HttpException;
import it.contactlab.hub.sdk.java.models.Customer;
import it.contactlab.hub.sdk.java.models.Event;

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
  public List<Customer> getCustomerByExternalId(String externalId) throws HttpException {
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


  /**
   * Patches an existing Customer.
   *
   * @param customerId    The id of the Customer to update.
   * @param patchCustomer The {@link Customer} object, containing all the values to patch.
   * @return              An updated {@link Customer}.
   */
  public Customer patchCustomer(String customerId, Customer patchCustomer)
      throws HttpException {
    return CustomerApi.patch(this.auth, customerId, patchCustomer);
  }

  /**
   * Adds a new Event.
   *
   * @param newEvent The {@link Event} to create.
   * @return  Whether the Event was successfully queued for insertion.
   */
  public boolean addEvent(Event newEvent) throws HttpException {
    return EventApi.add(this.auth, newEvent);
  }

}
