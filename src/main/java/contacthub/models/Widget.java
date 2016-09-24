 /* Automatically generated from Swagger definitions */
 /* DO NOT EDIT MANUALLY */

package com.contactlab.contacthub.models;

import java.util.List;
import java.util.Map;

public class Widget {

  public Widget(Map<String, Object> graph, String range, String dataSource, String label, String order, String name) {
    this.graph = graph;
    this.range = range;
    this.dataSource = dataSource;
    this.label = label;
    this.order = order;
    this.name = name;
  }

  /**
   * Graph
   */
  public final Map<String, Object> graph;

  /**
   * Range
   */
  public final String range;

  /**
   * Data source
   */
  public final String dataSource;

  /**
   * Label
   */
  public final String label;

  /**
   * Order
   */
  public final String order;

  /**
   * Name
   */
  public final String name;

  public String toString() {
    return "Widget(" + graph + range + dataSource + label + order + name + ")";
  }

}
