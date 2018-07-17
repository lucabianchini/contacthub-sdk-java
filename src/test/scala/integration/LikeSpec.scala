package it.contactlab.hub.sdk.java.test.integration;

import it.contactlab.hub.sdk.java.ContactHub
import it.contactlab.hub.sdk.java.Auth
import it.contactlab.hub.sdk.java.models._
import it.contactlab.hub.sdk.java.exceptions._

import org.scalatest.FeatureSpec
import org.scalatest.Matchers._
import org.scalatest.GivenWhenThen
import org.scalatest.BeforeAndAfter
import org.scalatest.BeforeAndAfterAll

import scala.collection.JavaConversions._

class LikeSpec extends FeatureSpec with GivenWhenThen with BeforeAndAfter with BeforeAndAfterAll with
    DataGenerators {

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

  val customer = genCustomer.sample.get
  val customerId = ch.addCustomer(customer).id.get

  val like1 = Like.builder.id("like1").name("foo").build
  val like2 = Like.builder.id("like2").name("bar").build

  before {
    ch.patchCustomer(customerId, Customer.builder
      .base(BaseProperties.builder
        .likes(Seq(like1, like2))
        .build)
      .build)
  }

  override def afterAll() {
    ch.deleteCustomer(customerId)
  }
  
  feature("managing likes") {
    scenario("adding a new like", Integration) {
      Given("a known customer")
      val cid = customerId

      When("I add a new like")
      val newLike = Like.builder.id("like3").name("baz").build

      ch.addLike(cid, newLike)

      val updated = ch.getCustomer(cid)

      Then("The new like is present")
      updated.base.get.likes should contain (newLike)

      And("The old ones are still there")
      updated.base.get.likes should contain (like1)
    }

    scenario("updating a like", Integration) {
      Given("a known customer")
      val cid = customerId

      When("I update an existing like")
      val newLike = Like.builder.id("like2").name("baz").build

      ch.updateLike(cid, newLike)

      val updated = ch.getCustomer(cid)

      Then("The matching like is updated")
      updated.base.get.likes should contain (newLike)

      And("The other likes are still there")
      updated.base.get.likes should contain (like1)
    }

    scenario("removing a like", Integration) {
      Given("a known customer")
      val cid = customerId

      When("I remove an existing like")
      ch.removeLike(cid, "like1")

      val updated = ch.getCustomer(cid)

      Then("The matching like is removed")
      updated.base.get.likes should not contain (like1)
    }
  }
}
