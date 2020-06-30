# kie-client-lab project

This is a sample project with Quarkus that shows how you can consume a remote Kie Server via REST API using the Kie Java Client API. For more details check: [https://karinavarela.me/2020/06/29/consuming-business-assets-via-rest/(opens in a new tab)](https://karinavarela.me/?p=1112)

* This project uses Quarkus, the Supersonic Subatomic Java Framework. If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .
* It is currently using the community version of jBPM, 7.38.0. 
* *Note:* Due to the issue reported in [JBPM-8937](https://issues.redhat.com/browse/JBPM-8937), in this version of the kie java client used on quarkus, we need to exclude the Jackson dependencies in the pom.xml. This should be adjusted on 7.39.0+

## Running the application in dev mode

* Download jBPM and start it. ( https://www.jbpm.org/ )

* You can run your application in dev mode that enables live coding using:

```
./mvnw quarkus:dev
```

## Packaging and running the application

The application can be packaged using `./mvnw package`.
It produces the `kie-client-lab-1.0-SNAPSHOT-runner.jar` file in the `/target` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/lib` directory.

The application is now runnable using `java -jar target/kie-client-lab-1.0-SNAPSHOT-runner.jar`.

## Creating a native executable

You can create a native executable using: `./mvnw package -Pnative`.

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: `./mvnw package -Pnative -Dquarkus.native.container-build=true`.

You can then execute your native executable with: `./target/kie-client-lab-1.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/building-native-image.# kie-client-lab
