package it.contactlab.hub.sdk.java.models;

import org.immutables.value.Value;

import java.util.Optional;

/**
 * Context info about events made in e-commerce context.
 */
@Value.Immutable
@Value.Style(typeImmutable = "*")
public abstract class AbstractEcommerceContextInfo extends AbstractContextInfo{
  /**
   * The store for this Event context info.
   */
  public abstract Optional<Store> store();
}
