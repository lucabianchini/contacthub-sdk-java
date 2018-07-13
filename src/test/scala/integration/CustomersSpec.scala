package it.contactlab.hub.sdk.java.test.integration

import it.contactlab.hub.sdk.java.ContactHub
import it.contactlab.hub.sdk.java.Auth
import it.contactlab.hub.sdk.java.models._
import it.contactlab.hub.sdk.java.exceptions._

import com.google.gson.JsonParser

import java.time._

import org.scalacheck.Gen

import org.scalatest.FeatureSpec
import org.scalatest.Matchers._
import org.scalatest.GivenWhenThen
import org.scalatest.BeforeAndAfter
import org.scalatest.BeforeAndAfterAll

import scala.collection.convert.ImplicitConversions._
import scala.collection.mutable.ListBuffer

class CustomersSpec extends FeatureSpec with GivenWhenThen with BeforeAndAfter with BeforeAndAfterAll with DataGenerators {

  val auth = new Auth(
    sys.env("CONTACTHUB_TEST_TOKEN"),
    sys.env("CONTACTHUB_TEST_WORKSPACE_ID"),
    sys.env("CONTACTHUB_TEST_NODE_ID")
  )

  val ch = new ContactHub(auth)
  var customerId: String = _
  val exampleTag =  "example-tag-" + Gen.alphaStr.sample.get
  val extIdSingle = "test-extIdSingle-" + Gen.alphaStr.sample.get
  val extIdMultiple = "multipleExternalIdTest-" + Gen.alphaStr.sample.get
  val initialCreatedCustomers = new ListBuffer[Customer]
  val createdCustomers = new ListBuffer[Customer]
  val sleepAmountInSecs = 60

  override def beforeAll() {
    // create new customer with a known manual tag
    val customerWithManualTag = genCustomer.sample.get.withTags(CustomerTags.builder.addManual(exampleTag).build)
    val createdCustomerWithManualTag = ch.addCustomer(customerWithManualTag)
    initialCreatedCustomers.append(createdCustomerWithManualTag)
    customerId = createdCustomerWithManualTag.id.get

    // create new customer with a known external id
    val customerWithExternalId = genCustomer.sample.get.withExternalId(extIdSingle)
    val createdCustomerWithExternalId = ch.addCustomer(customerWithExternalId)
    initialCreatedCustomers.append(createdCustomerWithExternalId)
    
    // create 2 new customers with a known external id (must be different than the one used before)
    val customerWithExternalIdFirst = genCustomer.sample.get.withExternalId(extIdMultiple)
    val createdCustomerWithExternalIdFirst = ch.addCustomer(customerWithExternalIdFirst)
    initialCreatedCustomers.append(createdCustomerWithExternalIdFirst)
    
    val customerWithExternalIdSecond = genCustomer.sample.get.withExternalId(extIdMultiple)
    val createdCustomerWithExternalIdSecond = ch.addCustomer(customerWithExternalIdSecond)
    initialCreatedCustomers.append(createdCustomerWithExternalIdSecond)
    
    for (x <- 1 to 10) {
      initialCreatedCustomers.append(ch.addCustomer(genCustomer.sample.get))
    }
    
    println("Created customers: " + initialCreatedCustomers)
    
    println("Waiting (" + sleepAmountInSecs + " secs) for Hub to index newly created customers")
    for( a <- 1 to sleepAmountInSecs){
       print("z")
       Thread.sleep(1000l)
    }
    println("\nback to work")
  }
  
  override def afterAll() {
    for (createdCustomer <- initialCreatedCustomers) {
      println("Deleting customer [" + createdCustomer.id.get + "]")
      ch.deleteCustomer(createdCustomer.id.get)
    }
    initialCreatedCustomers.clear()
  }
  
  after {
    for (createdCustomer <- createdCustomers) {
      ch.deleteCustomer(createdCustomer.id.get)
    }
    createdCustomers.clear()
  }
  
