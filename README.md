# Projet-4A
Hugo Blasco/Charles Cohen/Jimmy Nguyen/Emmanuelle Tourneroche


Création d'un dépôt Git sur GitHub

    Création des groupes (de 4)

    création d'un compte GitHub

    création d'un nouveau dépôt supermarket-receipt
        avec README.md
        avec .gitignore pour Maven
        avec Licence Apache 2.0
        envoi de l'adresse du dépot par mail à ledoyen.loic@gmail.com

    configuration Git avec clé SSH https://help.github.com/articles/connecting-to-github-with-ssh/

    récupération du dépôt en local (git clone)

Création du projet [OK]

    ajouter un fichier .editorconfig spécifiant (pour tous les fichiers) 
        caractère de fin de ligne : LF (unix)
        encodage: UTF-8
        fichiers doivent finir par une ligne vide
        l'indentation est de 4 espaces
    commiter cet unique fichier avec le message "Setup project layout"
    initialisation avec Maven archetype (utiliser l'archetype org.apache.maven.archetypes:maven-archetype-simple)
    commit de ces changements

Mise en place de l'intégration continue [A finir]

    création d'un compte TravisCI
    ajout du fichier .travis.yml configuré pour un build Maven https://docs.travis-ci.com/user/languages/java/
    commit de ces changements
    attente du premier build --> checker ici
    ajout du badge build dans le fichier README.md
    modification du commit précédent intégrant la modification du fichier README.md

Mise à jour du support des tests [A faire]

    Pour chercher la référence vers une librairie accessible par Maven, utiliser https://mvnrepository.com

    remplacer la dépendance JUnit3 par JUnit5
    ajouter la dépendance vers la librairie d'assertion assertj-core
    Faire les changements nécessaires dans le code de test
        remplacer l'héritage de la classe TestCase par l'annotation @Test
        utiliser l'API d'AssertJ pour la première assertion : Assertions.assertThat(true).isTrue()
    commiter ces changements

Mise en place de la couverture du code [En cours]

    création d'un compte sur https://codecov.io
    Modifier le pom.xml afin de générer un rapport de couverture avec cobertura
    modifier le descripteur Travis pour envoyer ce rapport à CodeCov
    commiter ces changements
    vérifier la prise en compte dans CodeCov
    ajouter du badge CodeCov dans le readme
    modifier le commit précédent en ajoutant du badge de couverture
