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
Ce projet maven est basé sur une architecture multi-tiers, en voici les différents modules:
business : module contenant la logique métier (couche métier)
consumer : module contenant les dépendances externes et le DAO (couche de persistance)
model : module contenant les entités
webapp : module contenant les vues et ses contrôleurs (couche présentation)
webservice: module contenant le webservice
batch : modules contenant les batchs pour la création de la base de données PostgreSQL


## How to run the App
1) Clone this repo: git clone git@github.com:walterwhites/library_information_system.git


## Diagrams
#### Class diagram
#### Use case diagram
