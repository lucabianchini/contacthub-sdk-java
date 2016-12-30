package it.contactlab.hub.sdk.java.models;

import com.google.gson.JsonObject;
import org.immutables.value.Value;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Optional;

/**
 * An Event.
 */
@Value.Immutable
@Value.Style(typeImmutable = "*")
public abstract class AbstractEvent {

  /**
   * The Customer ID for this Event.
   */
  public abstract Optional<String> customerId();

  /**
   * The event type of this Event.
   */
  public abstract String type();

  /**
   * The event context of this Event.
   */
  public abstract String context();

  /**
   * The properties of this Event.
   */
  public abstract JsonObject properties();

  /**
   * The moment when this Event happened.
   *
   * <p>It defaults to "now()" if not specified when creating a new Event.</p>
   */
  @Value.Default
  public OffsetDateTime date() {
    return OffsetDateTime.now();
  }
}
