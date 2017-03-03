# Contacthub Java SDK

[![Latest tag](https://img.shields.io/github/tag/contactlab/contacthub-sdk-java.svg)](https://github.com/contactlab/contacthub-sdk-java/releases/)

## Private repository for beta

Until this library reaches 1.0, it is only available as a private Maven
repository from:

* https://buildo-private-maven.appspot.com/

If you have a valid account for this repository, add it to your build tool
configuration.

### sbt

Add the following lines to your `build.sbt`:

```scala
resolvers += "buildo private maven" at "https://buildo-private-maven.appspot.com/"
credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")
```

Then create a file '~/.ivy2/.credentials' with the following content:

```
realm=
host=buildo-private-maven.appspot.com
user=user
password=pass
```


### Gradle

Add this to your project's `build.gradle`:

```
repositories {
  mavenCentral()
  maven {
    credentials {
      username "$buildoMavenUser"
      password "$buildoMavenPassword"
    }
    url "https://buildo-private-maven.appspot.com"
  }
}
```

Then add your credentials to `~/.gradle/gradle.properties`:

```
buildoMavenUser=user
buildoMavenPassword=pass
```

### Maven

Add this to your project's `pom.xml`:

```xml
  <repositories>
    <repository>
      <id>buildo-private-maven.appspot.com</id>
      <url>https://buildo-private-maven.appspot.com</url>
    </repository>
  </repositories>
```

Then add your credentials to `~/.m2/settings.xml`:

```xml
<settings>
  <servers>
    <server>
      <id>buildo-private-maven.appspot.com</id>
      <configuration>
        <httpHeaders>
          <property>
            <name>Authorization</name>
            <!-- Encode the string "user:pass" using https://www.base64encode.org/ -->
            <value>Basic dXNlcjpwYXNz</value>
          </property>
        </httpHeaders>
      </configuration>
    </server>
  </servers>
</settings>
```


## Adding this library to your project dependencies

### sbt

In your `build.sbt`:

```scala
libraryDependencies += "it.contactlab.hub" % "sdk-java" % "0.5.0"
```

### Gradle

In your `build.gradle`:

```
dependencies {
  compile 'it.contactlab.hub:sdk-java:0.5.0
}
```

### Maven

In your `pom.xml`:

```xml
<dependency>
  <groupId>it.contactlab.hub</groupId>
  <artifactId>sdk-java</artifactId>
  <version>0.5.0</version>
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
Boolean success = ch.addCustomerSession(customerId, sessionId);
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
List<Customer> customers = ch.getCustomers();
```

### getCustomerByExternalId

Retrieve all the Customers matching a specific external ID.

```java
List<Customer> customers = ch.getCustomerByExternalId("an-external-id");
```

Please note you can have more than one customer with the same external ID,
unless you include "external ID" in the "matching policies" for your workspace.

### addCustomer

Add a new Customer. This method returns a new Customer object including the ID that was
assigned to the new customer by the API.

```java
Customer newCustomer = ch.addCustomer(Customer customer)
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
Customer updatedCustomer = ch.updateCustomer(Customer newCustomer);
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


## Education API

The following methods are useful ways to add/update/remove the Education objects of a Customer.

### addEducation

```java
Customer updatedCustomer = ch.addEducation(customerId, Education education);
```

### updateEducation

```java
Customer updatedCustomer = ch.updateEducation(customerId, Education education);
```

### removeEducation

```java
Customer updatedCustomer = ch.removeEducation(customerId, String educationId);
```

## Like API

The following methods are useful ways to add/update/remove the Like objects of a Customer.

### addLike

```java
Customer updatedCustomer = ch.addLike(customerId, Like like);
```

### updateLike

```java
Customer updatedCustomer = ch.updateLike(customerId, Like like);
```

### removeLike

```java
Customer updatedCustomer = ch.removeLike(customerId, String likeId);
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

## Event API

### getEvent

Retrieve an event using its ID.

```java
Event event = ch.getEvent("a-valid-event-id");
```

### getEvents

Retrieve all the events for a customer.

```java
List<Event> events = ch.getEvents(customerId);
```

### addEvent

Add a new Event. This method returns `true` if the API has successfully queued
the event for insertion. The API will then process the queue asynchronously, and it
can take a few seconds for an event to actually be stored.

```java
Boolean queued = ch.addEvent(Event event)
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

Althpough this is a Java library, we use [sbt](http://www.scala-sbt.org/) as
the build tool.

`sbt compile` will compile all the Java sources to `target/classes`

`sbt package` will package the compiled files in a JAR file under `target`

`sbt doc` will generate an HTML JavaDoc in `target/api`

`sbt packageDoc` will package the javadoc files in a JAR file under `target`

`sbt test` will run the tests. To run the tests you need a valid ContactHub
account and a test workspace, refer to the notes in the [example](example) to
set the correct environment variables.

### Immutables

This library uses [Immutables](http://immutables.github.io/) to generate
high-quality, immutable implementations of the domain models.

This dramatically reduces the Java boilerplate code. For example, only an
`AbstractCustomer` model is present in the `src` folder. When you compile it,
the `Customer` implementation is automatically generated by Immutables and
saved as `target/classes/.../Customer.java`.
