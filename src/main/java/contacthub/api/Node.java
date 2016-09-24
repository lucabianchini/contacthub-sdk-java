package com.contactlab.contacthub;

import java.util.concurrent.CompletionStage;
import java.util.concurrent.CompletableFuture;
import java.util.List;

import com.contactlab.contacthub.models.Customer;

public class Node extends com.contactlab.contacthub.models.Node {

  public Node(String id, String workspaceId) {
    super(id, workspaceId);
  }

  public CompletionStage<List<Customer>> getCustomers() {
    return CompletableFuture.supplyAsync(() -> {
      return API.getCustomers(this.workspaceId, this.id);
    });
  }

}
