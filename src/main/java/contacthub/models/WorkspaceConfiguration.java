 /* Automatically generated from Swagger definitions */
 /* DO NOT EDIT MANUALLY */

package com.contactlab.hub.models;

import java.util.List;
import java.util.Map;

public class WorkspaceConfiguration {

  public WorkspaceConfiguration(String industry, String externalIdPolicy, String logo, String name, String timezone) {
    this.industry = industry;
    this.externalIdPolicy = externalIdPolicy;
    this.logo = logo;
    this.name = name;
    this.timezone = timezone;
  }

  /**
   * industry of workspace
   */
  public final String industry;

  /**
   * the external id check policy (NO_CHECK, UNIQUE_NODE, UNIQUE_WORKSPACE)
   */
  public final String externalIdPolicy;

  /**
   * url of workspace logo
   */
  public final String logo;

  /**
   * name of workspace
   */
  public final String name;

  /**
   * default timezone of workspace
   */
  public final String timezone;

  public String toString() {
    return "WorkspaceConfiguration(" + industry + externalIdPolicy + logo + name + timezone + ")";
  }

}
