package it.contactlab.hub.sdk.java.test.integration;

import it.contactlab.hub.sdk.java.ContactHub
import it.contactlab.hub.sdk.java.Auth
import it.contactlab.hub.sdk.java.models._
import it.contactlab.hub.sdk.java.queries._

import java.time._
import java.util.Arrays
import java.util.Optional

import org.scalatest.FeatureSpec
import org.scalatest.Matchers._
import org.scalatest.GivenWhenThen

import scala.collection.JavaConversions._

class QuerySpec extends FeatureSpec with GivenWhenThen {

  val auth = new Auth(
    sys.env("CONTACTHUB_TEST_TOKEN"),
    sys.env("CONTACTHUB_TEST_WORKSPACE_ID"),
    sys.env("CONTACTHUB_TEST_NODE_ID")
  );

  val ch = new ContactHub(auth)

  val atomicMario = AtomicCondition.builder
    .attribute("base.firstName")
    .operator(Operator.EQUALS)
    .value("Mario")
    .build

  val atomicBetween = AtomicCondition.builder
    .attribute("base.dob")
    .operator(Operator.BETWEEN)
    .value(Arrays.asList(
      OffsetDateTime.parse("1900-01-01T00:00:00Z"),
      OffsetDateTime.parse("2016-01-01T00:00:00Z")
    ))
    .build

  val atomicPicture = AtomicCondition.builder
    .attribute("base.pictureUrl")
    .operator(Operator.IS_NOT_NULL)
    .build

  val compositeMario = CompositeCondition.builder
    .conjunction(ConditionConjunction.and)
    .conditions(java.util.Arrays.asList(atomicMario, atomicBetween))
    .build

  val atomicPage = AtomicCondition.builder
    .attribute("page")
    .operator(Operator.EQUALS)
    .value("http://example.com")
    .build

  feature("createQuery helper") {
    scenario("Using the createQuery helper", Integration) {
      Given("I get a QueryContainer using the createQuery helper")
      val query = ch.createQuery("base.firstName", Operator.EQUALS, "Mario")

      When("I filter customers with that query")
      val customers = ch.getCustomers(GetCustomersOptions.builder.query(query).build)

      Then("I get customers matching the condition")
      customers.elements.length should be > 0
      customers.elements.head.base.get.firstName.get shouldBe "Mario"
    }
  }

  feature("Custom Queries") {
    scenario("SimpleQuery with one AtomicCondition", Integration) {
      Given("A simple (LOL) query")
      val query = QueryContainer.builder
        .name("foo")
        .query(SimpleQuery.builder
          .are(ConditionContainer.builder.condition(atomicMario).build)
          .build)
        .build

      When("I filter customers with that query")
      val customers = ch.getCustomers(GetCustomersOptions.builder.query(query).build)

      Then("I get customers matching the condition")
      customers.elements.length should be > 0
      customers.elements.head.base.get.firstName.get shouldBe "Mario"
    }

    scenario("SimpleQuery with a CompositeCondition", Integration) {
      Given("A simple (ROTFL) query with one CompositeCondition")
      val query = QueryContainer.builder
        .name("foo")
        .query(SimpleQuery.builder
          .are(ConditionContainer.builder.condition(compositeMario).build)
          .build)
        .build

      When("I filter customers with that query")
      val customers = ch.getCustomers(GetCustomersOptions.builder.query(query).build)

      Then("I get some customers")
      customers.elements.length should be > 0
    }

    scenario("CombinedQuery", Integration) {
      Given("An insanely complex CombinedQuery")
      val query = QueryContainer.builder
        .query(CombinedQuery.builder
          .conjunction(QueryConjunction.INTERSECT)
          .addQueries(SimpleQuery.builder
            .are(ConditionContainer.builder.condition(atomicMario).build).build)
          .addQueries(SimpleQuery.builder
            .are(ConditionContainer.builder.condition(atomicBetween).build).build)
          .addQueries(CombinedQuery.builder
            .conjunction(QueryConjunction.UNION)
            .addQueries(SimpleQuery.builder
              .are(ConditionContainer.builder.condition(atomicPicture).build)
              .build)
            .build)
          .build)
        .build

      When("I filter customers with that query")
      val customers = ch.getCustomers(GetCustomersOptions.builder.query(query).build)

      Then("I get some customers")
      customers.elements.length should be > 0
    }
  }

}
