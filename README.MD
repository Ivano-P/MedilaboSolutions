# MedilaboSolutions

L’application MedilaboSolutions permet aux médecins d'identifier plus aisément leurs patients à risque de diabète. \
Elle offre la possibilité d'ajouter des patients, de saisir leurs informations personnelles et de prendre des notes 
sur leur état de santé. \
L’application analyse ces données pour évaluer leur niveau de risque vis-à-vis du diabète.

## Prerequies

- Java 21
- Spring Boot
- Maven
- Lombok
- AssertJ Core
- base de donnée sql
- docker 

## Dependances
- spring-boot-starter-web
- spring-boot-starter-test
- spring cloud gateway 
- spring-boot-starter-security
- spring-boot-starter-validation
- mysql-connector-j
- spring-boot-starter-thymeleaf\
- spring-boot-starter-data-jpa
- spring-boot-starter-parent
- eureka server and client
- Feign 

## Instalation et mise en route
1. cloner le repertoire:\
   git clone https://github.com/Ivano-P/MedilaboSolutions.git

2. naviger vers le projet dans votre répertoire\
   cd medilabosolutions

3. installer les dépendances\
   mvn install

4. configurer les bases de données mysql et NoSQL

5. configurer les variables d'environnement

5. créer l'artifact (pour chaque microservice)
   mvn clean package

6. créer l'image docker pour chaque microservice docker 
   build -t nom-de-limage .

7. lancer le conteneur avec les images 
   docker docker-compose up 


## Usage
Cette application propose une interface utilisateur web simple. Il s'agit d'un prototype simple pour une application de\
suivi de santé visant à prévenir le risque de diabète. \
L’interface utilisateur est sombre et ne permet que l’ajout et la modification des informations. \
Pour la suppression, il faut intervenir directement sur les bases de données.\
Les comptes sont déjà créés in memory et permettent de tester l'application en tant que praticien et en tant qu'organisateur. \
Spring Security permet de sécuriser l'application en exigeant une authentification pour toute requête sollicitant un \
appel vers le backend.

## Architecture.

![schema d'architecture](C:\Users\Kerry\DevProjects\oc-projects\MedilaboSolutions\schemas\schema_architecture_medilabosolutions.png)

Le schéma MVC a été suivi, les endpoints se trouvent donc dans le contrôleur avec la logique métier dans le service.
L’application est composée de six micro-services. \

trois microservices backend :
- ms_gestionPatient
- ms_gestionHistorique
- ms_gestionRisque

\
Deux edge microservices :
- eureka-server
- ms_gateway

\
Un microservice frontend : 
- ms_frontend

\
Le frontend gère l'affichage et sollicite les informations nécessaires auprès des microservices backend, qui eux \
contiennent la logique métier pour répondre aux diverses demandes.

ms_gestionPatient gère la liste des patients et leurs informations personnelles, il est également lié à la base de données SQL.

ms_gestionHistorique gère les notes pour chaque patient et accède à la base de données NoSQL MongoDB.

ms_gestionRisque évalue le niveau de risque de diabète pour chaque patient en utilisant les informations de ms_gestionPatient \
et ms_gestionHistorique.

eureka-server conserve un registre de chaque micro-service et facilite l'accès pour les autres micro-services.

ms_gateway sert de pont entre le frontend et le backend. ms_frontend se connecte uniquement à ms_gateway, et ms_gateway \
route les requêtes vers le micro-service approprié.

## recommandations d’amélioration « Green »
- Consolidation des micro-services ms_gestionpatient et ms_gestionhistorique : leurs fonctions sont étroitement liées \
et gèrent des fonctionnalités très similaires.

- Optimisation de l'algorithme d'évaluation du niveau de risque.

- Minimisation de la redondance des données : nous pouvons réduire les informations présentes dans les bases de \
données SQL et NoSQL, ou, si les informations restent limitées à celles déjà présentes, nous pouvons utiliser \
uniquement une base de données NoSQL.

- Surveillance et mesure : mise en place d'outils de suivi de consommation des ressources des micro-services. 


## les test

Vous pouvez lancer les tests pour chaque microservice avec la commande: 'mvn clean test' .
