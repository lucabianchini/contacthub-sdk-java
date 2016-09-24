 /* Automatically generated from Swagger definitions */
 /* DO NOT EDIT MANUALLY */

package com.contactlab.hub.models;

import java.util.List;
import java.util.Map;

public class PostSegment {

  public PostSegment(String name, Boolean enabled, Map<String, Object> query) {
    this.name = name;
    this.enabled = enabled;
    this.query = query;
  }

  /**
   * name of segment
   */
  public final String name;

  /**
   * enable/disable segment
   */
  public final Boolean enabled;

  /**
   * object query
   */
  public final Map<String, Object> query;

  public String toString() {
    return "PostSegment(" + name + enabled + query + ")";
  }

}
