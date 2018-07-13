package it.contactlab.hub.sdk.java.models;

import org.immutables.value.Value;

import java.util.Optional;

/**
 * A created Event.
 */
@Value.Immutable
@Value.Style(typeImmutable = "*")
public abstract class AbstractEventCreated {
  /**
   * The event id.
   */
  public abstract Optional<String> id();

}
