package it.contactlab.hub.sdk.java.queries;

import org.immutables.value.Value;

import java.util.List;

@Value.Immutable
@Value.Style(typeImmutable = "*")
public abstract class AbstractCompositeCondition implements Condition {

  public final String type = "composite";

  public abstract ConditionConjunction conjunction();

  public abstract List<Condition> conditions();

}
