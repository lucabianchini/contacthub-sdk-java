# ContactHUB Java SDK

## Install

#### Maven
```xml
<dependency>
  <groupId>it.contactlab.hub</groupId>
  <artifactId>java-sdk</artifactId>
  <version>LATEST</version>
</dependency>
```

#### Gradle
```
compile 'it.contactlab.hub:java-sdk:+'
```

#### sbt
```scala
libraryDependencies += "it.contactlab.hub" % "java-sdk" % "<LATEST>"
```

## Import

```java
import it.contactlab.hub.sdk.java.sync.ContactHub;
```

We also provide a separate `async` implementation where all the methods return a
Java8 [CompletionStage&lt;T&gt;](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/CompletionStage.html).

If you wish the use the Async api, import from the `async` package instead:

```java
import it.contactlab.hub.sdk.java.async.ContactHub;
```

All the methods documented below are available in both packages.

## Authenticate

Find your token, workspaceId and nodeId in the ContactHub dashboard. Then
instantiate a new ContactHub object like this:

```java
Auth auth = new Auth(token, workspaceId, nodeId);

ContactHub ch = new ContactHub(auth);
```

## Customer API

### getCustomer

Lets you retrieve a customer by its id.

```java
Customer customer = ch.getCustomer('a-valid-customer-id');
```

## Examples

Check the [example](example/) folder for working examples.
