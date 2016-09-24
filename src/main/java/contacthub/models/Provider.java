 /* Automatically generated from Swagger definitions */
 /* DO NOT EDIT MANUALLY */

package com.contactlab.hub.models;

import java.util.List;
import java.util.Map;

public class Provider {

  public Provider(String propertiesSchema, String description, String id) {
    this.propertiesSchema = propertiesSchema;
    this.description = description;
    this.id = id;
  }

  /**
   * json schema of properties necessary when define a source
   */
  public final String propertiesSchema;

  /**
   * description of provider
   */
  public final String description;

  /**
   * id of provider that you must use in `type` property when define a source
   */
  public final String id;

  public String toString() {
    return "Provider(" + propertiesSchema + description + id + ")";
  }

}
