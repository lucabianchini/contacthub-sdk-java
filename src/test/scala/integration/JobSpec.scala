package it.contactlab.hub.sdk.java.test.integration;

import it.contactlab.hub.sdk.java.ContactHub
import it.contactlab.hub.sdk.java.Auth
import it.contactlab.hub.sdk.java.models._, base._
import it.contactlab.hub.sdk.java.exceptions._

import org.scalatest.FeatureSpec
import org.scalatest.Matchers._
import org.scalatest.GivenWhenThen
import org.scalatest.BeforeAndAfter

import scala.collection.JavaConversions._

class JobSpec extends FeatureSpec with GivenWhenThen with BeforeAndAfter {

  val auth = new Auth(
    sys.env("CONTACTHUB_TEST_TOKEN"),
    sys.env("CONTACTHUB_TEST_WORKSPACE_ID"),
    sys.env("CONTACTHUB_TEST_NODE_ID")
  );

  val ch = new ContactHub(auth)
  val customerId = "0529a1dd-1228-414c-b8a0-d62452cf0965"

  val job1 = Job.builder.id("job1").companyName("foo").build
  val job2 = Job.builder.id("job2").companyName("bar").build

  before {
    ch.patchCustomer(customerId, Customer.builder
      .base(BaseProperties.builder
        .jobs(Seq(job1, job2))
        .build)
      .build)
  }

  feature("managing jobs") {
    scenario("adding a new job", Integration) {
      Given("a known customer")
      val cid = customerId

      When("I add a new job")
      val newJob = Job.builder.id("job3").companyName("baz").build

      ch.addJob(cid, newJob)

      val updated = ch.getCustomer(cid)

      Then("The new job is present")
      updated.base.get.jobs.get should contain (newJob)

      And("The old ones are still there")
      updated.base.get.jobs.get should contain (job1)
    }

    scenario("updating a job", Integration) {
      Given("a known customer")
      val cid = customerId

      When("I update an existing job")
      val newJob = Job.builder.id("job2").companyName("baz").build

      ch.updateJob(cid, newJob)

      val updated = ch.getCustomer(cid)

      Then("The matching job is updated")
      updated.base.get.jobs.get should contain (newJob)

      And("The other jobs are still there")
      updated.base.get.jobs.get should contain (job1)
    }

    scenario("removing a job", Integration) {
      Given("a known customer")
      val cid = customerId

      When("I remove an existing job")
      ch.removeJob(cid, "job1")

      val updated = ch.getCustomer(cid)

      Then("The matching job is removed")
      updated.base.get.jobs.get should not contain (job1)
    }
  }
}
