 /* Automatically generated from Swagger definitions */
 /* DO NOT EDIT MANUALLY */

package com.contactlab.contacthub.models;

import java.util.List;
import java.util.Map;

public class BasePropertyModel {

  public BasePropertyModel(String schema) {
    this.schema = schema;
  }

  /**
   * the json schema of base property (including for every elements the contactlabProperties object with property `label`, `enabled`.
   */
  public final String schema;

  public String toString() {
    return "BasePropertyModel(" + schema + ")";
  }

}
