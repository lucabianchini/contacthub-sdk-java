package it.contactlab.hub.sdk.java.models;

import org.immutables.value.Value;

import java.util.Optional;

/**
 * Context info about events made in retail context.
 */
@Value.Immutable
@Value.Style(typeImmutable = "*")
public abstract class AbstractRetailContextInfo {

  /**
   * The sales assistant of this Event context info.
   */
  public abstract Optional<SalesAssistant> salesAssistant();

  /**
   * The client of this Event context info.
   */
  public abstract Client client();

  /**
   * The store of this Event context info.
   */
  public abstract Store store();

  /**
   * The user for this Event context info.
   */
  public abstract User user();
}
