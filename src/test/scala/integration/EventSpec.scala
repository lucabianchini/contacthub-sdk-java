package it.contactlab.hub.sdk.java.test.integration

import it.contactlab.hub.sdk.java.ContactHub
import it.contactlab.hub.sdk.java.Auth
import it.contactlab.hub.sdk.java.models._
import it.contactlab.hub.sdk.java.exceptions._

import java.time._
import java.time.temporal.ChronoUnit._

import org.scalacheck.Gen

import org.scalatest.FeatureSpec
import org.scalatest.Inspectors._
import org.scalatest.Matchers._
import org.scalatest.GivenWhenThen
import org.scalatest.TryValues._

import scala.collection.JavaConversions._
import scala.collection.mutable._
import scala.util.Try

class EventSpec extends FeatureSpec with GivenWhenThen {

  val auth = new Auth(
    sys.env("CONTACTHUB_TEST_TOKEN"),
    sys.env("CONTACTHUB_TEST_WORKSPACE_ID"),
    sys.env("CONTACTHUB_TEST_NODE_ID")
  )

  val ch = new ContactHub(auth)

  // FIXME: Given it takes about 30 seconds for new events to be indexed, we
  // rely on some existing events that were added manually to the test workspace
  val customerId = "9d066fe2-0d14-4027-8191-2892c663ad76"
  val eventId = "60717ebd-2819-4d54-9d5e-e269e4db5f04"

  feature("adding a new event") {
    scenario("adding a simple event", Integration) {
      Given("a new event object")
      val event = WebEvent.builder
        .customerId(customerId)
        .`type`(EventType.viewedPage)
        .properties(HashMap(
          "url" -> "https://example.com",
          "title" -> "Page Title"
        ))
        .build

      When("the user adds the event")
      def create = ch.addEvent(event)

      Then("the event is created successfully")
      noException should be thrownBy create

    }

    scenario("adding an event with a sessionId", Integration) {
      Given("a new event object with a sessionId")
      val event = WebEvent.builder
        .sessionId("ses123")
        .`type`(EventType.viewedPage)
        .build

      When("the user adds the event")
      def addEvent = Try(ch.addEvent(event))

      Then("the event is created successfully")
      addEvent should be a 'success
    }

    scenario("adding an event with an externalId", Integration) {
      Given("a new event object with a externalId")
      val event = WebEvent.builder
        .externalId("ext123")
        .`type`(EventType.viewedPage)
        .build

      When("the user adds the event")
      def addEvent = Try(ch.addEvent(event))

      Then("the event is created successfully")
      addEvent should be a 'success
    }

    scenario("adding an event with sessionId and externalId", Integration) {
      Given("a new event object with both a sessionId and a externalId")
      val event = WebEvent.builder
        .sessionId("ses123")
        .externalId("ext123")
        .`type`(EventType.viewedPage)
        .build

      When("the user adds the event")
      def addEvent = Try(ch.addEvent(event))

      Then("the event is created successfully")
      addEvent should be a 'success

      And("the sessionId is ignored (externalId has precedence)")
      // FIXME: it's not trivial to test this condition
    }

    scenario("adding a retail event with a store type", Integration) {
      Given("a new event object")
      val event = RetailEvent.builder
        .customerId(customerId)
        .`type`(EventType.viewedPage)
        .properties(HashMap(
          "url" -> "https://example.com",
          "title" -> "Page Title"
        ))
        .contextInfo(RetailContextInfo.builder
          .client(Client.builder
            .ip("127.0.0.1")
            .build
          )
          .user(User.builder
            .id("userId")
            .build
          )
          .store(Store.builder
            .id("storeId")
            .name("storeName")
            .`type`(StoreType.FREE_STANDING)
            .street("Via Malaga")
            .city("Milano")
            .country("Italy")
            .province("MI")
            .region("Lombardy")
            .zip("20143")
            .geo(Geo.builder.lat(45.4654).lon(9.1859).build)
            .website("https://www.awesomestore.com")
            .build
          )
          .build
        )
        .build

      When("the user adds the event")
      def create = ch.addEvent(event)

      Then("the event is created successfully")
      noException should be thrownBy create

    }
  }

