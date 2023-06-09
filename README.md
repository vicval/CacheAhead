# CacheAhead


sample project to test cache refresh before timeout

when a normal cache expires, the next request is executed and the result of it is stored in the cache and also sent to the client. However, the client will be waiting for this request to be completed. This project aims to fix this problem



# springboot-sample-app-cache-ahead

[//]: # ([![Build Status]&#40;https://travis-ci.org/codecentric/springboot-sample-app.svg?branch=master&#41;]&#40;https://travis-ci.org/vicval/CacheAhead&#41;)

[//]: # ([![Coverage Status]&#40;https://coveralls.io/repos/github/codecentric/springboot-sample-app/badge.svg?branch=master&#41;]&#40;https://coveralls.io/github/codecentric/springboot-sample-app?branch=master&#41;)
[![License](http://img.shields.io/:license-apache-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)

Minimal [Spring Boot](http://projects.spring.io/spring-boot/) sample app.

## Requirements

For building and running the application you need:

[//]: # (- [JDK 17]&#40;http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html&#41;)
- [JDK 17](https://www.oracle.com/java/technologies/downloads/#java17)
- [Maven 3](https://maven.apache.org)

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.vicentevalcarcel.refreshAheadCache.RefreshAheadCacheApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

## Copyright

Released under the Apache License 2.0. See the [LICENSE](https://github.com/codecentric/springboot-sample-app/blob/master/LICENSE) file.