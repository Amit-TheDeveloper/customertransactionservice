FROM openjdk:8
EXPOSE 9091
ADD /target/customertransactionservice.jar customertransactionservice.jar
ENTRYPOINT ["java","-jar","customertransactionservice.jar"]