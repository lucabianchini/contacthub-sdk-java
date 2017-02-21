# Example

This example project demonstrates how to use the Contacthub Java SDK.

### Auth parameters

To run the example, you need a ContactHub account. From the ContactHub web
interface, create an API token for a test workspace, then store the token,
workspace id and node id as environment variables.

**Important:**

Do **NOT** use a production workspace, because running the example will write
example data (fake Customers and Events) to the workspace you are using.

If you use Linux or macOS:

```sh
export CONTACTHUB_TEST_TOKEN="..."
export CONTACTHUB_TEST_WORKSPACE_ID="..."
export CONTACTHUB_TEST_NODE_ID="..."
```

If you use Windows:

```dos
SET "CONTACTHUB_TEST_TOKEN=..."
SET "CONTACTHUB_TEST_WORKSPACE_ID=..."
SET "CONTACTHUB_TEST_NODE_ID=..."
```

### Running the example with sbt

```sh
sbt example/run
```

### Running the example with Gradle

```sh
cd example
gradle run
```

### Running the example with Maven

```sh
cd example
mvn compile
mvn exec:java
```

