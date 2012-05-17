package me.couvreur.java.hellojee.client.entity;

import me.couvreur.java.hellojee.ejb.entity.AddressBook;
import me.couvreur.java.hellojee.ejb.entity.model.Person;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * User: jacques
 * Date: 15.04.12
 * Time: 17:29
 */
public class ClientAddressBookStateless {

    public static void main(String[] args) {
        try {
            Context context = new InitialContext();

            // @see mappedName attribute of @Stateless annotation of AddressBookBean class
            Object bean = context.lookup("ejb/AddressBookStateless");

            System.out.println("************************ INFORMATION SUR LE BEAN OBTENU DU LOOKUP SUR LE CONTEXT ************************");
            System.out.println("************************          (pour s'assurer de la bonne connexion)         ************************");
            System.out.println(bean.toString());
            System.out.println("=========================================================================================================");

            Person bernard = new Person();
            bernard.setName("Bernard");

            AddressBook addressBook = (AddressBook)bean;
            addressBook.addPerson(bernard);
            System.out.println("Voir les logs côté serveur !");
            System.out.println("*********************************************************************************************************");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

}
