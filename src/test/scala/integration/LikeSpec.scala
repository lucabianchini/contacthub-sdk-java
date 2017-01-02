package it.contactlab.hub.sdk.java.sync.test.integration;

import it.contactlab.hub.sdk.java.sync.ContactHub
import it.contactlab.hub.sdk.java.Auth
import it.contactlab.hub.sdk.java.models._, base._
import it.contactlab.hub.sdk.java.exceptions._

import org.scalatest.FeatureSpec
import org.scalatest.Matchers._
import org.scalatest.GivenWhenThen
import org.scalatest.BeforeAndAfter

import scala.collection.JavaConversions._

class LikeSpec extends FeatureSpec with GivenWhenThen with BeforeAndAfter {

  val auth = new Auth(
    "97841617075b4b5f8ea88c30a8d2aec7647b7181df2c483fa78138c8d58aed4d",
    "40b6195f-e4f7-4f95-b10e-75268d850988",
    "854f0791-c120-4e4a-9264-6dd197cb922c"
  )

  val ch = new ContactHub(auth)
  val customerId = "052d669e-c3bc-429e-a1ce-dc50908ea078"

  val like1 = Like.builder.id("like1").name("foo").build
  val like2 = Like.builder.id("like2").name("bar").build

  before {
    ch.patchCustomer(customerId, Customer.builder
      .base(BaseProperties.builder
        .likes(Seq(like1, like2))
        .build)
      .build)
  }

  feature("managing likes") {
    scenario("adding a new like", Integration) {
      Given("a known customer")
      val cid = customerId

      When("I add a new like")
      val newLike = Like.builder.id("like3").name("baz").build
      val updated = ch.addLike(cid, newLike)

      Then("The new like is present")
      updated.base.get.likes.get should contain (newLike)

      And("The old ones are still there")
      updated.base.get.likes.get should contain (like1)
    }

    scenario("updating a like", Integration) {
      Given("a known customer")
      val cid = customerId

      When("I update an existing like")
      val newLike = Like.builder.id("like2").name("baz").build
      val updated = ch.updateLike(cid, newLike)

      Then("The matching like is updated")
      updated.base.get.likes.get should contain (newLike)

      And("The other likes are still there")
      updated.base.get.likes.get should contain (like1)
    }

    scenario("removing a like", Integration) {
      Given("a known customer")
      val cid = customerId

      When("I remove an existing like")
      val updated = ch.removeLike(cid, "like1")

      Then("The matching like is removed")
      updated.base.get.likes.get should not contain (like1)
    }
  }
}