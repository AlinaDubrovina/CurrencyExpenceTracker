FROM openjdk:17

ADD /target/CurrencyExpenseTrackerMicroservice-1.0.0-SNAPSHOT.jar backend.jar

ENTRYPOINT {"java", "-jar", "backend.jar"}