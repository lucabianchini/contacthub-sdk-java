package it.contactlab.hub.sdk.java.models;

import org.immutables.value.Value;

/**
 *  Localization.
 */
@Value.Immutable
@Value.Style(typeImmutable = "*")
public abstract class AbstractLocalization {

  /**
   * City.
   */
  public abstract String city();

  /**
   * Country.
   */
  public abstract String country();

  /**
   * Region.
   */
  public abstract String region();

  /**
   * Province.
   */
  public abstract String province();

  /**
   * Zip code.
   */
  public abstract String zip();

  /**
   * Geographical coordinates.
   */
  public abstract Geo geo();

}
