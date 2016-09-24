 /* Automatically generated from Swagger definitions */
 /* DO NOT EDIT MANUALLY */

package com.contactlab.hub.models;

import java.util.List;
import java.util.Map;

public class SourcePriorityConfiguration {

  public SourcePriorityConfiguration(List<String> priority) {
    this.priority = priority;
  }

  /**
   * list of source id ordered by descending priority
   */
  public final List<String> priority;

  public String toString() {
    return "SourcePriorityConfiguration(" + priority + ")";
  }

}
