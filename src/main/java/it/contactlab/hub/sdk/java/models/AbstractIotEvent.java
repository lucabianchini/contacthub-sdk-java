package it.contactlab.hub.sdk.java.models;

import org.immutables.value.Value;

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.Optional;

/**
 * An Event made in Internet of Things context.
 */
@Value.Immutable
@Value.Style(typeImmutable = "*")
public abstract class AbstractIotEvent extends Event {

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
  @Value.Derived
  public EventContext context() {
    return EventContext.IOT;
  }

  /**
   * The properties of this Event.
   */
  public abstract Map<String, Object> properties();

  /**
   * The context-specific properties of this Event.
   */
  @Override
  public abstract Optional<IotContextInfo> contextInfo();

  /**
   * The moment when this Event happened.
   *
   * <p>It defaults to "now()" if not specified when creating a new Event.</p>
   */
  @Value.Default
  public OffsetDateTime date() {
    return OffsetDateTime.now();
  }

  /**
   * The moment when this Event was registered.
   */
  public abstract Optional<OffsetDateTime> registeredAt();
}
