package it.contactlab.hub.sdk.java.models;

import org.immutables.value.Value;

import java.util.Optional;

/**
 * Customer's consents information
 */
@Value.Immutable
@Value.Style(typeImmutable = "*")
public abstract class AbstractConsents {

  /**
   * The part for legal acceptances.
   */
  public abstract Optional<Disclaimer> disclaimer();

  /**
   * The part of marketing's consents.
   */
  public abstract Optional<Marketing> marketing();

  /**
   * The part of profiling's consents.
   */
  public abstract Optional<Profiling> profiling();

  /**
   * The part of softspam's consents.
   */
  public abstract Optional<SoftSpam> softSpam();

  /**
   * The part of third party transfer's consents.
   */
  public abstract Optional<ThirdPartyTransfer> thirdPartyTransfer();
}
