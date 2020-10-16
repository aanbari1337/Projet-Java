# Projet-java
Ce projet consiste à développer une application de gestion générique des
processus administratives.
Un processus administrative est composée de plusieurs étapes de traitement
linéaire.
Chaque étape est géré par un employé, et le processus administrative est géré par
une chef de division.
Un chef de division est aussi un employé, un employé peut intervenir dans
plusieurs étapes dans plusieurs processus ou le même processus

Le processus administrative est déclenché par un utilisateur en déposant une
demande et des documents spécifique aux processus.
Chaque étape aboutit à l’une des trois résultats suivants :
1-Acceptation : La demande passe à l’étape suivante ou termine si c’est la
dernière étape
2- Mis à jour : Le citoyen doit déposer d’autres documents pour compléter la
procédure, la procédure reste en attente
3-Refus : La demande reviens à l’étape précédente
4-Rejet : La demande est refusé définitivement.
Pour chaque décision, l’employé doit rédiger un rapport qui justifie sa décision.
Chaque démarche doit être archivé à la fin de son traitement.
Le citoyen doit pouvoir suivre l’avancement de sa demande et ses états.
Le projet est divisé en trois parties :
1-Backoffice : Les fonctionnalités sont les suivantes :
1.1. Ajout, modification, archivage et édition des employés et chefs de
services
1.2. Ajout, , archivage et édition des procédures administratives ainsi que ses
documents
1.3. Ajout, , suppression et édition des étapes des étapes des procédures.
1.4. Affectation des procédures et des étapes aux chef des divisons et aux
employés.
2. Front office : Les fonctionnalités sont les suivantes :
2.1. Le citoyen peut se connecter avec son numéro de CIN et déposer sa
demande en choisissant la procédure et déposer les documents
nécessaires
2.2. Le citoyen peut suivre sa demande et modifier les documents déposés à la
demande de l’employé qui traite sa demande
2.3. Le chef de division accepte la demande de procédure de citoyen et généré
un jeton pour permettre au citoyen le suivi de son dossier
2.4. Le chef de division archive une demande de procédure terminé ou rejeté
2.5. Le chef de division peut consulter l’avancement de tous les demandes de
procédures dans son service et leur avancement
2.6. L’employé consulte les demandes de procédure qui lui sont affectés et les
traitent.
3. Reporting : Partie des statistiques, l’acteur est l’administrateur
3.1. Consultation des statistiques de nombre de demande traités pour chaque
procédure
3.2. Consultation des taux d’acception des demandes de procédures par
procédure et de plusieurs en même temps
3.3. Statistique sur taux de validation d’étapes par employé et de plusieurs en
même temps

3.4. Statistiques sur la moyenne du durée des traitements par procédure ou par
étape ou par employé
Les contraintes techniques imposées sur le projet sont les suivants :
1. La base de donnée du back office en BDR
2. La base de donnée du front office en BD Non SQL (MongoDB ou HBASE)
3. Le back office doit être réalise avec un client lourd ou riche ( SWING,
JAVAFX….)
4. Le reporting est réalisé avec une application bureau.
