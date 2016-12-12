package it.contactlab.hub.sdk.java.models.base;

import org.immutables.gson.Gson;
import org.immutables.value.Value;

@Gson.TypeAdapters
@Value.Immutable
@Value.Style(typeImmutable = "*")
public abstract class AbstractMobileDevice {

  public abstract String identifier();

  public abstract String name();

  public abstract Type type();

  public enum Type {
    IOS, GCM, WP
  }

}
