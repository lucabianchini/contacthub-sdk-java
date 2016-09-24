 /* Automatically generated from Swagger definitions */
 /* DO NOT EDIT MANUALLY */

package com.contactlab.hub.models;

import java.util.List;
import java.util.Map;

public class CompositionAgeRange {

  public CompositionAgeRange(String name, Map<String, Object> headers, Map<String, Object> parameters, List<Map<String, Object>> data) {
    this.name = name;
    this.headers = headers;
    this.parameters = parameters;
    this.data = data;
  }

  /**
   * the analytics name
   */
  public final String name;

  /**
   * the schema of object inside data property list
   */
  public final Map<String, Object> headers;

  /**
   * TODO
   */
  public final Map<String, Object> parameters;

  /**
   * list of node
   */
  public final List<Map<String, Object>> data;

  public String toString() {
    return "CompositionAgeRange(" + name + headers + parameters + data + ")";
  }

}
