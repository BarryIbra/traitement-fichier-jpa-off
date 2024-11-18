package com.barryibrahima.connexion;



import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * Classe Connexion
 */

public class Connexion {

    /**
     * Attribut de la classe Connexion
     * EMF : EntityManagerFactory
     */
    public static final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("pu_food");


}
