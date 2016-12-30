package it.contactlab.hub.sdk.java.sync.test.integration;

import it.contactlab.hub.sdk.java.sync.ContactHub
import it.contactlab.hub.sdk.java.Auth
import it.contactlab.hub.sdk.java.models._, base._
import it.contactlab.hub.sdk.java.exceptions._

import com.google.gson.JsonObject

import java.time._

import org.scalacheck.Gen

import org.scalatest.FeatureSpec
import org.scalatest.Matchers._
import org.scalatest.GivenWhenThen

import scala.collection.JavaConversions._

class EventSpec extends FeatureSpec with GivenWhenThen {

  val auth = new Auth(
    "97841617075b4b5f8ea88c30a8d2aec7647b7181df2c483fa78138c8d58aed4d",
    "40b6195f-e4f7-4f95-b10e-75268d850988",
    "854f0791-c120-4e4a-9264-6dd197cb922c"
  )

  val ch = new ContactHub(auth)
  val customerId = "4012e67d-72fd-4f84-9039-71cbc5b80078"

  feature("adding a new event") {
    scenario("adding a simple event", Integration) {
      Given("a new event object")
      val props = new JsonObject;
      props.addProperty("url", "https://example.com/")
      props.addProperty("title", "Page Title")
      val event = Event.builder
        .customerId(customerId)
        .`type`("viewedPage")
        .context("WEB")
        .properties(props)
        .build

      When("the user adds the event")
      val success = ch.addEvent(event);

      Then("the event is created successfully")
      success should be (true)
    }
  }

}
