 /* Automatically generated from Swagger definitions */
 /* DO NOT EDIT MANUALLY */

package com.contactlab.hub.models;

import java.util.List;
import java.util.Map;

public class BasePropertyConfiguration {

  public BasePropertyConfiguration(String name, String description, Boolean enabled) {
    this.name = name;
    this.description = description;
    this.enabled = enabled;
  }

  /**
   * the name of base property
   */
  public final String name;

  /**
   * the description of base property
   */
  public final String description;

  /**
   * flag for enable/disable the base property
   */
  public final Boolean enabled;

  public String toString() {
    return "BasePropertyConfiguration(" + name + description + enabled + ")";
  }

}
