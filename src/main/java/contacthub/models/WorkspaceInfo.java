 /* Automatically generated from Swagger definitions */
 /* DO NOT EDIT MANUALLY */

package com.contactlab.contacthub.models;

import java.util.List;
import java.util.Map;

public class WorkspaceInfo {

  public WorkspaceInfo(String name, List<NodePermission> nodes, String id) {
    this.name = name;
    this.nodes = nodes;
    this.id = id;
  }

  /**
   * name of workspace
   */
  public final String name;

  /**
   * TODO
   */
  public final List<NodePermission> nodes;

  /**
   * unique identifier of workspace
   */
  public final String id;

  public String toString() {
    return "WorkspaceInfo(" + name + nodes + id + ")";
  }

}
