package it.contactlab.hub.sdk.java.models;

import org.immutables.value.Value;

/**
 *  Store of the ecommerce context info.
 */
@Value.Immutable
@Value.Style(typeImmutable = "*")
public abstract class AbstractStore {

  /**
   * The identifier of the store.
   */
  public abstract String id();

  /**
   * The name of the store.
   */
  public abstract String name();

  /**
   * The type of the store.
   */
  public abstract StoreType type();

  /**
   * The street of the store.
   */
  public abstract String store();

  /**
   * The city of the store.
   */
  public abstract String city();

  /**
   * The country of the store.
   */
  public abstract String country();

  /**
   * The province of the store.
   */
  public abstract String province();

  /**
   * The region of the store.
   */
  public abstract String region();

  /**
   * The zip code of the store.
   */
  public abstract String zip();

  /**
   * The geographical coordinates of the store.
   */
  public abstract Geo geo();

  /**
   * The website of the store.
   */
  public abstract String website();

}
