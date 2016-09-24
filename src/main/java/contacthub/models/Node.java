package com.contactlab.contacthub.models;

import java.util.List;

public class Node {

  public final String id;
  public final String workspaceId;

  public Node(String id, String workspaceId) {
    this.id = id;
    this.workspaceId = workspaceId;
  }

  public String toString() {
    return "Node(" + id + ")";
  }

}
