 /* Automatically generated from Swagger definitions */
 /* DO NOT EDIT MANUALLY */

package com.contactlab.hub.models;

import java.util.List;
import java.util.Map;

public class AuditEvent {

  public AuditEvent(String sourceId, String nodeId, String date, String id, String customerId, Map<String, Object> properties, Map<String, Object> _links, String type) {
    this.sourceId = sourceId;
    this.nodeId = nodeId;
    this.date = date;
    this.id = id;
    this.customerId = customerId;
    this.properties = properties;
    this._links = _links;
    this.type = type;
  }

  /**
   * source id
   */
  public final String sourceId;

  /**
   * node id
   */
  public final String nodeId;

  /**
   * the timestamp of event
   */
  public final String date;

  /**
   * Unique identifier of event
   */
  public final String id;

  /**
   * customer id
   */
  public final String customerId;

  /**
   * the json schema related to event type
   */
  public final Map<String, Object> properties;

  /**
   * the related links of event
   */
  public final Map<String, Object> _links;

  /**
   * the name of type event you're tracking
   */
  public final String type;

  public String toString() {
    return "AuditEvent(" + sourceId + nodeId + date + id + customerId + properties + _links + type + ")";
  }

}
