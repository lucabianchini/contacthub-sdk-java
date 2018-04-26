package it.contactlab.hub.sdk.java.models;

import org.immutables.value.Value;

import java.util.Optional;

/**
 * Customer's consents information - part of softspam's consents.
 */
@Value.Immutable
@Value.Style(typeImmutable = "*")
public abstract class AbstractSoftSpam {

  /**
   * Consent for e-mail softspam.
   */
  public abstract Optional<Consent> email();

  /**
   * Consent for papery softspam.
   */
  public abstract Optional<Consent> papery();
}
