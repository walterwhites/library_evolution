# Mettez en oeuvre la SOA pour le nouveau système d’information de la bibliothèque d’une grande ville
Construire une application Java EE permettant la gestion d'emprunt de livres d'une bibliothèque.
https://openclassrooms.com/en/projects/mettez-en-oeuvre-la-soa-pour-le-nouveau-systeme-dinformation-de-la-bibliotheque-dune-grande-ville

## Table of Contents

* [What technologies project uses](#what-technologies-project-uses)
* [Stucture du projet Maven](#stucture-du-projet-maven)
* [How to run the App](#how-to-run-the-app)
* [Diagrams](#diagrams)

## What technologies project uses
- Java jdk: JDK10 version
- Java jre: Java(TM) SE Runtime Environment 18.3
- Apache Maven 3.5.4 
- Apache Tomcat 9.0.12
- psql (PostgreSQL) 10.5
- ElephantSQL (SAAS of postgreSQL)
- CloudFoundry (PAAS which contains ElephantSQL)
- Spring boot (Spring framework to get AutoConfiguration and starters)
- Hibernate (persistence framework)


## Stucture du projet Maven
Ce projet maven est basé sur une architecture multi-tiers, en voici les différents modules:</br>
business : module contenant la logique métier (couche métier) </br>
consumer : module contenant les dépendances externes et le DAO (couche de persistance) </br>
model : module contenant les entités</br>
webapp : module contenant les vues et ses contrôleurs (couche présentation) </br>
webservice: module contenant le webservice</br>
batch : modules contenant les batchs pour la création de la base de données PostgreSQL


## How to run the App, there have 2 choices:
the first choice:
1) Clone this repo: 
```
git clone git@github.com:walterwhites/library.git
```
2) Go inside the project directory
```
cd library/
```
3) Run the maven goals:
```
./mvnw install && ./mvnw spring-boot:run -pl webapp/
```
4) Navigate on your browser to http://localhost:8080/ (or maybe it's one other the port)

the second one:
1) Clone this repo:
```
git clone git@github.com:walterwhites/library.git
```
2) Go inside the project directory
```
cd library/
```
3) Run the maven goal:
```
./mvnw clean package
```
4) copy you webapp .war file on tomcat dir tomcat/:
```
cp -R webapp/target/webapp-0.0.1-SNAPSHOT.war /usr/local/env/tomcat/apache-tomcat-9.0.12/webapps/
```
5) start tomcat server:
```
catalina.sh start
```
6) Navigate on your browser to http://localhost:8080/webapp-0.0.1-SNAPSHOT/index (or maybe it's one other the port)

## Deploy the app manually
1) start tomcat server, running
```startup.sh```
  
2) build your project:
```mvn clean package```

3) Copy war file of the app inside tomcat/webapps dir
```cp webapp/target/warfile.war usr/local/env/tomcat-9/webapps/warfile.war```

4) Navigate on your browser to http://localhost:8080/webapp

## Deploy the app on cloudfoundry
1) this CLI command below reads manifest.yml file which contains all informations
 about the cloud instance, just run
```cf push webapp -p webapp/target/webapp-0.0.1-SNAPSHOT.war -b java_buildpack```
- webapp is the name of the application on cloud foundry
- option b is to specified which buildpack cf will use to build the app,
so we use Java buildpack https://github.com/cloudfoundry/java-buildpack



## Diagrams
#### Class diagram

![alt class_diagram](diagrams/class_diagram.png?raw=true "Class diagram")

#### Description
- one Admin WORK at 0 or 1 Library <b>(it's a many to one relation)</b>
- one Library can be associated with 0 or many Admin <b>(it's a one to many relation)</b>
- one Client HAVE 0 or many Loan  <b>(it's a one to many relation)</b>
- one Loan can be associated with just 1 Client <b>(it's a many to one relation)</b>
- one Loan CONTAINS 1 or many Book <b>(it's a many to many relation)</b>
- one Book can be associated with 0 or many Loan <b>(it's a many to many relation)</b>
- one Library HAVE 0 or many Book <b>(it's a many to many relation)</b>
- one Book is associated with 1 or many Library <b>(it's a many to many relation)</b>
