package it.contactlab.hub.sdk.java.models;

import org.immutables.value.Value;

import java.util.Optional;

/**
 * Customer Tags.
 */
@Value.Immutable
@Value.Style(typeImmutable = "*")
public abstract class AbstractEvent {

  public abstract String customerId();

  public abstract String type();

  public abstract String context();

  public abstract Object properties();
}
