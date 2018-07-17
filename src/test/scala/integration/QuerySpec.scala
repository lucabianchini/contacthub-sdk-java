package it.contactlab.hub.sdk.java.test.integration;

import it.contactlab.hub.sdk.java.ContactHub
import it.contactlab.hub.sdk.java.Auth
import it.contactlab.hub.sdk.java.models._
import it.contactlab.hub.sdk.java.queries._

import java.time._
import java.util.Arrays
import java.util.Optional
import java.net.URI

import org.scalacheck.Gen
import org.scalatest.FeatureSpec
import org.scalatest.Matchers._
import org.scalatest.GivenWhenThen
import org.scalatest.BeforeAndAfter
import org.scalatest.BeforeAndAfterAll

import scala.collection.mutable.ListBuffer
import scala.collection.JavaConversions._

class QuerySpec extends FeatureSpec with GivenWhenThen with BeforeAndAfter with BeforeAndAfterAll with DataGenerators {

  val auth = if (sys.env.get("CONTACTHUB_TEST_API_URL").isDefined) {
    new Auth(
      sys.env("CONTACTHUB_TEST_TOKEN"),
      sys.env("CONTACTHUB_TEST_WORKSPACE_ID"),
      sys.env("CONTACTHUB_TEST_NODE_ID"),
      sys.env("CONTACTHUB_TEST_API_URL")
    )
  } else {
    new Auth(
      sys.env("CONTACTHUB_TEST_TOKEN"),
      sys.env("CONTACTHUB_TEST_WORKSPACE_ID"),
      sys.env("CONTACTHUB_TEST_NODE_ID")
    )
  }

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
      OffsetDateTime.parse("1991-01-01T00:00:00Z")
    ))
    .build

  val atomicPicture = AtomicCondition.builder
    .attribute("base.pictureUrl")
    .operator(Operator.IS_NOT_NULL)
    .build

  val compositeMario = CompositeCondition.builder
    .conjunction(ConditionConjunction.and)
    .conditions(Arrays.asList(atomicMario, atomicBetween))
    .build

  val initialCreatedCustomers = new ListBuffer[Customer]
  val sleepAmountInSecs = 60  
  var tmpCustomer: Customer = _
  var tmpContacts: Contacts = _
  
  override def beforeAll() {
    tmpContacts = genCustomer.sample.get.base.get.contacts.get
    tmpCustomer = Customer.builder
      .base(BaseProperties.builder
        .firstName("Mario")
        .lastName("Mario")
        .dob(LocalDate.parse("1978-01-01"))
        .pictureUrl(new URI("https://www.example.com/its-a-me-Mario.jpg"))
        .contacts(tmpContacts)
        .build)
      .build
    initialCreatedCustomers.append(ch.addCustomer(tmpCustomer))

    tmpContacts = genCustomer.sample.get.base.get.contacts.get
    tmpCustomer = Customer.builder
      .base(BaseProperties.builder
        .firstName("Mario")
        .lastName("Mario")
        .dob(LocalDate.parse("1984-01-01"))
        .contacts(tmpContacts)
        .build)
      .build
    initialCreatedCustomers.append(ch.addCustomer(tmpCustomer))
    
    tmpContacts = genCustomer.sample.get.base.get.contacts.get
    tmpCustomer = Customer.builder
      .base(BaseProperties.builder
        .firstName("Luigi")
        .lastName("Mario")
        .dob(LocalDate.parse("1990-01-01"))
        .pictureUrl(new URI("https://www.example.com/its-a-me-Luigi.jpg"))
        .contacts(tmpContacts)
        .build)
      .build
    initialCreatedCustomers.append(ch.addCustomer(tmpCustomer))
    
    println("[" + Thread.currentThread.getId() + "] Created [" + initialCreatedCustomers.size + "] customers: " + initialCreatedCustomers)
    println("[" + Thread.currentThread.getId() + "] Waiting (" + sleepAmountInSecs + " secs) for Hub to index newly created customers")
    for( a <- 1 to sleepAmountInSecs){
       Thread.sleep(1000l)
    }
    println("[" + Thread.currentThread.getId() + "] back to work")
  }
  
   override def afterAll() {
    for (createdCustomer <- initialCreatedCustomers) {
      println("[" + Thread.currentThread.getId() + "] Deleting customer [" + createdCustomer.id.get + "]")
      ch.deleteCustomer(createdCustomer.id.get)
    }
    initialCreatedCustomers.clear()
  }
  
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
