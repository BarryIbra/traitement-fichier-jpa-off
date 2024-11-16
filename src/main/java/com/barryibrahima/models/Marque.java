package com.barryibrahima.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Classe Marque
 * @Entity : permet de définir une entité JPA
 * @Table : permet de spécifier le nom de la table en base de données
 */

@Entity
@Table(name ="MARQUE")
public class Marque {
    /**
     * Attributs de la classe Marque
     * @Id : permet de spécifier la clé primaire
     * @GeneratedValue : permet de spécifier la stratégie de génération de la clé primaire
     * @Column : permet de spécifier les propriétés de la colonne en base de données
     */
    
     /**id: identifiant de la marque */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name="ID")
    private int id;
    /**nom: nom de la marque */
    @Column(name = "NOM",nullable = false,unique = true)
    private String nom;

    /**
     * Constructeurs de la classe Marque utilisé par JPA
     */

    public Marque() {
    }

    /**
     * Constructeur de la classe Marque utilisé par le programme
     * @param nom : nom de la marque
     */
    public Marque(String nom) {
        this.nom = nom;
    }

    /**
     * Méthode toString de la classe Marque
     * @return : une chaîne de caractères
     */

    public String toString() {
        return "Marque [id=" + id + ", nom=" + nom + "]";
    }

}
