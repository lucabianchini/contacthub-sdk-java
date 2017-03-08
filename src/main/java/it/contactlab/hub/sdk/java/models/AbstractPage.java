package it.contactlab.hub.sdk.java.models;

import org.immutables.value.Value;

@Value.Immutable
@Value.Style(typeImmutable = "*")
public abstract class AbstractPage {

  public abstract int number();

  public abstract int size();

  public abstract int totalElements();

  public abstract int totalPages();

}
