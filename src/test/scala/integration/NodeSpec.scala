package com.contactlab.hub.test.integration

import org.scalatest.AsyncFeatureSpec
import org.scalatest.Matchers._
import org.scalatest.GivenWhenThen
import scala.compat.java8.FutureConverters._

import com.contactlab.hub.Node

class NodeSpec extends AsyncFeatureSpec with GivenWhenThen {

  implicit def toFuture[A](c: java.util.concurrent.CompletionStage[A]) = c.toScala

  val defaultNode = new Node("854f0791-c120-4e4a-9264-6dd197cb922c", "40b6195f-e4f7-4f95-b10e-75268d850988")

  feature("retrieving customers") {
    scenario("retrieving all customers of a node") {

      Given("a node with 10 customers")
      val node = defaultNode

      When("the user asks for all of them")
      node.getCustomers.map { customers =>

        Then("all 10 users should be retrieved")
        customers shouldNot be (null)
        customers should have length 10
      }
    }

    scenario("retrieving a single customer of a node by id") {
      Given("a node with 10 customers")
      val node = defaultNode

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


    scenario("retrieving a single customer of a node by external id") {
      Given("a node with 10 customers")
      val node = defaultNode

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
