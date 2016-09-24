 /* Automatically generated from Swagger definitions */
 /* DO NOT EDIT MANUALLY */

package com.contactlab.contacthub.models;

import java.util.List;
import java.util.Map;

public class PostViewNodeConfiguration {

  public PostViewNodeConfiguration(String name, String description, String imageUrl) {
    this.name = name;
    this.description = description;
    this.imageUrl = imageUrl;
  }

  /**
   * the name of view node
   */
  public final String name;

  /**
   * the description of view node
   */
  public final String description;

  /**
   * the url of logo
   */
  public final String imageUrl;

  public String toString() {
    return "PostViewNodeConfiguration(" + name + description + imageUrl + ")";
  }

}
