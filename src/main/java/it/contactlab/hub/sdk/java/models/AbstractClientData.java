package it.contactlab.hub.sdk.java.models;

import org.immutables.value.Value;

@Value.Immutable
@Value.Style(typeImmutable = "*")
public abstract class AbstractClientData {

  public abstract String correlationId();

}
