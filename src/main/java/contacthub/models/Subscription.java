 /* Automatically generated from Swagger definitions */
 /* DO NOT EDIT MANUALLY */

package com.contactlab.contacthub.models;

import java.util.List;
import java.util.Map;

public class Subscription {

  public Subscription(String kind, String subscriberId, String id, Boolean enable, String dateStart, String name, String updatedAt, String registeredAt, String type, String dateEnd, List<Map<String, Object>> preferences) {
    this.kind = kind;
    this.subscriberId = subscriberId;
    this.id = id;
    this.enable = enable;
    this.dateStart = dateStart;
    this.name = name;
    this.updatedAt = updatedAt;
    this.registeredAt = registeredAt;
    this.type = type;
    this.dateEnd = dateEnd;
    this.preferences = preferences;
  }

  /**
   * The kind of subscription
   */
  public final String kind;

  /**
   * unique identifier of subscriber
   */
  public final String subscriberId;

  /**
   * unique identifier of subscription
   */
  public final String id;

  /**
   * TODO
   */
  public final Boolean enable;

  /**
   * start date of subscription
   */
  public final String dateStart;

  /**
   * The name of subscription
   */
  public final String name;

  /**
   * subscription update datetime
   */
  public final String updatedAt;

  /**
   * subscription creation datetime
   */
  public final String registeredAt;

  /**
   * The type of subscription
   */
  public final String type;

  /**
   * end date of subscription
   */
  public final String dateEnd;

  /**
   * TODO
   */
  public final List<Map<String, Object>> preferences;

  public String toString() {
    return "Subscription(" + kind + subscriberId + id + enable + dateStart + name + updatedAt + registeredAt + type + dateEnd + preferences + ")";
  }

}
