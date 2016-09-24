 /* Automatically generated from Swagger definitions */
 /* DO NOT EDIT MANUALLY */

package com.contactlab.contacthub.models;

import java.util.List;
import java.util.Map;

public class Context {

  public Context(String propertiesSchema, String description, String id, String label) {
    this.propertiesSchema = propertiesSchema;
    this.description = description;
    this.id = id;
    this.label = label;
  }

  /**
   * json schema of properties for the context
   */
  public final String propertiesSchema;

  /**
   * description of context
   */
  public final String description;

  /**
   * id of context that you must use in `context` property when define an event
   */
  public final String id;

  /**
   * the label of context
   */
  public final String label;

  public String toString() {
    return "Context(" + propertiesSchema + description + id + label + ")";
  }

}
