#!/usr/bin/bash
java -jar ./eureka-server/target/eureka-server-0.0.1-SNAPSHOT.jar
java -jar ./service-feign/target/service-feign-0.0.1-SNAPSHOT.jar
java -jar ./service-hi/target/service-hi-0.0.1-SNAPSHOT.jar
java -jar ./service-zuul/target/service-zuul-0.0.1-SNAPSHOT.jar