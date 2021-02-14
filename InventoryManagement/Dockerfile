FROM java:15

EXPOSE 8889

ADD target/invdoc.jar invdoc.jar

ENTRYPOINT ["java","-jar","invdoc.jar"]