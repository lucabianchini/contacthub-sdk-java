 /* Automatically generated from Swagger definitions */
 /* DO NOT EDIT MANUALLY */

package com.contactlab.hub.models;

import java.util.List;
import java.util.Map;

public class PutUser {

  public PutUser(List<NodePermission> nodes, String id) {
    this.nodes = nodes;
    this.id = id;
  }

  /**
   * TODO
   */
  public final List<NodePermission> nodes;

  /**
   * unique identifier of event
   */
  public final String id;

  public String toString() {
    return "PutUser(" + nodes + id + ")";
  }

}
