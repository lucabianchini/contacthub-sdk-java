 /* Automatically generated from Swagger definitions */
 /* DO NOT EDIT MANUALLY */

package com.contactlab.contacthub.models;

import java.util.List;
import java.util.Map;

public class AnalyticDistributionEventTime {

  public AnalyticDistributionEventTime(Map<String, Object> parameters, List<AnalyticDistributionEventTimeValue> data, String name, Map<String, Object> headers, Map<String, Object> _links) {
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
   * List of data aggregated by node identifiers and time format
   */
  public final List<AnalyticDistributionEventTimeValue> data;

  /**
   * Analytics name
   */
  public final String name;

  /**
   * Schema of object inside data property list
   */
  public final Map<String, Object> headers;

  /**
   * TODO
   */
  public final Map<String, Object> _links;

  public String toString() {
    return "AnalyticDistributionEventTime(" + parameters + data + name + headers + _links + ")";
  }

}
