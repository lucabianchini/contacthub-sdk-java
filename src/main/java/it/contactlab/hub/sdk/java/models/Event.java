package it.contactlab.hub.sdk.java.models;

import org.immutables.value.Value;

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.Optional;

/**
 * An Event.
 */
public abstract class Event {

  /**
   * The ID of this Event.
   */
  public abstract Optional<String> id();

  /**
   * The CustomerId for this Event.
   */
  public abstract Optional<String> customerId();

  /**
   * The ExternalId for this Event.
   */
  public abstract Optional<String> externalId();

  /**
   * The SessionId for this Event.
   */
  public abstract Optional<String> sessionId();

  /**
   * The event type of this Event.
   */
  public abstract EventType type();

  /**
   * The event context of this Event.
   */
  public abstract EventContext context();

  /**
   * The properties of this Event.
   */
  public abstract Map<String, Object> properties();

  /**
   * The context-specific properties of this Event.
   */
  public abstract Optional<? extends AbstractContextInfo> contextInfo();

  /**
   * The moment when this Event happened.
   *
   */
  public abstract OffsetDateTime date();

  /**
   * The moment when this Event was registered.
   */
  public abstract Optional<OffsetDateTime> registeredAt();
}
