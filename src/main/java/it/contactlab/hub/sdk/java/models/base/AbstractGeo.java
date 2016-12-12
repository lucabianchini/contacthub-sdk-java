package it.contactlab.hub.sdk.java.models.base;

import org.immutables.gson.Gson;
import org.immutables.value.Value;

/**
 * The geographic localization.
 */
@Gson.TypeAdapters
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
  public abstract Double lng();

}
