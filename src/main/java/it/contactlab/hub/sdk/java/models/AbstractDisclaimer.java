package it.contactlab.hub.sdk.java.models;

import org.immutables.value.Value;

import java.time.OffsetDateTime;
import java.util.Optional;

/**
 * Customer's consents information - part for legal acceptances.
 */
@Value.Immutable
@Value.Style(typeImmutable = "*")
public abstract class AbstractDisclaimer {

  /**
   * The date of acceptance of the disclaimer.
   */
  public abstract Optional<OffsetDateTime> date();

  /**
   * Version of disclaimer.
   */
  public abstract Optional<String> version();
}
