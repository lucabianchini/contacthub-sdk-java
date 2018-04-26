package it.contactlab.hub.sdk.java.models;

import org.immutables.value.Value;

import java.util.Optional;

/**
 * Customer's consents information - part of automatic marketing's consents.
 */
@Value.Immutable
@Value.Style(typeImmutable = "*")
public abstract class AbstractAutomaticMarketing {

  /**
   * Consent for marketing by an automatic sms.
   */
  public abstract Optional<Consent> sms();

  /**
   * Consent for marketing by an automatic email.
   */
  public abstract Optional<Consent> email();

  /**
   * Consent for marketing by an automatic mobile push.
   */
  public abstract Optional<Consent> push();

  /**
   * Consent for marketing by an automatic instant message.
   */
  public abstract Optional<Consent> im();

  /**
   * Consent for marketing by an automatic telephonic call.
   */
  public abstract Optional<Consent> telephonic();
}