  feature("retrieving customers") {
    scenario("retrieving the first page of customers of a node", Integration) {
      Given("a node")

      When("the user asks for the first page of customers")
      val customers = ch.getCustomers.elements

      Then("all 10 users should be retrieved")
      customers shouldNot be (null)
      customers should have length 10
    }

    scenario("retrieving a single existing customer of a node by id", Integration) {
      Given("a node")

      When("the user asks for a customer by id")
      val customer = ch.getCustomer(customerId)

      Then("the user should be retrieved")
      customer.id.get shouldBe customerId
    }

    scenario("retrieving a single non-existing customer of a node by id", Integration) {
      Given("a node")
      When("the user asks for a user that doesn't exist")
      Then("the user should not be retrieved")
      Then("an error should be thrown")
      an [ApiException] should be thrownBy ch.getCustomer("not-existing")
    }

    scenario("retrieving a single customer by external id", Integration) {
      Given("a node")

      When("the user asks for a customer matching an external id")
      val customers = ch.getCustomerByExternalId(extIdSingle).elements

      Then("the expected user should be retrieved")
      customers should have length 1
      customers.head.externalId.get shouldBe extIdSingle
    }

    scenario("retrieving multiple customers by external id", Integration) {
      Given("a node")

      When("the user asks for multiple customers matching an external id")
      val customers = ch.getCustomerByExternalId(extIdMultiple).elements

      Then("the expected users should be retrieved")
      customers should have length 2
      customers(0).externalId.get shouldBe extIdMultiple
      customers(1).externalId.get shouldBe extIdMultiple
    }

    scenario("retrieving a non-existing customer by external id", Integration) {
      Given("a node")
      When("the user asks for a user that doesn't exist")
      val customers = ch.getCustomerByExternalId("not-existing").elements

      Then("an empty list should be returned")
      customers should have length 0
    }

    scenario("reading top-level properties", Integration) {
      Given("a node")

      When("the user asks for a customer by id")
      val customer = ch.getCustomer(customerId)

      Then("the customer should have the expected id")
      customer.id.get shouldBe customerId

      And("the customer should have the expected tags")
      customer.tags.get.manual.toArray shouldBe Array(exampleTag)
    }

    scenario("specifying a whitelist of fields") {
      Given("a whitelist")
      val options = GetCustomersOptions.builder
                    .fields(Set("base.firstName"))
                    .sort("base.firstName")
                    .direction("asc")
                    .build

      When("the user retrieves a list of customers")
      val customers = ch.getCustomers(options).elements

      Then("the returned objects should not include other fields")
      customers.head.base.get.lastName.isPresent shouldBe false
    }
  }

  feature("adding and deleting customers") {
    scenario("adding a customer to a node and deleting it", Integration) {
      Given("a new customer")
      val customer = genCustomer.sample.get

      When("the user adds a customer")
      val newCustomer = ch.addCustomer(customer)

      Then("a new customer is created")
      val id = newCustomer.id.get
      id shouldNot be (null)

      When("the user deletes the customer")
      def delete = ch.deleteCustomer(id)

      Then("the customer should be deleted")
      noException should be thrownBy delete
    }

    scenario("deleting a non-exiting customer", Integration) {
      Given("a customer id")
      val customerId = "not-existing"
      Given("a node that doesn't contain a customer with such id")

      When("the user tries to delete that customer")

      Then("no customer deleted")
      an [ApiException] should be thrownBy ch.deleteCustomer(customerId)
    }
  }

