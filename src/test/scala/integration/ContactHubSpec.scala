package com.contactlab.hub.test.integration

import org.scalatest.AsyncFeatureSpec
import org.scalatest.Matchers._
import org.scalatest.GivenWhenThen
import scala.compat.java8.FutureConverters._

import com.contactlab.hub.sdk.async.ContactHub
import com.contactlab.hub.Auth

class NodeSpec extends AsyncFeatureSpec with GivenWhenThen {

  implicit def toFuture[A](c: java.util.concurrent.CompletionStage[A]) = c.toScala

  val auth = new Auth(
    "97841617075b4b5f8ea88c30a8d2aec7647b7181df2c483fa78138c8d58aed4d",
    "40b6195f-e4f7-4f95-b10e-75268d850988",
    "854f0791-c120-4e4a-9264-6dd197cb922c"
  )

  val ch = new ContactHub(auth);

  feature("retrieving customers") {
    scenario("retrieving all customers of a node", Integration) {

      Given("a node with 10 customers")

      When("the user asks for all of them")
      ch.getCustomers.map { customers =>

        Then("all 10 users should be retrieved")
        customers shouldNot be (null)
        customers should have length 10
      }
    }

    scenario("retrieving a single customer of a node by id", Integration) {
      Given("a node with 10 customers")

      When("the user asks for one of them by id")
      //TODO
      Then("the user should be retrieved")
      // TODO

      When("the user asks for a user that doesn't exist")
      //TODO
      Then("the user should not be retrieved")
      Then("an error should be thrown")
      // TODO
      succeed
    }


    scenario("retrieving a single customer of a node by external id", Integration) {
      Given("a node with 10 customers")

      When("the user asks for one of them by external id")
      //TODO
      Then("the user should be retrieved")
      // TODO

      When("the user asks for a user that doesn't exist")
      //TODO
      Then("the user should not be retrieved")
      Then("an error should be thrown")
      // TODO
      succeed
    }

  }

}
