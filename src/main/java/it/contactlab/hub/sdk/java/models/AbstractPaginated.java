package it.contactlab.hub.sdk.java.models;

import org.immutables.value.Value;

import java.util.List;

@Value.Immutable
@Value.Style(typeImmutable = "*")
public abstract class AbstractPaginated<T> {

  public abstract List<T> elements();

  public abstract Page page();

}
