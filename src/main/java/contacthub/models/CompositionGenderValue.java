 /* Automatically generated from Swagger definitions */
 /* DO NOT EDIT MANUALLY */

package com.contactlab.contacthub.models;

import java.util.List;
import java.util.Map;

public class CompositionGenderValue {

  public CompositionGenderValue(String name, String value) {
    this.name = name;
    this.value = value;
  }

  /**
   * gender name
   */
  public final String name;

  /**
   * Value for gender
   */
  public final String value;

  public String toString() {
    return "CompositionGenderValue(" + name + value + ")";
  }

}
