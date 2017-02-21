package it.contactlab.hub.sdk.java.models.base;

import org.immutables.value.Value;

import java.time.OffsetDateTime;
import java.util.Optional;

@Value.Immutable
@Value.Style(typeImmutable = "*")
public abstract class AbstractLike {

  public abstract String id();

  public abstract Optional<String> category();

  public abstract Optional<String> name();

  public abstract Optional<OffsetDateTime> createdTime();

}
