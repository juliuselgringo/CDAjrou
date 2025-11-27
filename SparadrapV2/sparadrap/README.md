# Sparadrap

Application Swing Java de gestion (mutuelles, clients, prescriptions, achats) — projet d'exemple/TP.

## Description

Ce projet est une application de bureau Java (Swing) qui gère des mutuelles, clients, prescriptions et achats. Le code se trouve sous le package `fr.juliuselgringo.sparadrap`.

Structure importante du dépôt :
- `src/main/java` : code source Java
- `src/main/resources` : fichiers de config (notamment `configDB.properties`)
- `scriptDB` : scripts SQL pour créer et peupler la base de données
- `target` : build Maven (jar généré)

## Prérequis

- Java JDK 17+ (le projet a été testé avec Java 17+ ; les logs montrent Java 24 sur votre machine — ça fonctionne aussi, mais compilez/exécutez avec la même version si possible)
- Maven 3.6+ pour construire le projet
- MySQL (ou un autre SGBD compatible configuré dans `configDB.properties`)
- (Optionnel) IntelliJ IDEA ou un autre IDE pour le développement

## Configuration de la base de données

1. Copiez `src/main/resources/configDB.properties` et mettez à jour les paramètres de connexion (host, port, database, user, password).

Exemple de propriétés (illustratif) :

```
db.url=jdbc:mysql://localhost:3306/sparadrap
$db.user=root
$db.password=secret
```

2. Initialisez la base de données en important les scripts SQL situés dans le dossier `scriptDB` :
- `creationBaseTables.sql` : crée les tables
- `populationSparadrapDb.sql` : insère des données d'exemple

Vous pouvez exécuter ces scripts depuis votre client MySQL (MySQL Workbench, ligne de commande `mysql`, ou via un outil tiers).

Exemple (ligne de commande) :

```powershell
mysql -u root -p < "C:\Path\to\sparadrap\scriptDB\creationBaseTables.sql"
mysql -u root -p < "C:\Path\to\sparadrap\scriptDB\populationSparadrapDb.sql"
```

Adaptez le chemin et l'utilisateur selon votre configuration.

## Compilation et exécution

Depuis la racine du projet (là où se trouve `pom.xml`) :

- Compiler et construire le JAR :

```powershell
mvn clean package
```

- Exécuter le JAR généré :

```powershell
java -jar target\sparadrap-1.0-SNAPSHOT.jar
```

Vous pouvez aussi lancer depuis votre IDE (classe `fr.juliuselgringo.sparadrap.controller.Main`).

## Tests



```powershell
mvn test
```

Les rapports de test sont générés dans `target/surefire-reports`.

## Utilisation

L'application est une interface Swing classique. Les écrans importants se trouvent dans le package `fr.juliuselgringo.sparadrap.view` (ex. `MutualSwing`, `PurchaseSwing`, `ProgramSwing`).




