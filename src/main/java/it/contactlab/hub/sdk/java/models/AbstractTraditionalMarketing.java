package it.contactlab.hub.sdk.java.models;

import org.immutables.value.Value;

import java.util.Optional;

/**
 * Customer's consents information - part of traditional marketing's consents.
 */
@Value.Immutable
@Value.Style(typeImmutable = "*")
public abstract class AbstractTraditionalMarketing {

  /**
   * Consent for traditional marketing by a telephonic call.
   */
  public abstract Optional<Consent> telephonic();

  /**
   * Consent for traditional marketing by a papery.
   */
  public abstract Optional<Consent> papery();
}
