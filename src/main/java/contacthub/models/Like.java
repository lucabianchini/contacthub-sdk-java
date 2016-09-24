 /* Automatically generated from Swagger definitions */
 /* DO NOT EDIT MANUALLY */

package com.contactlab.hub.models;

import java.util.List;
import java.util.Map;

public class Like {

  public Like(String createdTime, String name, String category, String id) {
    this.createdTime = createdTime;
    this.name = name;
    this.category = category;
    this.id = id;
  }

  /**
   * the like creation datetime
   */
  public final String createdTime;

  /**
   * the name of like
   */
  public final String name;

  /**
   * The name of like category
   */
  public final String category;

  /**
   * unique identifier of like
   */
  public final String id;

  public String toString() {
    return "Like(" + createdTime + name + category + id + ")";
  }

}
