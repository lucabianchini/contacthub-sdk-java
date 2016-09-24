 /* Automatically generated from Swagger definitions */
 /* DO NOT EDIT MANUALLY */

package com.contactlab.contacthub.models;

import java.util.List;
import java.util.Map;

public class UniqueCustomerMatchingPolicyConfiguration {

  public UniqueCustomerMatchingPolicyConfiguration(List<String> properties) {
    this.properties = properties;
  }

  /**
   * Rules of aggregations
   */
  public final List<String> properties;

  public String toString() {
    return "UniqueCustomerMatchingPolicyConfiguration(" + properties + ")";
  }

}
