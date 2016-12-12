package it.contactlab.hub.sdk.java.models.base;

import org.immutables.gson.Gson;
import org.immutables.value.Value;

import java.time.OffsetDateTime;

@Gson.TypeAdapters
@Value.Immutable
@Value.Style(typeImmutable = "*")
public abstract class AbstractLike {

  public abstract String id();

  public abstract String category();

  public abstract String name();

  public abstract OffsetDateTime createdTime();

}
