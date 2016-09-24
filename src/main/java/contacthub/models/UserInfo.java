 /* Automatically generated from Swagger definitions */
 /* DO NOT EDIT MANUALLY */

package com.contactlab.hub.models;

import java.util.List;
import java.util.Map;

public class UserInfo {

  public UserInfo(String name, String surname, String id, List<GroupInfo> groups) {
    this.name = name;
    this.surname = surname;
    this.id = id;
    this.groups = groups;
  }

  /**
   * name of user
   */
  public final String name;

  /**
   * surname of user
   */
  public final String surname;

  /**
   * unique identifier of user
   */
  public final String id;

  /**
   * TODO
   */
  public final List<GroupInfo> groups;

  public String toString() {
    return "UserInfo(" + name + surname + id + groups + ")";
  }

}
