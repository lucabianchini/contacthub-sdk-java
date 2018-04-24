package it.contactlab.hub.sdk.java.models;

import org.immutables.value.Value;

import java.util.Optional;

/**
 * Base consent type.
 */
@Value.Immutable
@Value.Style(typeImmutable = "*")
public abstract class AbstractConsent {

  /**
   * Status of consent.
   */
  public abstract Optional<Boolean> status();

  /**
   * Status of consent limitation.
   */
  public abstract Optional<Boolean> limitation();

  /**
   * Status of consent objection.
   */
  public abstract Optional<Boolean> objection();
}
