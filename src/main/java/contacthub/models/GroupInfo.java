 /* Automatically generated from Swagger definitions */
 /* DO NOT EDIT MANUALLY */

package com.contactlab.hub.models;

import java.util.List;
import java.util.Map;

public class GroupInfo {

  public GroupInfo(List<WorkspaceInfo> workspaces, String name, String id) {
    this.workspaces = workspaces;
    this.name = name;
    this.id = id;
  }

  /**
   * TODO
   */
  public final List<WorkspaceInfo> workspaces;

  /**
   * name of group
   */
  public final String name;

  /**
   * unique identifier of group
   */
  public final String id;

  public String toString() {
    return "GroupInfo(" + workspaces + name + id + ")";
  }

}
