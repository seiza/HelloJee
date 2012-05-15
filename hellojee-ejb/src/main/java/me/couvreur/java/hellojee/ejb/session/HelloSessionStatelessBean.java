package me.couvreur.java.hellojee.ejb.session;

import javax.ejb.Stateless;

/**
 * User: jacques
 * Date: 15.04.12
 * Time: 16:56
 */
@Stateless(mappedName = "ejb/HelloSessionStateless")
public class HelloSessionStatelessBean implements HelloSession {

    public HelloSessionStatelessBean() {
    }

    public String sayHello(String name) {
        System.out.println("***** L'EJB Stateless HelloSessionStatelessBean vient d'être appelé !! *****");
        return "Cher " + name + ", ce message provient de l'EJB Session Stateless cote serveur : c'est une bonne nouvelle ! (me.couvreur.java.hellojee.ejb.session.HelloSessionStatelessBean)";
    }

}
