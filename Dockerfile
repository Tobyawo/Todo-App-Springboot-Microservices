FROM adoptopenjdk/openjdk11:alpine-jre
ADD target/docker-todo-app.jar docker-todo-app.jar
ENTRYPOINT ["java", "-jar", "docker-todo-app.jar"]

