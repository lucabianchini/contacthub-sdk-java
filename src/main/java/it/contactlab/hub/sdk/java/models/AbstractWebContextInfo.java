package it.contactlab.hub.sdk.java.models;

import org.immutables.value.Value;

/**
 * Context info about events made in web context.
 */
@Value.Immutable
@Value.Style(typeImmutable = "*")
public abstract class AbstractWebContextInfo {

  /**
   * The client of this Event context info.
   */
  public abstract Client client();

  /**
   * The user for this Event context info.
   */
  public abstract User user();
}
