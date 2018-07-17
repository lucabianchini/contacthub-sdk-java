package it.contactlab.hub.sdk.java.test.integration

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
import scala.collection.mutable.ListBuffer

class TagSpec extends FeatureSpec with GivenWhenThen with BeforeAndAfter with BeforeAndAfterAll with DataGenerators {

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
  val createdCustomers = new ListBuffer[Customer]

  before {
    ch.patchCustomer(customerId, Customer.builder
            .tags(CustomerTags.builder
                    .manual(Set("existing-tag", "another-tag"))
                    .auto(Set("existing-auto-tag"))
                    .build)
            .build)
  }
  
  after {
    for (createdCustomer <- createdCustomers) {
      ch.deleteCustomer(createdCustomer.id.get)
    }
    createdCustomers.clear()
  }

  override def afterAll() {
    ch.deleteCustomer(customerId)
  }
  
  feature("adding a tag") {
    scenario("when the tag is new", Integration) {
      Given("a known customer")
      val cid = customerId

      When("I add a new tag")
      val updated = ch.addTag(cid, "new-tag")

      Then("The new tag is present")
      updated.tags.get.manual should contain ("new-tag")

      And("The old ones are still there")
      updated.tags.get.manual should contain ("existing-tag")
      updated.tags.get.auto should contain ("existing-auto-tag")
    }

    scenario("when the tag is already present", Integration) {
      Given("a known customer")
      val cid = customerId

      When("I try to add an existing tag")
      val updated = ch.addTag(cid, "existing-tag")

      Then("The existing tag is not removed")
      updated.tags.get.manual should contain ("existing-tag")

      And("The old ones are still there")
      updated.tags.get.auto should contain ("existing-auto-tag")
    }

    scenario("when the Customer has no other tags", Integration) {
      Given("a new Customer with no tags")
      val customer = genCustomer.sample.get
      val newCustomer = ch.addCustomer(customer)
      createdCustomers.append(newCustomer)
      val cid = newCustomer.id.get

      When("I add a new tag")
      val updated = ch.addTag(cid, "new-tag")

      Then("The new tag is present")
      updated.tags.get.manual should contain ("new-tag")
    }
  }

  feature("removing a tag") {
    scenario("when the tag is present", Integration) {
      Given("a known customer")
      val cid = customerId

      When("I remove an existing tag")
      val updated = ch.removeTag(cid, "existing-tag")

      Then("The existing tag is no longer present")
      updated.tags.get.manual should not contain ("existing-tag")

      And("The other ones are still there")
      updated.tags.get.manual should contain ("another-tag")
      updated.tags.get.auto should contain ("existing-auto-tag")
    }

    scenario("when the tag is not present", Integration) {
      Given("a known customer")
      val cid = customerId

      When("I try to remove a tag that is not present")
      val updated = ch.removeTag(cid, "non-existing-tag")

      Then("The existing ones are still there")
      updated.tags.get.auto should contain ("existing-auto-tag")
      updated.tags.get.manual should contain ("existing-tag")
      updated.tags.get.manual should contain ("another-tag")
    }

    scenario("when the Customer has no tags", Integration) {
      Given("a new Customer with no tags")
      val customer = genCustomer.sample.get

      val newCustomer = ch.addCustomer(customer)
      createdCustomers.append(newCustomer)
      val cid = newCustomer.id.get
      
      When("I try to remove a tag that is not present")
      val updated = ch.removeTag(cid, "non-existing-tag")

      Then("the Customer tags have not changed")
      updated.tags.isPresent should be (false)
    }
  }
}
