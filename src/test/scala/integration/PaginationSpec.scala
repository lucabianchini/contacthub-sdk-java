package it.contactlab.hub.sdk.java.test.integration

import it.contactlab.hub.sdk.java.ContactHub
import it.contactlab.hub.sdk.java.Auth
import it.contactlab.hub.sdk.java.models._

import org.scalatest.FeatureSpec
import org.scalatest.Matchers._
import org.scalatest.GivenWhenThen
import org.scalatest.BeforeAndAfter
import org.scalatest.BeforeAndAfterAll

import java.time._
import scala.collection.JavaConversions._
import scala.collection.mutable._

class PaginationSpec extends FeatureSpec with GivenWhenThen with BeforeAndAfter with BeforeAndAfterAll with DataGenerators {

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
  var customerId: String = _
  val initialCreatedCustomers = new ListBuffer[Customer]
  val initialCreatedEvents = new ListBuffer[EventCreated]
  val customersToCreate = 29
  val eventTitle = "The Title"
  val eventUserAgent = "testUserAgent"
  val eventDate = "2016-12-29T14:36:49.339Z"
  val eventsToCreate = 30
  val sleepAmountInSecs = 60
  
  override def beforeAll() {
    val customer = genCustomer.sample.get
    val customerWithEvents = ch.addCustomer(customer)
    initialCreatedCustomers.append(customerWithEvents)
    customerId = customerWithEvents.id.get
    
     val webEvent = WebEvent.builder
        .customerId(customerId)
        .`type`(EventType.viewedPage)
        .date(OffsetDateTime.parse(eventDate))
        .properties(HashMap(
          "title" -> eventTitle
        ))
        .contextInfo(
              WebContextInfo.builder.client(
                Client.builder
                .userAgent(eventUserAgent)
                .ip("127.0.0.1")
                .build
            ).build
        ).build
            
    for (e <- 1 to eventsToCreate) {
      initialCreatedEvents.append(ch.addEvent(webEvent))
    }
    println("[" + Thread.currentThread.getId() + "] Created [" + initialCreatedEvents.size + "] events: " + initialCreatedEvents)
    
    for (a <- 1 to customersToCreate) {
      val customer = genCustomer.sample.get
      initialCreatedCustomers.append(ch.addCustomer(customer))
    }
    
    println("[" + Thread.currentThread.getId() + "] Created [" + initialCreatedCustomers.size + "] customers: " + initialCreatedCustomers)
    
    println("[" + Thread.currentThread.getId() + "] Waiting (" + sleepAmountInSecs + " secs) for Hub to index newly created customers and events")
    for (a <- 1 to sleepAmountInSecs) {
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
  
  feature("Paginating Customers") {
    scenario("Reading pagination attributes") {
      Given("I fetch a list of customers")
      val page0 = ch.getCustomers

      When("I inspect the 'page' property")
      val page = page0.page

      Then("I find some interesting pagination attributes")
      page.number shouldBe 0
      page.size shouldBe 10
      page.totalElements.toInt should be > 0
      page.totalPages.toInt should be > 0
    }

    scenario("Fetching a specific page") {
      Given("I request a specific page")
      val page3 = ch.getCustomers(GetCustomersOptions.builder.page(3).build)

      When("I inspect the 'page' property")
      val page = page3.page

      Then("I find the expected page number")
      page.number shouldBe 3
    }

    scenario("Fetching the next page") {
      Given("I fetch a list of customers")
      val page0 = ch.getCustomers

      When("I request the next page")
      val page1 = page0.nextPage

      Then("I get the second page")
      page1.get.page.number shouldBe 1
    }

    scenario("Fetching the previous page") {
      Given("I fetch a specific page")
      val page3 = ch.getCustomers(GetCustomersOptions.builder.page(3).build)

      When("I request the previous page")
      val page2 = page3.previousPage

      Then("I get the previous page")
      page2.get.page.number shouldBe 2
    }

    scenario("Navigating back and forwards") {
      Given("I fetch a list of customers")
      val page3 = ch.getCustomers(GetCustomersOptions.builder.page(3).build)

      When("I navigate back and forward")
      val page = page3.previousPage.get.nextPage.get.page

      Then("I end back on the same page")
      page.number shouldBe 3
    }

    scenario("Calling nextPage from the last page") {
      Given("I fetch the last page")
      val page0 = ch.getCustomers
      val last = page0.page.totalPages
      val lastPage = ch.getCustomers(GetCustomersOptions.builder.page(last).build)

      When("I try to fetch the next page")
      val next = lastPage.nextPage

      Then("I get an empty Optional")
      next.isPresent shouldBe false
    }

    scenario("Calling previousPage from the first page") {
      Given("I fetch the first page")
      val page0 = ch.getCustomers

      When("I try to fetch the previous page")
      val prev = page0.previousPage

      Then("I get an empty Optional")
      prev.isPresent shouldBe false
    }
  }

  /*
   * We only test one scenario for Events, as the code handling pagination is
   * the same and we don't expect Events to behave differently.
   */
  feature("Paginating Events") {
    scenario("Navigating back and forwards") {
      Given("I fetch a list of events")
      val page3 = ch.getEvents(customerId, EventFilters.builder.page(3).build)

      When("I navigate back and forward")
      val page = page3.previousPage.get.nextPage.get.page

      Then("I end back on the same page")
      page.number shouldBe 3
    }
  }

}
