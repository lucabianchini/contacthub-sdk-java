package com.contactlab.hub;

import com.contactlab.hub.models.Customer;

import java.util.List;

public interface Api {

  /**
   * Retrieve all the Customers of a Node.
   */
  public List<Customer> getCustomers(String workspaceId, String nodeId);

}
