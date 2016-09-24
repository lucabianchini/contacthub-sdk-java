 /* Automatically generated from Swagger definitions */
 /* DO NOT EDIT MANUALLY */

package com.contactlab.hub.models;

import java.util.List;
import java.util.Map;

public class Graph {

  public Graph(List<String> axisLabel, Integer widthColumns, Integer heightRows, String type) {
    this.axisLabel = axisLabel;
    this.widthColumns = widthColumns;
    this.heightRows = heightRows;
    this.type = type;
  }

  /**
   * Axis label
   */
  public final List<String> axisLabel;

  /**
   * Rows width
   */
  public final Integer widthColumns;

  /**
   * Rows height
   */
  public final Integer heightRows;

  /**
   * Graph type
   */
  public final String type;

  public String toString() {
    return "Graph(" + axisLabel + widthColumns + heightRows + type + ")";
  }

}
