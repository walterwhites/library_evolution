# Mettez en oeuvre la SOA pour le nouveau système d’information de la bibliothèque d’une grande ville
Construire une application Java EE permettant la gestion d'emprunt de livres d'une bibliothèque.

## Table of Contents

This project is a improvment of library project https://github.com/walterwhites/library

* [What technologies project uses](#what-technologies-project-uses)
* [Stucture du projet Maven](#stucture-du-projet-maven)
* [Docker](#docker)
* [Class diagram](#class-diagram)

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
- Sentry (Error tracking Software) https://sentry.io
- Travis (continuous integration) https://travis-ci.com


## Stucture du projet Maven
Ce projet maven est basé sur une architecture multi-tiers, en voici les différents modules:</br>
business : module contenant la logique métier (couche métier) </br>
consumer : module contenant les dépendances externes et le DAO (couche de persistance) </br>
model : module contenant les entités</br>
webapp : module contenant les vues et ses contrôleurs (couche présentation) </br>
webservice: module contenant le webservice</br>
batch : module contenant le batch pour la création de la base de données PostgreSQL
batch_email: un batch qui envoie des mails de relance aux usagers n’ayant pas rendu les livres en fin de période de prêt.

batchs ajoutés:
batch_remind_expiration: batch de rappel des prêts arrivant bientôt à expiration (5 jours ou moins avec la date d’expiration)
batch_notification: Batch d'envoi de notification à lors du retour d'un livre
(Car après le délai de 48h après la notification, la réservation est annulée)

## Docker
1) To build docker image:
		mvn install dockerfile:build
		(by default it create image with a tag 'latest': walterwhites/webservice:latest)

2) To change tag (optionnal):
	docker tag walterwhites/webservice:latest walterwhites/webservice:soap-api

3) To list images:
	$ docker images

4) To delete images:
	$ docker image rm {IMAGE ID} --force

5) To login to Docker CLI:
	$ docker login -u "username" -p "password" docker.io

6) To push image on docker hub:
	$ docker push walterwhites/webservice:latest

7) To run the container:
	$  docker run -p 8081:8081 walterwhites/webservice

8) To pull image on docker hub:
	$ docker pull walterwhites/webservice:latest
	
## Class diagram
![class diagram](class_diagram.png?raw=true "class diagram")