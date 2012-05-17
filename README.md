HelloJee
========

Hello World (minimaliste &amp; fonctionnel) sur Java EE – EJB3... Chiche !

http://en.wikipedia.org/wiki/Markdown


# Utilisation

* Installer l'environnement technique (voir "Environnement technique") :
    * JBoss 6
    * Maven 3
    * IntelliJ 11
* Obtenir les sources depuis Github :
    * depuis un terminal : `git clone https://github.com/seiza/HelloJee.git`
	* Copier le fichier `settings-sample.xml` (à la racine de ce projet) dans votre répertoire `~/.m2`
* Si JBoss n'est pas installé dans le répertoire `/usr/local/jboss-6.1.0.Final` modifier la property maven `jboss.home` dans le `pom.xml` racine
* Exécuter `mvn clean package` dans un terminal (copie l'EAR dans le répertoire `/usr/local/jboss-6.1.0.Final/server/default/deploy/` si il existe !)
* Lancer l'instance `default` de JBoss
* Lancer IntelliJ (http://www.jetbrains.com/idea/download/index.html), faire "Ouvrir un projet" et sélectionner le `pom.xml` racine
* Voir la version concernée pour trouver la classe client à lancer (dans IntelliJ, une des classes du package `me.couvreur.java.hellojee.client`)

Pour créer une nouvelle version (après avoir fait un commit des sources) : `mvn release:prepare -Dresume=false`



# Versions

* [0.1 : EJB 3 Session Stateless](https://github.com/seiza/HelloJee#version-01--ejb-3-session-stateless) : : Projet JEE minimaliste avec uniquement un EJB Stateless et un appel depuis une classe Java simple.
* [0.2 : EJB 3 MDB / JMS)](https://github.com/seiza/HelloJee#version-02--message-driven-bean-mdb--jms) : Ajout d'un Message Driven Bean
* [0.3 : EJB 3 JPA / Hibernate](https://github.com/seiza/HelloJee#version-03--ejb-3---jpa-avec-hibernate) : Ajout de la persistance avec EJB 3 - JPA - Hibernate



# Version 0.1 : EJB 3 Session Stateless

* Voir sur Github :
    * La branche [1-ejb3-stateless](https://github.com/seiza/HelloJee/tree/1-ejb3-stateless) pour voir que les sources de cette partie.
    * Le tag [HelloJee-0.1.0](https://github.com/seiza/HelloJee/zipball/HelloJee-0.1.0) pour ne télécharger que les sources de cette partie


## Version 0.1.0

* Voir le tag [HelloJee-0.1.0](https://github.com/seiza/HelloJee/zipball/HelloJee-0.1.0) pour ne télécharger que les sources de cette partie


### Contenu

Le minimum pour mettre en oeuvre un EJB :

* Un EJB session stateless (qui prend un paramètre String retourne une String concaténée)
* Un EAR encapsulant l'EJB à des fins de déploiement
* Un client qui se contente d'appeler l'EJB, de l'appeler et d'écrire le résultat sur la console (aucune autre technologie)

Il n'y a pas encore de JMS / MDB, de persistance ou d'application web.



### Exécution

* Exécuter `mvn clean package` dans un terminal (l'EAR est copié dans le répertoire `/usr/local/jboss-6.1.0.Final/server/default/deploy/`)
* Dans IntelliJ exécuter la classe client `me.couvreur.java.hellojee.client.session.HelloSessionStatelessClient` du module `hellojee-client` : vous devriez voir une trace intéressante ;)

Attention :

* Il va peut être falloir changer le port JNDI dans le fichier `hellojee-client/src/main/resources/jndi.properties` pour que cela fonctionne.



# Version 0.2 : Message Driven Bean (MDB / JMS)

* Voir sur Github :
    * La branche [2-ejb3-mdb-jms](https://github.com/seiza/HelloJee/tree/2-ejb3-mdb-jms) pour voir que les sources de cette partie.
    * Le tag [hellojee-0.2.0](https://github.com/seiza/HelloJee/zipball/hellojee-0.2.0) pour ne télécharger que les sources de cette partie


## Version 0.2.0 

* Voir le tag [hellojee-0.2.0](https://github.com/seiza/HelloJee/zipball/hellojee-0.2.0) pour ne télécharger que les sources de cette partie


### Contenu

Ajout d'un Message Driven Bean (MDB / JMS) :

* Dans le package `me.couvreur.java.hellojee.ejb.mdb`.
* Le Message Producer et le MDB fonctionnent avec une Queue.
* Mais impossible de faire fonctionner le Messager/Queue Receiver ;(
* Mise en place de la livraison via Maven : `mvn mvn release:prepare` et 3 fois 'return' (utiliser les 3 propositions par défaut)


### Remarques

* Attention, dans JBoss, toutes les destinations de type Queue doivent commencer par `queue/` (voir l'annotation `@MessageDriven/@ActivationConfigProperty` dans la classe `me.couvreur.java.hellojee.ejb.mdb.HelloMessageListenerMDB`)
* Attention, dans JBoss, la déclaration des queues se fait dans un fichier `hornetq-jms.xml` (et non pas `jms.xml`) dans le répertoire ressource `META-INF` du module `hellojee-ejb`


### Exécution

* Exécuter `mvn clean package` dans un terminal (l'EAR est copié dans le répertoire `/usr/local/jboss-6.1.0.Final/server/default/deploy/`)
* Dans IntelliJ exécuter la classe client `me.couvreur.java.hellojee.client.mdb.ClientHelloJMSProducer` du module `hellojee-client` : vous devriez voir une trace intéressante dans les logs du serveur ;)

Attention :

* Il va peut être falloir changer le port JNDI dans le fichier `hellojee-client/src/main/resources/jndi.properties` pour que cela fonctionne.




# Version 0.3 : EJB 3 - JPA (avec Hibernate)

* Voir sur Github :
    * La branche [3-ejb3-entity-jpa](https://github.com/seiza/HelloJee/tree/3-ejb3-entity-jpa) pour voir que les sources de cette partie.
    * Le tag [hellojee-0.3.0](https://github.com/seiza/HelloJee/zipball/hellojee-0.3.0) pour ne télécharger que les sources de cette partie


## Release 0.3.0

* Le tag [hellojee-0.3.0](https://github.com/seiza/HelloJee/zipball/hellojee-0.3.0) pour ne télécharger que les sources de cette partie


### Contenu

Ajout de la persistance (JPA / Hibernate) :

* Entity Beans `Person` vers `Address` dans le package `me.couvreur.java.hellojee.ejb.entity.model` du module `hellojee-model` (pour isoler le modèle de la persistance : plus facile à tester).
* Tests unitaires `PersonTest` et `AddressTest` (voir http://ejb3unit.sourceforge.net/Session-Bean.html)
* Relation OneToMany de `Person` vers `Address` (voir l'annotation au dessus de la méthode `Person.getAdresses()` pour la configuration clef).
* L'EJB Session Stateless `AddressBookBean` a été créé pour pouvoir accéder aux Entity Beans


1) Utilisation d'une base de données dans la persistance (JPA / Hibernate) :

* Création d'une branche Git sur le tag `hellojee-0.3`
* [Configuration de la base de données HSQLDB intégrée à JBoss AS](http://agora.2ia.net/mediawiki/index.php?title=HSQLDB#Version_int.C3.A9gr.C3.A9e_.C3.A0_JBoss_Application_Server) pour ajouter le DataSource `HelloJeeDS` :
    * Création du fichiers projet `<hellojee-ejb>/META-INF/hellojee-ds.xml` pour définir le `local-tx-datasource` (plutôt que dans le fichier `JBOSS_HOME/server/default/deploy/hsqldb-ds.xml`)
    * Création du fichiers projet `<hellojee-ejb>/META-INF/hellojee-jboss-beans.xml` pour définir le `application-policy` (plutôt que dans le fichier `JBOSS_HOME/server/default/conf/login-config.xml`)
* Refactoring du fichier `<hellojee-ejb>/META-INF/persistence.xml` (nommage correct du persistence unit et correction de sa configuration)
* Ajout de la configuration suivante au `pom.xml` du module `hellojee-ejb` :

    <!-- Dans la balise project/plugins/plugin[artifactId="maven-ejb-plugin"]/configuration -->
    <archive>
        <manifest>
            <addClasspath>true</addClasspath>
        </manifest>
    </archive>

* Configuration du plugin Maven `hibernate3-maven-plugin` dans le `pom.xml` du module `hellojee-model` afin de pouvoir générer le schéma de base de données
* Correction de la dépendance `hibernate-entitymanager` dans le `pom.xml` du module `hellojee-model` :
    * Upgrade de la version utilisée à `3.4.0.GA` pour supprimer des erreurs au runtime liées à des conflits de version sur les dépendances transitives
    * Changement du `scope` à `provided` également pour éviter des erreurs au runtime liées à des collisions dans les libs utilisées.
* Ajout de la dépendance à `dom4j` dans le `pom.xml` du module `hellojee-ear`.


2) Création d'un EJB Stateless

* Ajout des annotations `@Stateless` sur la classe `AddressBookBean`
* Ajout des annotations `@PersistenceContext(unitName="HelloJeePU")` sur l'attribut membre `AddressBookBean.entityManager` afin de permettre l'injection sans equivoque.


3) Création du client

* Création du package `me.couvreur.java.hellojee.client.entity` dans le module `hellojee-client`
* Création de la classe `ClientAddressBookStateless` dedans pour appeler l'EJB Stateless et lui transmettre l'EJB Entity `Person` créé pour le persister.



### Remarques

* `Person` et `Address` (les objets persistés) doivent implémenter `Serializable` (si utilisés comme Entity Bean et pas seulement serialisés).


### Exécution

* Exécuter `mvn clean package` dans un terminal (l'EAR est copié dans le répertoire `/usr/local/jboss-6.1.0.Final/server/default/deploy/`)
* Dans IntelliJ exécuter la classe client `me.couvreur.java.hellojee.client.session.HelloSessionStatelessClient` du module `hellojee-client` : vous devrier voir une trace interessante ;)

Attention :

* Il va peut être falloir changer le port JNDI dans le fichier `hellojee-client/src/main/resources/jndi.properties` pour que cela fonctionne.




# Environnement technique

* JBoss AS 6
    * J'ai initialement commencé avec JBoss AS 7, mais trop de limitations et de bugs pour débuter
    * Voir [Installer JBoss AS 7 sur Mac](http://www.agora.2ia.net/mediawiki/index.php?title=JBossPLP#JBoss_7_AS_Sous_MAC_OS_X) (la procédure reste la même que pour la v6).
* Maven 3 
    * Les versions récentes de Mac OS X intègre Maven dans sa version 3
    * Voir [Installer Maven3 sur Mac](http://www.agora.2ia.net/mediawiki/index.php?title=Maven3#Mac_OS_X)
* IntelliJ 11 :
    * [Use Maven in Intellij IDEA](https://wiki.openmrs.org/display/docs/Use+Maven+In+Intellij+IDEA)
    * [Maven IDEA Plugin](http://maven.apache.org/plugins/maven-idea-plugin/index.html)

Voir aussi :

* Le paragraphe "Ecrire le projet 'from scratch'" pour plus de détails
* [la page JavaEE sur mon wiki](http://www.agora.2ia.net/mediawiki/index.php?title=JavaEE) pour les détails d'installation.



# Ecrire le projet "from scratch"

## Module racine dans Maven

    mkdir ejb-gwt
    cd ejb-gwt
    gedit pom.xml


## Sous-module EJB

    mvn archetype:create -DgroupId=me.couvreur.java -DartifactId=ejb-jar
    cd ejb-jar/src/main
    mkdir resources
    cd resources
    mkdir META-INF
    cd META-INF
    mate persistence.xml


## Configuration de Maven pour JBoss AS 7 (fichier settings.xml)

Ajouter dans le settings.xml :

    <profiles>
          <profile>
        <id>jboss-public-repository</id>
        <repositories>
          <repository>
            <id>jboss-public-repository-group</id>
            <name>JBoss Public Maven Repository Group</name>
            <url>https://repository.jboss.org/nexus/content/groups/public-jboss/</url>
            <layout>default</layout>
            <releases>
              <enabled>true</enabled>
              <updatePolicy>never</updatePolicy>
            </releases>
            <snapshots>
              <enabled>true</enabled>
              <updatePolicy>never</updatePolicy>
            </snapshots>
          </repository>
        </repositories>
        <pluginRepositories>
          <pluginRepository>
            <id>jboss-public-repository-group</id>
            <name>JBoss Public Maven Repository Group</name>
            <url>https://repository.jboss.org/nexus/content/groups/public-jboss/</url>
            <layout>default</layout>
            <releases>
              <enabled>true</enabled>
              <updatePolicy>never</updatePolicy>
            </releases>
            <snapshots>
              <enabled>true</enabled>
              <updatePolicy>never</updatePolicy>
            </snapshots>
          </pluginRepository>
        </pluginRepositories>
      </profile>
    </profiles>


Vous aurez alors l'erreur suivante :

    [ERROR] Failed to execute goal on project ejb-jar: Could not resolve dependencies for project
    me.couvreur.java.hellojee:ejb-jar:ejb:1.0-SNAPSHOT: Failed to collect dependencies for
    [org.jboss.jbossas:jboss-as-client:pom:6.1.0.Final (provided), junit:junit:jar:4.7 (test)]:
    Failed to read artifact descriptor for trove:trove:jar:2.1.1: Could not transfer artifact
    trove:trove:pom:2.1.1 from/to jboss (http://repository.jboss.org/maven2): Access denied to:
    http://repository.jboss.org/maven2/trove/trove/2.1.1/trove-2.1.1.pom, ReasonPhrase:Forbidden. -> [Help 1]

Ajouter dans le settings.xml :

    <mirrors>
      <mirror>
        <id>deprecated-jboss-repo</id>
        <name>Use new repo to handle requests to deprecated repo</name>
        <mirrorOf>repository.jboss.org</mirrorOf>
        <url>http://repository.jboss.org/nexus/content/repositories/deprecated</url>
      </mirror>
      <mirror>
        <id>deprecated-jboss-repo2</id>
        <name>Use new repo to handle requests to deprecated repo</name>
        <mirrorOf>jboss</mirrorOf>
        <url>http://repository.jboss.org/nexus/content/repositories/deprecated</url>
      </mirror>
    </mirrors>


## Positionner le Module SDK dans IntelliJ pour chaque module

Voir : Capture d’écran 2012-04-15 à 14.18.07.png

Créer le SDK dans IntelliJ : `Project Settings > SDKs > [+]`

Puis le selectionner : `Project Settings > Modules > module-name > Dependencies > Module SDk : 1.6`



## Erreur lors du lookup côté client :

Si vous avez l'erreur :

    Exception in thread "main" java.lang.ClassCastException: javax.naming.Reference cannot be cast to
    me.couvreur.java.hellojee.ejb.session.HelloSession

Alors il faut ajouter la librairie `jbossall-client.jar` (qui se trouve dans `JBOSS_HOME/client`) au projet IntelliJ `hellojee-client`.


## Maven properties

* http://docs.codehaus.org/display/MAVENUSER/MavenPropertiesGuide
* http://www.sonatype.com/books/mvnref-book/reference/resource-filtering-sect-properties.html
