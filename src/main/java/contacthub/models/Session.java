 /* Automatically generated from Swagger definitions */
 /* DO NOT EDIT MANUALLY */

package com.contactlab.hub.models;

import java.util.List;
import java.util.Map;

public class Session {

  public Session(String value, String id, Map<String, Object> _links) {
    this.value = value;
    this.id = id;
    this._links = _links;
  }

  /**
   * the session id of customer. the value is unique for a single customer
   */
  public final String value;

  /**
   * Unique identifier of session
   */
  public final String id;

  /**
   * the related links of session
   */
  public final Map<String, Object> _links;

  public String toString() {
    return "Session(" + value + id + _links + ")";
  }

}
