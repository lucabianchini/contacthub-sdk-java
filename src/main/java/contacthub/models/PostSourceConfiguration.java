 /* Automatically generated from Swagger definitions */
 /* DO NOT EDIT MANUALLY */

package com.contactlab.hub.models;

import java.util.List;
import java.util.Map;

public class PostSourceConfiguration {

  public PostSourceConfiguration(List<String> nodes, String description, String provider, String name, Map<String, Object> properties, Boolean enabled) {
    this.nodes = nodes;
    this.description = description;
    this.provider = provider;
    this.name = name;
    this.properties = properties;
    this.enabled = enabled;
  }

  /**
   * list of unique identifier where source is enabled
   */
  public final List<String> nodes;

  /**
   * the description of source
   */
  public final String description;

  /**
   * the provider of source
   */
  public final String provider;

  /**
   * the name of source
   */
  public final String name;

  /**
   * the properties required by the selected provider
   */
  public final Map<String, Object> properties;

  /**
   * flag for enable-disable source
   */
  public final Boolean enabled;

  public String toString() {
    return "PostSourceConfiguration(" + nodes + description + provider + name + properties + enabled + ")";
  }

}
