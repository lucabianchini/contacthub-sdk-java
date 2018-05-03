package it.contactlab.hub.sdk.java.models;

import org.immutables.value.Value;

/**
 * Events made in contact center context.
 */
@Value.Immutable
@Value.Style(typeImmutable = "*")
public abstract class AbstractContactCenterContextInfo {

  /**
   * The client of this Event context info.
   */
  public abstract Client client();

  /**
   * The user for this Event context info.
   */
  public abstract User user();
}
