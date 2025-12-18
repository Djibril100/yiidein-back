# Yiidein – Backend (API de gestion des rendez-vous)

Projet backend développé dans un cadre amical (entre amis guinéens).  
Yiidein est une application de gestion de rendez-vous visant à faciliter la prise de RDV pour des démarches administratives et des services (passeport, carte d’identité, documents officiels, rendez-vous médicaux, etc.).

Le projet est générique et peut être adapté à tout domaine nécessitant la gestion de rendez-vous.

## Fonctionnalités principales
- Gestion des clients
- Gestion des services (administrations, organismes, établissements)
- Gestion des prestataires
- Prise et suivi des rendez-vous
- API REST documentée avec Swagger
- Données de test injectées au démarrage (CommandLineRunner)

## Architecture
Architecture backend modulaire et claire :
- entities : entités métier (Client, Prestataire, Service, RendezVous, Utilisateur…)
- dtos : objets de transfert de données
- repositories : accès aux données
- services : logique métier
- exceptions : gestion des erreurs
- web / controllers : endpoints REST

## Technologies
- Java
- Spring Boot
- Spring Data JPA
- API REST
- Swagger / OpenAPI
- Architecture modulaire et bonnes pratiques

## Documentation API
La documentation Swagger est accessible après lancement de l’application :

## Contexte
Projet développé dans un cadre amical, avec pour objectif de répondre à un besoin concret en Guinée concernant la gestion et la centralisation des rendez-vous administratifs et de services.

## Auteurs
- Abdoulaye Djibril BARRY  
- Souleymane Diallo
