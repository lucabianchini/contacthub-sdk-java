package it.contactlab.hub.sdk.java.models;

import org.immutables.value.Value;

import java.util.Optional;

/**
 * Customer's consents information - part of profiling's consents.
 */
@Value.Immutable
@Value.Style(typeImmutable = "*")
public abstract class AbstractProfiling {

  /**
   * Consent for classic profiling.
   */
  public abstract Optional<Consent> classic();

  /**
   * Consent for classic profiling.
   */
  public abstract Optional<Consent> online();
}
