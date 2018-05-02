# Contacthub Java SDK

[![Build Status](https://travis-ci.org/contactlab/contacthub-sdk-java.svg?branch=master)](https://travis-ci.org/contactlab/contacthub-sdk-java)
[![Latest tag](https://img.shields.io/github/tag/contactlab/contacthub-sdk-java.svg)](https://github.com/contactlab/contacthub-sdk-java/releases/)

## Adding this library to your project dependencies

### sbt

In your `build.sbt`:

```scala
libraryDependencies += "it.contactlab.hub" % "sdk-java" % "1.2.1"
```

### Gradle

In your `build.gradle`:

```
dependencies {
  compile 'it.contactlab.hub:sdk-java:1.2.1
}
```

### Maven

In your `pom.xml`:

```xml
<dependency>
  <groupId>it.contactlab.hub</groupId>
  <artifactId>sdk-java</artifactId>
  <version>1.2.1</version>
</dependency>
```


## Importing this library into your project

```java
import it.contactlab.hub.sdk.java.sync.ContactHub;
```

We also provide a separate `async` implementation where all the methods return a
Java8 [CompletionStage&lt;T&gt;](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/CompletionStage.html).

If you wish the use the Async API, import from the `async` package instead:

```java
import it.contactlab.hub.sdk.java.async.ContactHub;
```

All the following methods are available in both packages.


## Authenticating

Find your token, workspaceId and nodeId in the Contacthub UI. Then
instantiate a new Contacthub object as follows:

```java
Auth auth = new Auth(token, workspaceId, nodeId);

ContactHub ch = new ContactHub(auth);
```

### Custom API url

If you want to use a non-standard baseUrl for the API (for example to connect to
a staging environment), you can add the API base URL as an optional parameter:


```java
Auth auth = new Auth(token, workspaceId, nodeId, apiUrl);
```

If not specified, the SDK will use the default URL for the Contacthub API:
https://api.contactlab.it/hub/v1

## Pagination

When the API returns a paginated set of results, the SDK will return an instance
of `Paginated<T>`. This class has the following methods:

* `elements()`: returns a `List<T>` containing the elements of the current page
* `page()`: returns an instance of `Page` containing some page metadata (current
  page number, total number of pages, etc.)
* `nextPage()`: sends a new API request and returns a new instance of
  `Paginated<T>` for the next page
* `previousPage()`: sends a new API request and returns a new instance of
  `Paginated<T>` for the previous page

`nextPage()` and `previousPage` wrap the result in an `Optional`. If you try to
call `nextPage()` when you have reached the last page, or `previousPage()` from
the first page, you will get an empty `Optional` instance.

## Session API

### createSessionId

Generate a new sessionId.

```java
String sessionId = ch.createSessionId();
```

### addCustomerSession

Reconcile a sessionId with an existing Customer. Use this if you want to
associate anonymous events (containing a sessionId) with an existing customerId.

```java
ch.addCustomerSession(customerId, sessionId);
```

## Customer API

### getCustomer

Retrieve a customer by their ID.

```java
Customer customer = ch.getCustomer("a-valid-customer-id");
```

### getCustomers

Retrieve all the Customers in a node.

```java
Paginated<Customer> customers = ch.getCustomers();
```

This method returns an instance of `Paginated<T>`.
See [Pagination](#pagination).

### getCustomers with extra options

```java
Paginated<Customer> customers = ch.getCustomers(options);
```

`options` is an instance of `GetCustomersOptions`, which can contain one or more
of the following attributes:

* `externalId`: filters Customers by externalId
* `fields`: a whitelist of Customer properties you are interested in
* `query`: a `QueryContainer` (see [Custom Queries](#custom-queries)).
* `sort`: the field to order the results by
* `direction`: the order of the sorting (`asc` or `desc`)
* `page`: the page to retrieve (defaults to `0`)

This method returns an instance of `Paginated<T>`.
See [Pagination](#pagination).

### getCustomerByExternalId

Retrieve all the Customers matching a specific external ID.

```java
Paginated<Customer> customers = ch.getCustomerByExternalId("an-external-id");
```

This method will return the same result you would get from this more verbose
request:

```java
Paginated<Customer> customers = ch.getCustomers(
    GetCustomersOptions.builder().externalId("an-external-id").build()
)
```

Please note you can have more than one customer with the same external ID,
unless you include "external ID" in the "matching policies" for your workspace.

This method returns an instance of `Paginated<T>`.
See [Pagination](#pagination).

### addCustomer

Add a new Customer. This method returns a new Customer object including the ID that was
assigned to the new customer by the API.

```java
Customer newCustomer = ch.addCustomer(customer)
```

If the "Customer uniqueness" configuration for your workspace is set to "Merge"
and the new customer data matches an existing customer according to the
"matching policies", this method will return the merged Customer data.

To create a `Customer` instance that this method requires as its argument, use the
builder provided by the `Customer` object.

```java
Customer.builder()
        .base(...)
        .extended(...)
        .consents(...)
        // ...
        .build();
```

### deleteCustomer

Delete the Customer with the specified ID.

```java
deleteCustomer("an-existing-id");
```

### updateCustomer

Update an existing Customer by replacing all of their data with the data provided.

```java
Customer updatedCustomer = ch.updateCustomer(newCustomer);
```

This method will fail if the ID of the `newCustomer` does not match an existing
customer.

### patchCustomer

Update an existing Customer (identified by `id`) by merging their existing data
with the data provided.

```java
Customer updatedCustomer = ch.patchCustomer(String id, Customer modifiedCustomer);
```

Customer properties that were already set and are included in the new data, will
be overwritten.

Customer properties that were already set, but are not included, will be left
untouched.

For example, if you want to update the email of a customer and nothing else, you
can use:

```java
Customer patchCustomer = Customer.builder()
                         .base(BaseProperties.builder()
                               .contacts(Contacts.builder()
                                         .email(newEmail)
                                         .build())
                               .build())
                         .build();

Customer updatedCustomer = ch.patchCustomer(customerId, patchCustomer);
```

### How to use the Customer class

When you **create** a new `Customer`, you have to set at least one between
`base`, `extended` and `externalId` using `Customer.builder()`. Nested objects
must be created with their own `builder()` methods. Most properties are
optional.

When you **read** a `Customer` from the API, you get an immutable instance of
`Customer` with a few additional properties:

* an `id`
* a `registedAt` property
* an `updatedAt` property

All optional properties are wrapped in an `Optional<T>` to statically check you
cannot run into a NullPointerException.

To **update** a `Customer`, you need to retrieve it and create a modified
immutable copy using the `withX()` methods (e.g. `withExternalId()`).

To **patch** a `Customer`, you create a "patch" instance with the same
`Customer.builder()` you use to create a new `Customer`, but you set only the
fields you want to patch. All the fields that are not explicitly set in the
"patch" object will be left untouched.

Further details about the `Customer` class can be found in Contactlab Developer Site schemas documentation:
https://developer.contactlab.com/documentation/contacthub/schemas/index
or in the Javadoc of this project:
http://javadoc.io/doc/com.contactlab.hub/sdk-java/1.3.0

## Education API

The following methods are useful ways to add/update/remove the Education objects of a Customer.

### addEducation

```java
Customer updatedCustomer = ch.addEducation(customerId, education);
```

### updateEducation

```java
Customer updatedCustomer = ch.updateEducation(customerId, education);
```

### removeEducation

```java
Customer updatedCustomer = ch.removeEducation(customerId, educationId);
```

## Like API

The following methods are useful ways to add/update/remove the Like objects of a Customer.

### addLike

```java
Customer updatedCustomer = ch.addLike(customerId, like);
```

### updateLike

```java
Customer updatedCustomer = ch.updateLike(customerId, like);
```

### removeLike

```java
Customer updatedCustomer = ch.removeLike(customerId, likeId);
```

## Job API

The following methods are useful ways to add/update/remove the Job objects of a Customer.

### addJob

```java
Customer updatedCustomer = ch.addJob(customerId, Job job);
```

### updateJob

```java
Customer updatedCustomer = ch.updateJob(customerId, Job job);
```

### removeJob

```java
Customer updatedCustomer = ch.removeJob(customerId, String jobId);
```

## Tag API

### addTag

Add a tag to a Customer. If the tag is already present, nothing is changed.

```java
Customer updatedCustomer = ch.addTag(customerId, "a-new-tag");
```

### removeTag

Remove a tag from a Customer. If the tag is already present, nothing is changed.

```java
Customer updatedCustomer = ch.addTag(customerId, "a-tag-to-remove");
```

## Custom Queries

Advanced searches and filtering of Customers can be performed using Custom
Queries.

```java
ch.getCustomers(GetCustomersOptions.builder().query(customQuery).build)
```

A Custom Query is an instance of `QueryContainer` that can be generated
in two ways:

### Basic custom queries

```java
QueryContainer customQuery = ch.createQuery(
    "base.firstName", Operator.EQUALS, "Mario"
);
```

```java
QueryContainer customQuery = ch.createQuery(
    "base.pictureUrl", Operator.IS_NOT_NULL)
);
```

### Advanced custom queries

The SDK lets you use the full power of the ContactHub API query language. The
SDK methods closely mirror the structure of the API query format. Refer to the
API documentation for more details.

```java
QueryContainer customQuery = QueryContainer.builder()
  .name("An optional name for your query")
  .query(SimpleQuery.builder()
    .are(ConditionContainer.builder()
      .condition(AtomicCondition.builder()
        .attribute("base.firstName")
        .operator(Operator.EQUALS)
        .value("Mario")
        .build())
      .build())
    .build())
  .build();
```

## Event API

### getEvent

Retrieve an event using its ID.

```java
Event event = ch.getEvent("a-valid-event-id");
```

### getEvents

Retrieve all the events for a customer.

```java
Paginated<Event> events = ch.getEvents(customerId);
```

This method returns an instance of `Paginated<T>`.
See [Pagination](#pagination).

### addEvent

Add a new Event. The API will process the event queue asynchronously, and it can
take a few seconds for an event to be actually stored.

```java
queued = ch.addEvent(event)
```

To create an `Event` instance, use the builder provided by the `Event` object.

```java
Event.builder()
  .customerId(customerId)
  .context(EventContext.WEB)
  .type(EventType.viewedPage)
  .properties(eventProperties)
  .build();
```

`eventProperties` must be of type `Map<String, Object>`. The
structure of this object varies according to the event type, and always contains
a number of optional fields. You should only include the properties that you need.

**Example:**

This will generate a valid `eventProperties` object for a
`viewedPage` event:

```java
HashMap<String, Object> eventProperties = new HashMap<String, Object>();
eventProperties.put("url", "https://example.com/");
eventProperties.put("title", "Page Title");
```

## Examples

See the [example](example) folder for working examples that you can download and
try out.

## Contributing to this library

Although this is a Java library, we use [sbt](http://www.scala-sbt.org/) as
the build tool.

`sbt compile` will compile all the Java sources to `target/classes`.

`sbt package` will package the compiled files in a JAR file under `target`.

`sbt doc` will generate an HTML JavaDoc in `target/api`.

`sbt packageDoc` will package the javadoc files in a JAR file under `target`.

`sbt test` will run the tests. To run the tests you need a valid ContactHub
account and a test workspace, refer to the notes in the [example](example) to
set the correct environment variables.

`sbt publishSigned` and `sbt sonatypeRelease` will publish a new version to
Maven Central. You'll need the required credentials and the setup described
[here](https://www.scala-sbt.org/release/docs/Using-Sonatype.html).

### Immutables

This library uses [Immutables](http://immutables.github.io/) to generate
high-quality, immutable implementations of the domain models.

This dramatically reduces the Java boilerplate code. For example, only an
`AbstractCustomer` model is present in the `src` folder. When you compile it,
the `Customer` implementation is automatically generated by Immutables and
saved as `target/classes/.../Customer.java`.
