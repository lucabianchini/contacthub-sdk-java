package it.contactlab.hub.sdk.java.models;

import org.immutables.value.Value;

import java.util.Optional;

/**
 * Context info about events made in retail context.
 */
@Value.Immutable
@Value.Style(typeImmutable = "*")
public abstract class AbstractRetailContextInfo extends AbstractContextInfo{

  /**
   * The sales assistant of this Event context info.
   */
  public abstract Optional<SalesAssistant> salesAssistant();
  /**
   * The store of this Event context info.
   */
  public abstract Store store();
}
