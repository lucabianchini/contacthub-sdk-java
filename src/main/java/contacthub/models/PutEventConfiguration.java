 /* Automatically generated from Swagger definitions */
 /* DO NOT EDIT MANUALLY */

package com.contactlab.contacthub.models;

import java.util.List;
import java.util.Map;

public class PutEventConfiguration {

  public PutEventConfiguration(String description, String propertiesSchema, String id, String label, String mode, String type, Boolean enabled) {
    this.description = description;
    this.propertiesSchema = propertiesSchema;
    this.id = id;
    this.label = label;
    this.mode = mode;
    this.type = type;
    this.enabled = enabled;
  }

  /**
   * the description of event
   */
  public final String description;

  /**
   * json schema of event properties
   */
  public final String propertiesSchema;

  /**
   * unique identifier of event
   */
  public final String id;

  /**
   * the label of event
   */
  public final String label;

  /**
   * the mode of event. ACTIVE if the customer made the event, PASSIVE if the customer recive the event
   */
  public final String mode;

  /**
   * the type of event
   */
  public final String type;

  /**
   * flag for enable-disable event
   */
  public final Boolean enabled;

  public String toString() {
    return "PutEventConfiguration(" + description + propertiesSchema + id + label + mode + type + enabled + ")";
  }

}
