package it.contactlab.hub.sdk.java.models.base;

import org.immutables.gson.Gson;
import org.immutables.value.Value;

@Gson.TypeAdapters
@Value.Immutable
@Value.Style(typeImmutable = "*")
public abstract class AbstractOtherContact {

  public abstract String name();

  public abstract Type type();

  public abstract String value();

  public enum Type {
    MOBILE,
    PHONE,
    EMAIL,
    FAX,
    OTHER;
  }

}