  feature("updating and patching customers") {
    scenario("updating a customer's email address") {
      Given("a customer previously added")
      val customer = genCustomer.sample.get
      val newCustomer = ch.addCustomer(customer)
      createdCustomers.append(newCustomer)

      When("the user updates the customer")
      val base = newCustomer.base.get
      val contacts = base.contacts.get
      val newEmail = Gen.alphaStr.sample.get + "@example.com"
      val newBase = base.withContacts(contacts.withEmail(newEmail))

      val updatedCustomer = ch.updateCustomer(
        Customer.builder
          .id(newCustomer.id)
          .base(newBase)
          .build
      )

      Then("the customer should be updated")
      ch.getCustomer(newCustomer.id.get) shouldBe updatedCustomer

      Then("the customer's id should not have changed")
      updatedCustomer.id.get shouldBe newCustomer.id.get

      Then("the customer's email should be updated")
      updatedCustomer.base.get.contacts.get.email.get shouldBe newEmail

      Then("the customer's updatedAt date should be updated")
      updatedCustomer.updatedAt.get should be > newCustomer.updatedAt.get
    }

    scenario("updating a non-existing customer") {
      Given("a node")
      When("the user tries to update a user that does not exist")
      val customer = Customer.builder.id("not-existing").build

      Then("the update should fail")
      an [ApiException] should be thrownBy ch.updateCustomer(customer)
    }

    scenario("patching a customer's email address") {
      Given("a customer previously added")
      val customer = genCustomer.sample.get
      val newCustomer = ch.addCustomer(customer)
      createdCustomers.append(newCustomer)
      
      When("the user patches the customer")
      val newEmail = Gen.alphaStr.sample.get + "@example.com"

      val patchCustomer = Customer.builder
        .base(BaseProperties.builder
          .contacts(Contacts.builder.email(newEmail).build)
          .build)
        .build

      val updatedCustomer = ch.patchCustomer(newCustomer.id.get, patchCustomer)

      Then("the customer should be updated")
      ch.getCustomer(newCustomer.id.get) shouldBe updatedCustomer

      Then("the customer's id should not have changed")
      updatedCustomer.id shouldBe newCustomer.id

      Then("the customer's first and last name should not have changed")
      updatedCustomer.base.get.firstName shouldBe newCustomer.base.get.firstName
      updatedCustomer.base.get.lastName shouldBe newCustomer.base.get.lastName

      Then("the customer's email should be updated")
      updatedCustomer.base.get.contacts.get.email.get shouldBe newEmail
      Then("the customer's updatedAt date should be updated")
      updatedCustomer.updatedAt.get should be > newCustomer.updatedAt.get
    }

    scenario("patching a non-existing customer") {
      Given("some customer data")
      val newCustomer = genCustomer.sample.get
      When("the user tries to update a user that does not exist")
      def patch = ch.patchCustomer("non-existing", newCustomer)
      Then("the patch should fail")
      an [ApiException] should be thrownBy patch
    }

    scenario("patching a customer's privacy consents, on a customer that has no existing privacy consent") {
      Given("a customer previously added, without any privacy consent")
      val customer = genCustomer.sample.get
      val newCustomer = ch.addCustomer(customer)
      createdCustomers.append(newCustomer)
      
      When("the user patches the customer")
      val consents = Consents.builder
        .thirdPartyTransfer(
          ThirdPartyTransfer.builder.profiling(
            Consent.builder.status(true).limitation(false).objection(false).build
          )
          .build)
        .build

      val patchCustomer = Customer.builder
        .consents(consents)
        .build

      val updatedCustomer = ch.patchCustomer(newCustomer.id.get, patchCustomer)

      Then("the customer's privacy consents should be updated")
      updatedCustomer.consents.get.thirdPartyTransfer.get.profiling.get shouldBe consents.thirdPartyTransfer.get.profiling.get
      Then("the customer's updatedAt date should be updated")
      updatedCustomer.updatedAt.get should be > newCustomer.updatedAt.get
    }

    scenario("patching a customer's privacy consents, on a customer that has existing privacy consents") {
      Given("a customer previously added that has specified some privacy consent")
      val base = BaseProperties.builder
        .firstName("Mario")
        .lastName("Rossi")
        .contacts(Contacts.builder
          .email(Gen.alphaStr.sample.get + "@example.com")
          .build)
        .build

      val consents = Consents.builder
        .thirdPartyTransfer(
          ThirdPartyTransfer.builder.profiling(
            Consent.builder.status(true).limitation(false).objection(false).build
          )
          .build)
        .build

      val customer = Customer.builder
        .base(base)
        .consents(consents)
        .build

      val newCustomer = ch.addCustomer(customer)
      createdCustomers.append(newCustomer)

      When("the user patches the customer")
      val newConsents = Consents.builder
        .softSpam(
          SoftSpam.builder.email(
            Consent.builder.status(true).limitation(true).objection(true).build
          )
          .build)
        .build

      val patchCustomer = Customer.builder
        .consents(newConsents)
        .build

      val updatedCustomer = ch.patchCustomer(newCustomer.id.get, patchCustomer)

      Then("the new privacy consent is added")
      updatedCustomer.consents.get.softSpam.get.email.get shouldBe newConsents.softSpam.get.email.get

      Then("the existing privacy consent is left untouched")
      updatedCustomer.consents.get.thirdPartyTransfer.get.profiling.get shouldBe consents.thirdPartyTransfer.get.profiling.get

      Then("the customer's updatedAt date should be updated")
      updatedCustomer.updatedAt.get should be > newCustomer.updatedAt.get
    }

  }

