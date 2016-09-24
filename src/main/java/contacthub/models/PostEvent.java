 /* Automatically generated from Swagger definitions */
 /* DO NOT EDIT MANUALLY */

package com.contactlab.hub.models;

import java.util.List;
import java.util.Map;

public class PostEvent {

  public PostEvent(String date, Map<String, Object> contextInfo, String customerId, Map<String, Object> properties, BringBackProperty bringBackProperties, String context, String type) {
    this.date = date;
    this.contextInfo = contextInfo;
    this.customerId = customerId;
    this.properties = properties;
    this.bringBackProperties = bringBackProperties;
    this.context = context;
    this.type = type;
  }

  /**
   * the timestamp of event
   */
  public final String date;

  /**
   * the json schema related to event context
   */
  public final Map<String, Object> contextInfo;

  /**
   * customer id
   */
  public final String customerId;

  /**
   * the json schema related to event type
   */
  public final Map<String, Object> properties;

  /**
   * TODO
   */
  public final BringBackProperty bringBackProperties;

  /**
   * the name of context event you are tracking. The value are defined in `id` property of method /model/contexts
   */
  public final String context;

  /**
   * the name of type event you are tracking
   */
  public final String type;

  public String toString() {
    return "PostEvent(" + date + contextInfo + customerId + properties + bringBackProperties + context + type + ")";
  }

}
