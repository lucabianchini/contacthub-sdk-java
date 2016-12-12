package it.contactlab.hub.sdk.java.models.base;

import org.immutables.gson.Gson;
import org.immutables.value.Value;

/**
 * Address informatioa.
 */
@Gson.TypeAdapters
@Value.Immutable
@Value.Style(typeImmutable = "*")
public abstract class AbstractAddress {

  /**
   * The street.
   */
  public abstract String street();

  /**
   * The city.
   */
  public abstract String city();

  /**
   * The country.
   */
  public abstract String country();

  /**
   * The province.
   */
  public abstract String province();

  /**
   * The ZIP code.
   */
  public abstract String zip();

  /**
   * The geographic information.
   */
  public abstract Geo geo();

}
