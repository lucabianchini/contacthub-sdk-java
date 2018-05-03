package it.contactlab.hub.sdk.java.models;

import org.immutables.value.Value;

import java.util.Optional;

/**
 * Details about the client in an event context info.
 */
@Value.Immutable
@Value.Style(typeImmutable = "*")
public abstract class AbstractClient {

  /**
   * The user agent of the client.
   */
  public abstract Optional<String> userAgent();

  /**
   * The ipv4 of the client.
   */
  public abstract String ip();

  /**
   * The localization of the client.
   */
  public abstract Optional<Localization> localization();
}
