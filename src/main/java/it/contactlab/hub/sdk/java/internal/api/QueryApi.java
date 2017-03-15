package it.contactlab.hub.sdk.java.internal.api;

import it.contactlab.hub.sdk.java.queries.AtomicCondition;
import it.contactlab.hub.sdk.java.queries.ConditionContainer;
import it.contactlab.hub.sdk.java.queries.Operator;
import it.contactlab.hub.sdk.java.queries.QueryContainer;
import it.contactlab.hub.sdk.java.queries.SimpleQuery;

import java.util.Optional;

public class QueryApi {

  /**
   * Returns a {@link QueryContainer} based on some simple arguments.
   */
  public static QueryContainer createQuery(String attribute, Operator operator,
      Optional<Object> value) {
    return QueryContainer.builder()
      .name("SdkGeneratedQuery")
      .query(SimpleQuery.builder()
        .are(ConditionContainer.builder()
          .condition(AtomicCondition.builder()
            .attribute(attribute)
            .operator(operator)
            .value(value)
            .build())
          .build())
        .build())
      .build();
  }

}
