 /* Automatically generated from Swagger definitions */
 /* DO NOT EDIT MANUALLY */

package com.contactlab.hub.models;

import java.util.List;
import java.util.Map;

public class KpiValue {

  public KpiValue(String name, String value) {
    this.name = name;
    this.value = value;
  }

  /**
   * Name of kpi
   */
  public final String name;

  /**
   * Value of kpi
   */
  public final String value;

  public String toString() {
    return "KpiValue(" + name + value + ")";
  }

}
