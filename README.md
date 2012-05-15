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

Pour créer une nouvelle version : `mvn release:prepare`



# Versions

Versions (tags GitHub) :

* `HelloJee-0.1` : Projet JEE minimaliste avec uniquement un EJB Stateless et un appel depuis une classe Java simple.


# Version 0.1 : EJB 3 Session Stateless

## Version 0.1.1 

### Contenu

Le minimum pour mettre en oeuvre un EJB :

* Un EJB session stateless (qui prend un paramètre String retourne une String concaténée)
* Un EAR encapsulant l'EJB à des fins de déploiement
* Un client qui se contente d'appeler l'EJB, de l'appeler et d'écrire le résultat sur la console (aucune autre technologie)

Il n'y a pas encore de JMS / MDB, de persistance ou d'application web.

En dehors des 4 fichiers `pom.xml` (le racine, et celui de chacun des 3 modules EJB, EAR et client), ce projet contient 3 classes Java et 1 fichier de configuration. Et l'EAR fait 5 ko.


### Exécution

* Exécuter `mvn clean package` dans un terminal (l'EAR est copié dans le répertoire `/usr/local/jboss-6.1.0.Final/server/default/deploy/`)
* Dans IntelliJ exécuter la classe client `me.couvreur.java.hellojee.client.session.HelloSessionStatelessClient` du module `jeetuto-client` : vous devrier voir une trace interessante ;)

Attention :

* Il va peut être falloir changer le port JNDI dans le fichier `jeetuto-swing/src/main/resources/jndi.properties` pour que cela fonctionne.



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
    me.couvreur.java.jeetuto:ejb-jar:ejb:1.0-SNAPSHOT: Failed to collect dependencies for
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
    me.couvreur.java.jeetuto.ejb.session.HelloSession

Alors il faut ajouter la librairie `jbossall-client.jar` (qui se trouve dans `JBOSS_HOME/client`) au projet IntelliJ `jeetuto-client`.


## Maven properties

* http://docs.codehaus.org/display/MAVENUSER/MavenPropertiesGuide
* http://www.sonatype.com/books/mvnref-book/reference/resource-filtering-sect-properties.html

