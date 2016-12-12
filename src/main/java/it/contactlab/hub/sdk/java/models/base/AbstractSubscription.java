package it.contactlab.hub.sdk.java.models.base;

import org.immutables.gson.Gson;
import org.immutables.value.Value;

import java.time.OffsetDateTime;
import java.util.List;

@Gson.TypeAdapters
@Value.Immutable
@Value.Style(typeImmutable = "*")
public abstract class AbstractSubscription {

  public abstract String id();

  public abstract String name();

  public abstract String type();

  public abstract Kind kind();

  public abstract Boolean subscribed();

  public abstract OffsetDateTime dateStart();

  public abstract OffsetDateTime dateEnd();

  public abstract String subscriberId();

  public abstract OffsetDateTime registeredAt();

  public abstract OffsetDateTime updatedAt();

  public abstract List<Preference> preferences();

  public enum Kind {

    DIGITAL_MESSAGE,
    SERVICE,
    OTHER

  }

}
