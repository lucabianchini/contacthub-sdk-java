package com.contactlab.hub;

import com.contactlab.hub.models.Customer;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

/**
 * A ContactHUB Node.
 */
public class Node extends com.contactlab.hub.models.Node {

  private final Api api;

  public Node(String id, String workspaceId) {
    super(id, workspaceId);
    this.api = new ContactHubApi();
  }

  public Node(String id, String workspaceId, Api api) {
    super(id, workspaceId);
    this.api = api;
  }

  /**
   * Retrieve all the Customers of a Node.
   */
  public CompletionStage<List<Customer>> getCustomers() {
    return CompletableFuture.supplyAsync(() -> {
      return api.getCustomers(this.workspaceId, this.id);
    });
  }

}
