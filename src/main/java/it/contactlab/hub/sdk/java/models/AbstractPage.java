package it.contactlab.hub.sdk.java.models;

import org.immutables.value.Value;

@Value.Immutable
@Value.Style(typeImmutable = "*")
public abstract class AbstractPage {

  public abstract Integer number();

  public abstract Integer size();

  public abstract Integer totalElements();

  public abstract Integer totalPages();

}
