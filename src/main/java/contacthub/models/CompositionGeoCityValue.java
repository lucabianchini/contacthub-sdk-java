 /* Automatically generated from Swagger definitions */
 /* DO NOT EDIT MANUALLY */

package com.contactlab.contacthub.models;

import java.util.List;
import java.util.Map;

public class CompositionGeoCityValue {

  public CompositionGeoCityValue(String name, String value) {
    this.name = name;
    this.value = value;
  }

  /**
   * City name
   */
  public final String name;

  /**
   * Value for city
   */
  public final String value;

  public String toString() {
    return "CompositionGeoCityValue(" + name + value + ")";
  }

}
