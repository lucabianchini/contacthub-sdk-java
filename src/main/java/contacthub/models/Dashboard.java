 /* Automatically generated from Swagger definitions */
 /* DO NOT EDIT MANUALLY */

package com.contactlab.hub.models;

import java.util.List;
import java.util.Map;

public class Dashboard {

  public Dashboard(String industry, String description, String status, String id, String range, String label, String name, List<Widget> widgets, Boolean enabled) {
    this.industry = industry;
    this.description = description;
    this.status = status;
    this.id = id;
    this.range = range;
    this.label = label;
    this.name = name;
    this.widgets = widgets;
    this.enabled = enabled;
  }

  /**
   * Industy
   */
  public final String industry;

  /**
   * Description
   */
  public final String description;

  /**
   * Status
   */
  public final String status;

  /**
   * Dashboard identifier
   */
  public final String id;

  /**
   * Range
   */
  public final String range;

  /**
   * Label
   */
  public final String label;

  /**
   * Name
   */
  public final String name;

  /**
   * Widgets
   */
  public final List<Widget> widgets;

  /**
   * Enabled flag
   */
  public final Boolean enabled;

  public String toString() {
    return "Dashboard(" + industry + description + status + id + range + label + name + widgets + enabled + ")";
  }

}
