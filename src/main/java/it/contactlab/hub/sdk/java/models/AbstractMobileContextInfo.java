package it.contactlab.hub.sdk.java.models;

import org.immutables.value.Value;

/**
 * Context info about events made in mobile context.
 */
@Value.Immutable
@Value.Style(typeImmutable = "*")
public abstract class AbstractMobileContextInfo {

  /**
   * The client of this Event context info.
   */
  public abstract Client client();

  /**
   * The device of this Event context info.
   */
  public abstract Device device();

  /**
   * The user for this Event context info.
   */
  public abstract User user();
}
