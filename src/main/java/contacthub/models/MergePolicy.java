 /* Automatically generated from Swagger definitions */
 /* DO NOT EDIT MANUALLY */

package com.contactlab.contacthub.models;

import java.util.List;
import java.util.Map;

public class MergePolicy {

  public MergePolicy(String description, String id, List<String> supportedTypes) {
    this.description = description;
    this.id = id;
    this.supportedTypes = supportedTypes;
  }

  /**
   * description of merge policy behavior
   */
  public final String description;

  /**
   * id of merge policy that you must use in `mergePolicy` property
   */
  public final String id;

  /**
   * Supported json schema types
   */
  public final List<String> supportedTypes;

  public String toString() {
    return "MergePolicy(" + description + id + supportedTypes + ")";
  }

}
