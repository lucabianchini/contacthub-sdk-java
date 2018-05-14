package it.contactlab.hub.sdk.java.models;

import org.immutables.value.Value;

/**
 * Context info about events made in mobile context.
 */
@Value.Immutable
@Value.Style(typeImmutable = "*")
public abstract class AbstractMobileContextInfo extends AbstractContextInfo{
  /**
   * The device of this Event context info.
   */
  public abstract Device device();
}
