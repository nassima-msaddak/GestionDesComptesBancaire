# GestionDesComptesBancaire
Projet JEE _ Gestion des Comptes Bancaire: JPA/Hibernate ,Spring ,Spring MVC, Maven.

Exemple d'application JEE basée sur JPA, Hibernate et Spring. 
Elle permet de gérer des comptes bancaires. 

Elle montre comment :
- Comment créer un projet Spring
- Mapper les entités (Héritage et Associations OneToMany, MenayToOne, ManyToMany)
- Comment mapper l'héritage
- Gérer la persistance des entités avec JPA EntityManager au niveau de la couche DAO.
- Gérer les transactions avec Spring TX au niveau de la couche métier
- Faire l'injection des dépendances
- Tester la couche métier


la couche web de l'application permet :
- de consulter un compte avec ses opérations
- d'effectuer les opérations de versement, de retrait et de virement. 

la couche web utilise Spring MVC et montre comment faire la validation des formulaires et comment gérer la pagination.
