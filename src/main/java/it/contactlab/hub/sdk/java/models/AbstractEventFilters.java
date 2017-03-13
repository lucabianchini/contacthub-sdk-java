package it.contactlab.hub.sdk.java.models;

import org.immutables.value.Value;

import java.time.OffsetDateTime;
import java.util.Optional;

@Value.Immutable
@Value.Style(typeImmutable = "*")
public abstract class AbstractEventFilters {

  public abstract Optional<Integer> page();

  public abstract Optional<EventType> type();

  public abstract Optional<EventContext> context();

  public abstract Optional<EventMode> mode();

  public abstract Optional<OffsetDateTime> dateFrom();

  public abstract Optional<OffsetDateTime> dateTo();

}
