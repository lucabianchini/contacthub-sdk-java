package it.contactlab.hub.sdk.java.models;

import org.immutables.value.Value;

import java.util.Optional;

/**
 * Customer's consents information - part of marketing's consents.
 */
@Value.Immutable
@Value.Style(typeImmutable = "*")
public abstract class AbstractMarketing {

  /**
   * The part of traditional marketing's consents.
   */
  public abstract Optional<TraditionalMarketing> traditional();

  /**
   * The part of automatic marketing's consents.
   */
  public abstract Optional<AutomaticMarketing> automatic();
}
