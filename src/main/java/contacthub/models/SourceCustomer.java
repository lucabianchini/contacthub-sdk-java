 /* Automatically generated from Swagger definitions */
 /* DO NOT EDIT MANUALLY */

package com.contactlab.contacthub.models;

import java.util.List;
import java.util.Map;

public class SourceCustomer {

  public SourceCustomer(String nodeId, String extra, Map<String, Object> extended, Map<String, Object> tags, String id, String updatedAt, Map<String, Object> _links, String registeredAt, String externalId, Boolean enabled, Map<String, Object> base) {
    this.nodeId = nodeId;
    this.extra = extra;
    this.extended = extended;
    this.tags = tags;
    this.id = id;
    this.updatedAt = updatedAt;
    this._links = _links;
    this.registeredAt = registeredAt;
    this.externalId = externalId;
    this.enabled = enabled;
    this.base = base;
  }

  /**
   * entry node `id`
   */
  public final String nodeId;

  /**
   * custom data defined by workspace not based on a declarated schema
   */
  public final String extra;

  /**
   * custom data defined by workspace based on a declarated schema
   */
  public final Map<String, Object> extended;

  /**
   * the default tags property of customers
   */
  public final Map<String, Object> tags;

  /**
   * the source id
   */
  public final String id;

  /**
   * the source customer update timestamp
   */
  public final String updatedAt;

  /**
   * the related links of source customer
   */
  public final Map<String, Object> _links;

  /**
   * the source customer registration timestamp
   */
  public final String registeredAt;

  /**
   * the external id of customer
   */
  public final String externalId;

  /**
   * flag for soft delete
   */
  public final Boolean enabled;

  /**
   * properties predefined in contacthub in base to the model retrived in /models/properties/base
   */
  public final Map<String, Object> base;

  public String toString() {
    return "SourceCustomer(" + nodeId + extra + extended + tags + id + updatedAt + _links + registeredAt + externalId + enabled + base + ")";
  }

}
