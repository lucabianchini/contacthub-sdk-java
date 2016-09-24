 /* Automatically generated from Swagger definitions */
 /* DO NOT EDIT MANUALLY */

package com.contactlab.hub.models;

import java.util.List;
import java.util.Map;

public class NodePermission {

  public NodePermission(String id, String permission) {
    this.id = id;
    this.permission = permission;
  }

  /**
   * unique identifier of node
   */
  public final String id;

  /**
   * permission mode
   */
  public final String permission;

  public String toString() {
    return "NodePermission(" + id + permission + ")";
  }

}
