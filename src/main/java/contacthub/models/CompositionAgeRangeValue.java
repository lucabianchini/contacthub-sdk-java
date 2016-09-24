 /* Automatically generated from Swagger definitions */
 /* DO NOT EDIT MANUALLY */

package com.contactlab.hub.models;

import java.util.List;
import java.util.Map;

public class CompositionAgeRangeValue {

  public CompositionAgeRangeValue(String name, String value) {
    this.name = name;
    this.value = value;
  }

  /**
   * Age range name
   */
  public final String name;

  /**
   * Value for Age range
   */
  public final String value;

  public String toString() {
    return "CompositionAgeRangeValue(" + name + value + ")";
  }

}
