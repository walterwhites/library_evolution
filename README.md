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
- PostgreSQL 9.6
- psql (PostgreSQL) 10.5


## Stucture du projet Maven
Ce projet maven est basé sur une architecture multi-tiers, en voici les différents modules:</br>
business : module contenant la logique métier (couche métier) </br>
consumer : module contenant les dépendances externes et le DAO (couche de persistance) </br>
model : module contenant les entités</br>
webapp : module contenant les vues et ses contrôleurs (couche présentation) </br>
webservice: module contenant le webservice</br>
batch : modules contenant les batchs pour la création de la base de données PostgreSQL


## How to run the App
1) Clone this repo: git clone git@github.com:walterwhites/library_information_system.git


## Diagrams
#### Class diagram

![alt class_diagram](diagrams/class_diagram.png?raw=true "Class diagram")

#### Description
- one Admin WORK at 0 or 1 Library
- one Library can be associated with 0 or many Admin
- one Client HAVE 0 or many Loan
- one Loan can be associated with just 1 Client
- one Loan CONTAINS 1 or many Book
- one Book can be associated with 0 or many Loan
- one Library HAVE 0 or many Book
- one Book is associated with 1 or many Library
