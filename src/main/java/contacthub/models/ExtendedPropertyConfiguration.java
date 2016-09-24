 /* Automatically generated from Swagger definitions */
 /* DO NOT EDIT MANUALLY */

package com.contactlab.hub.models;

import java.util.List;
import java.util.Map;

public class ExtendedPropertyConfiguration {

  public ExtendedPropertyConfiguration(String schema) {
    this.schema = schema;
  }

  /**
   * the json schema of extended property (including for every elements the contactlabProperties object with property `label`, `enabled` and `mergePolicy` with value OBJ_PRIORITY, OBJ_CONCATENATE, ARRAY_UNION, ARRAY_INTERSECTION, MATH_SUM, MATH_AVG, MATH_DIFF, MATH_MIN, MATH_MAX).
   */
  public final String schema;

  public String toString() {
    return "ExtendedPropertyConfiguration(" + schema + ")";
  }

}
