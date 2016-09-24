 /* Automatically generated from Swagger definitions */
 /* DO NOT EDIT MANUALLY */

package com.contactlab.hub.models;

import java.util.List;
import java.util.Map;

public class CompositionGeoCountryValue {

  public CompositionGeoCountryValue(String name, String value) {
    this.name = name;
    this.value = value;
  }

  /**
   * Country name
   */
  public final String name;

  /**
   * Value for country
   */
  public final String value;

  public String toString() {
    return "CompositionGeoCountryValue(" + name + value + ")";
  }

}
