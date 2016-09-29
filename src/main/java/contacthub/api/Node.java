package com.contactlab.hub;

import com.contactlab.hub.models.Customer;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

/**
 * A ContactHUB Node.
 */
public class Node extends com.contactlab.hub.models.Node {

  public Node(String id, String workspaceId) {
    super(id, workspaceId);
  }

  /**
   * Retrieve all the Customers of a Node.
   */
  public CompletionStage<List<Customer>> getCustomers() {
    return CompletableFuture.supplyAsync(() -> {
      return API.getCustomers(this.workspaceId, this.id);
    });
  }

}
