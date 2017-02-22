package it.contactlab.hub.sdk.java.models;

import org.immutables.value.Value;

/**
 * The geographic localization.
 */
@Value.Immutable
@Value.Style(typeImmutable = "*")
public abstract class AbstractGeo {

  /**
   * Latitude.
   */
  public abstract Double lat();

  /**
   * Longitude.
   */
  public abstract Double lon();

}
