package it.contactlab.hub.sdk.java.models;

import org.immutables.value.Value;

/**
 * Context info about an Event.
 */
public abstract class AbstractContextInfo {

  /**
   * The client of this Event context info.
   */
  public abstract Client client();

  /**
   * The user for this Event context info.
   */
  public abstract User user();
}
