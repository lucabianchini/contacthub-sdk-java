 /* Automatically generated from Swagger definitions */
 /* DO NOT EDIT MANUALLY */

package com.contactlab.hub.models;

import java.util.List;
import java.util.Map;

public class BringBackProperty {

  public BringBackProperty(String value, String nodeId, String type) {
    this.value = value;
    this.nodeId = nodeId;
    this.type = type;
  }

  /**
   * value of bring back
   */
  public final String value;

  /**
   * id of entry node
   */
  public final String nodeId;

  /**
   * type of bring back
   */
  public final String type;

  public String toString() {
    return "BringBackProperty(" + value + nodeId + type + ")";
  }

}
