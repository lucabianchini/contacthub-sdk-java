 /* Automatically generated from Swagger definitions */
 /* DO NOT EDIT MANUALLY */

package com.contactlab.hub.models;

import java.util.List;
import java.util.Map;

public class TreeNode {

  public TreeNode(List<String> nodePriority, String id, List<TreeNode> children) {
    this.nodePriority = nodePriority;
    this.id = id;
    this.children = children;
  }

  /**
   * array of entry node `id`
   */
  public final List<String> nodePriority;

  /**
   * the id of entry or view node
   */
  public final String id;

  /**
   * the list of children nodes
   */
  public final List<TreeNode> children;

  public String toString() {
    return "TreeNode(" + nodePriority + id + children + ")";
  }

}