  feature("creating and reading base properties") {
    scenario("creating a new customer with many base properties") {
      Given("a customer with many props")

      val base = BaseProperties.builder
        .pictureUrl(new java.net.URI("http://example.com/img.png"))
        .title("Mr")
        .prefix("Dr")
        .firstName("Mario")
        .lastName("Rossi")
        .middleName("Giacomo")
        .gender("male")
        .dob(LocalDate.parse("1990-12-12"))
        .locale("it_IT")
        .timezone(ZoneId.of("Europe/Rome"))
        .contacts(Contacts.builder
          .email(Gen.alphaStr.sample.get + "@example.com")
          .fax("123456")
          .mobilePhone("+393331234567")
          .phone("0212345678")
          .otherContacts(Seq(
            OtherContact.builder
              .`type`(OtherContactType.MOBILE).name("work").value("3337654321")
              .build))
          .mobileDevices(Seq(
            MobileDevice.builder
              .`type`(MobileDeviceType.IOS)
              .notificationService(MobileDeviceNotificationService.APN)
              .name("iPhone")
              .identifier("1234")
              .appId("some-app-id")
              .build))
          .build)
        .address(Address.builder
          .street("Via Malaga")
          .city("Milano")
          .country("Italy")
          .province("MI")
          .zip("20143")
          .geo(Geo.builder.lat(45.4654).lon(9.1859).build)
          .build)
        .credential(Credential.builder.username("user").password("pass").build)
        .educations(Seq(
          Education.builder
            .id("edu")
            .schoolType(SchoolType.OTHER)
            .schoolName("Politecnico di Milano")
            .schoolConcentration("Software Engineering")
            .startYear(2000)
            .endYear(2005)
            .isCurrent(false)
            .build))
        .likes(Seq(
          Like.builder
            .id("like1")
            .category("cat1")
            .name("foobar")
            .createdTime(OffsetDateTime.parse("2016-12-01T00:00:00Z"))
            .build))
        .socialProfile(SocialProfile.builder
          .facebook("https://www.facebook.com/ContactLab")
          .twitter("https://twitter.com/ContactLab")
          .build)
        .jobs(Seq(
          Job.builder
            .id("contactlab")
            .companyIndustry("Marketing")
            .companyName("ContactLab")
            .jobTitle("Software Engineer")
            .startDate(LocalDate.parse("2016-09-01"))
            .isCurrent(true)
            .build))
        .subscriptions(Seq(
          Subscription.builder
            .id("sub")
            .name("ContactLab News")
            .`type`("Newsletter")
            .kind(SubscriptionKind.DIGITAL_MESSAGE)
            .subscribed(true)
            .startDate(OffsetDateTime.parse("2016-01-01T00:00:00Z"))
            .endDate(OffsetDateTime.parse("2018-01-01T00:00:00Z"))
            .subscriberId("ASD123")
            .registeredAt(OffsetDateTime.parse("2016-05-10T00:00:00Z"))
            .updatedAt(OffsetDateTime.parse("2016-05-10T00:00:00Z"))
            .preferences(Seq(
              Preference.builder.key("key1").value("value1").build,
              Preference.builder.key("key2").value("value2").build
            ))
            .build))
        .build

      val customer = Customer.builder
        .base(base)
        .build

      When("the user creates and retrieves the customer")
      val newCustomer = ch.addCustomer(customer)
      createdCustomers.append(newCustomer)
      
      val retrievedCustomer = ch.getCustomer(newCustomer.id.get)

      Then("all the properties match")
      retrievedCustomer.base.get shouldBe base
    }
  }

  feature("creating and reading privacy consents") {
    scenario("creating a new customer with privacy consents") {
      Given("a customer with privacy consents")

      val base = BaseProperties.builder
        .firstName("Mario")
        .lastName("Rossi")
        .contacts(Contacts.builder
          .email(Gen.alphaStr.sample.get + "@example.com")
          .build)
        .build

      val consents = Consents.builder
        .thirdPartyTransfer(
          ThirdPartyTransfer.builder.profiling(
            Consent.builder.status(true).limitation(false).objection(false).build
          )
          .build)
        .build

      val customer = Customer.builder
        .base(base)
        .consents(consents)
        .build

      When("the user creates and retrieves the customer")
      val newCustomer = ch.addCustomer(customer)
      createdCustomers.append(newCustomer)
      
      val retrievedCustomer = ch.getCustomer(newCustomer.id.get)

      Then("all the consents match")
      retrievedCustomer.consents.get.thirdPartyTransfer.get.profiling.get shouldBe consents.thirdPartyTransfer.get.profiling.get
    }
  }

}
