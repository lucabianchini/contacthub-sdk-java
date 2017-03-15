package it.contactlab.hub.sdk.java.queries;

import org.immutables.value.Value;

import java.util.List;

@Value.Immutable
@Value.Style(typeImmutable = "*")
public abstract class AbstractCombinedQuery implements Query {

  public final String type = "combined";

  public abstract QueryConjunction conjunction();

  public abstract List<Query> queries();

}
