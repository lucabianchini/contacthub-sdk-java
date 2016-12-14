package it.contactlab.hub.sdk.java.models.base;

import org.immutables.value.Value;

@Value.Immutable
@Value.Style(typeImmutable = "*")
public abstract class AbstractPreference {

  public abstract String key();

  public abstract String value();

}
