 /* Automatically generated from Swagger definitions */
 /* DO NOT EDIT MANUALLY */

package com.contactlab.contacthub.models;

import java.util.List;
import java.util.Map;

public class Trend {

  public Trend(Map<String, Object> parameters, List<Map<String, Object>> data, String name, Map<String, Object> headers, Map<String, Object> _links) {
    this.parameters = parameters;
    this.data = data;
    this.name = name;
    this.headers = headers;
    this._links = _links;
  }

  /**
   * TODO
   */
  public final Map<String, Object> parameters;

  /**
   * list of node
   */
  public final List<Map<String, Object>> data;

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
  public final Map<String, Object> _links;

  public String toString() {
    return "Trend(" + parameters + data + name + headers + _links + ")";
  }

}
