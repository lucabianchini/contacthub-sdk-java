 /* Automatically generated from Swagger definitions */
 /* DO NOT EDIT MANUALLY */

package com.contactlab.hub.models;

import java.util.List;
import java.util.Map;

public class KpiModel {

  public KpiModel(String description, String kpi) {
    this.description = description;
    this.kpi = kpi;
  }

  /**
   * Description of kpi
   */
  public final String description;

  /**
   * Name of kpi
   */
  public final String kpi;

  public String toString() {
    return "KpiModel(" + description + kpi + ")";
  }

}