  feature("retrieving events") {
    scenario("retrieve an event by id", Integration) {
      Given("an existing event id")
      val id = eventId

      When("the user asks for that event")
      val event = ch.getEvent(id)

      Then("the returned object contains the expected fields")
      event.id.get should be (id)
      event.customerId.get should be (customerId)
      event.`type` should be (EventType.viewedPage)
      event.context should be (EventContext.WEB)
      event.date should be (OffsetDateTime.parse("2016-12-29T14:36:49.339Z"))
      event.properties.get("title") should be("The Title")
      event.contextInfo.get.client.get.userAgent.get should be ("testUserAgent")
      event.registeredAt.get shouldBe (OffsetDateTime.parse("2017-03-09T10:34:03.547Z"))
    }

    scenario("retrieve all events for a customer", Integration) {
      Given("a customer with some events")
      val cid = customerId

      When("the user retrieves the events for that customer")
      val events = ch.getEvents(cid).elements

      Then("a List of Events with that customerId should be returned")
      events.length should be > 0
      forAll(events)(_.customerId.get should be (cid))
    }

    scenario("retrieve events filtering by type", Integration) {
      Given("a customer with some events")
      val cid = customerId

      And("an event type filter")
      val eventType = EventType.addedProduct
      val filters = EventFilters.builder.`type`(eventType).build

      When("the user retrieves the events for that customer")
      val events = ch.getEvents(cid, filters).elements

      Then("a List of Events with that type should be returned")
      events.length should be > 0
      forAll(events)(_.`type` should be (eventType))
    }

    scenario("retrieve events filtering by context", Integration) {
      Given("a customer with some events")
      val cid = customerId

      And("an event context filter")
      val context = EventContext.ECOMMERCE
      val filters = EventFilters.builder.context(context).build

      When("the user retrieves the events for that customer")
      val events = ch.getEvents(cid, filters).elements

      Then("a List of Events with that customerId should be returned")
      events.length should be > 0
      forAll(events)(_.context should be (context))
    }

    scenario("retrieve events filtering by mode", Integration) {
      Given("a customer with some events")
      val cid = customerId

      And("an event mode filter")
      val mode = EventMode.PASSIVE
      val filters = EventFilters.builder.mode(mode).build

      When("the user retrieves the events for that customer")
      val events = ch.getEvents(cid, filters).elements

      Then("no events should be returned (no passive events in the test db)")
      events.length should be (0)
    }

    scenario("retrieve events filtering by date", Integration) {
      Given("a customer with some events")
      val cid = customerId

      And("a date range filter")
      val dateFrom = OffsetDateTime.now.minus(7, DAYS)
      val dateTo = OffsetDateTime.now.minus(1, HOURS)
      val filters = EventFilters.builder.dateFrom(dateFrom).dateTo(dateTo).build

      When("the user retrieves the events for that customer")
      val events = ch.getEvents(cid, filters).elements

      Then("a List of Events in that date range should be returned")
      events.length should be > 0
      forAll(events)(_.date.isAfter(dateFrom) should be (true))
      forAll(events)(_.date.isBefore(dateTo) should be (true))
    }

    scenario("retrieve events with multiple filters", Integration) {
      Given("a customer with some events")
      val cid = customerId

      And("multiple event filters")
      val eventType = EventType.addedProduct
      val context = EventContext.ECOMMERCE
      val dateTo = OffsetDateTime.now.minus(1, HOURS)
      val filters = EventFilters.builder
                                .`type`(eventType)
                                .context(context)
                                .dateTo(dateTo)
                                .build

      When("the user retrieves the events for that customer")
      val events = ch.getEvents(cid, filters).elements

      Then("a List of Events with that customerId should be returned")
      events.length should be > 0
      forAll(events)(_.customerId.get should be (cid))
      forAll(events)(_.`type` should be (eventType))
      forAll(events)(_.context should be (context))
      forAll(events)(_.date.isBefore(dateTo) should be (true))
    }
  }

}
