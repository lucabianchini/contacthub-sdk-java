package it.contactlab.hub.sdk.java.models;

import org.immutables.value.Value;

import java.util.Optional;

/**
 * Customer's consents information - part of third party transfer's consents.
 */
@Value.Immutable
@Value.Style(typeImmutable = "*")
public abstract class AbstractThirdPartyTransfer {

  /**
   * Consent transfer to third parties for profiling.
   */
  public abstract Optional<Consent> profiling();

  /**
   * Consent transfer to third parties for marketing.
   */
  public abstract Optional<Consent> marketing();
}
