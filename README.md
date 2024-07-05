# ecf_04-07-2024

# Gestion RH - Application JEE JAX-RS

## Introduction

Cette application est une API RESTful développée en utilisant JEE (Jakarta EE) et JAX-RS pour la gestion des ressources humaines. Elle permet de gérer des entités telles que les employés, les départements et les postes de travail.

[Sujet de l'exercice'](https://github.com/Florian00000/ecf_04-07-2024/blob/main/ecf-jee-spring.pdf)

## Fonctionnalités

- Ajouter, mettre à jour, supprimer et consulter des employés.
- Ajouter, supprimer et consulter des départements.
- Ajouter, supprimer et consulter des postes de travail.

## Prérequis

- JDK 11 ou supérieur
- Maven 3.x
- Un serveur d'application compatible avec Jakarta EE (comme Tomcat)
- Une base de données MySQL du nom de `ecf_04-07-2024`

## Installation

### Cloner le projet

```sh
git clone https://github.com/votre-utilisateur/gestion-rh.git
cd gestion-rh
```

### Configuration de la base de donnée

Modifier le fichier `JEE-JaxRs-GestionRH\src\main\resources\hibernate.properties` pour configurer les paramètres de votre base de données:

### Construction du projet

Charger les dépendances avec Maven

### Déploiement

Déployez le fichier War ou War exploded généré dans votre serveur d'application Jakarta EE

## Endpoints de l'Api

### Employés

- GET /employee : Récupérer tous les employés.
- POST /employee/add : Ajouter un nouvel employé.
- GET /employee/{id} : Récupérer un employé par son ID.
- DELETE /employee/{id} : Supprimer un employé par son ID.
- PATCH /employee/{id} : Mettre à jour un employé.
- GET /employee/lastname={lastname} : Récupérer les employés par nom de famille.
- GET /employee/department={department} : Récupérer les employés par département.
- GET /employee/position={position} : Récupérer les employés par position.

### Départements

- GET /department : Récupérer tous les départements.
- POST /department/add : Ajouter un nouveau département.
- GET /department/{id} : Récupérer un département par son ID.
- DELETE /department/{id} : Supprimer un département par son ID.

### Postes de travail

- GET /position : Récupérer toutes les positions.
- POST /position/add : Ajouter une nouvelle position.
- GET /position/{id} : Récupérer une position par son ID.
- DELETE /position/{id} : Supprimer une position par son ID.

## Exemple de Requêtes

POST BaseURL/JEE_JaxRs_GestionRH_war_exploded/employee/add 

```JSON
{
    "firstName": "John",
    "lastName": "Doe"    
}
```

POST BaseURL/JEE_JaxRs_GestionRH_war_exploded/api/position/add

```JSON
{
    "name": "Informatique",    
}
```

PATCH BaseUrl/JEE_JaxRs_GestionRH_war_exploded/api/employee/4

```JSON
{
    "department": {
        "id": 2
    },
    "position": {
        "id":2
    } 
}
```

## Note de Développement

### Classes Importantes 

- DepartmentController : Gère les endpoints pour les départements.
- EmployeeController : Gère les endpoints pour les employés.
- PositionController : Gère les endpoints pour les postes de travails.
- EmployeeService : Contient la logique métier pour les employés.
- DepartmentService : Contient la logique métier pour les départements.
- PositionService : Contient la logique métier pour les postes de travail.

### Entités 

- Employee : Représente un employé avec des relations vers les postes de travails et les départements.
- Department : Représente un département avec une liste d'employés.
- Position : Représente un poste de travail avec une liste d'employés.