 /* Automatically generated from Swagger definitions */
 /* DO NOT EDIT MANUALLY */

package com.contactlab.hub.models;

import java.util.List;
import java.util.Map;

public class PostEntryNodeConfiguration {

  public PostEntryNodeConfiguration(String description, List<String> sourcePriority, String imageUrl, List<String> mandatoryProperties, String name) {
    this.description = description;
    this.sourcePriority = sourcePriority;
    this.imageUrl = imageUrl;
    this.mandatoryProperties = mandatoryProperties;
    this.name = name;
  }

  /**
   * the description of entry node
   */
  public final String description;

  /**
   * array of source `id`
   */
  public final List<String> sourcePriority;

  /**
   * the url of logo
   */
  public final String imageUrl;

  /**
   * array of mandatory properties
   */
  public final List<String> mandatoryProperties;

  /**
   * the name of entry node
   */
  public final String name;

  public String toString() {
    return "PostEntryNodeConfiguration(" + description + sourcePriority + imageUrl + mandatoryProperties + name + ")";
  }

}
