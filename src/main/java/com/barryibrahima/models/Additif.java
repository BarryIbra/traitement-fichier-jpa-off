package com.barryibrahima.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Classe Additif
 * @Entity : permet de définir une entité JPA
 */

@Entity
@Table(name="ADDITIF")
public class Additif {

    /**
     * Attributs de la classe Additif
     */

    /**id: identifiant de l'additif */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private int id;
    /**nom: nom de l'additif */
    @Column(name = "NOM", nullable = false, unique = true)
    private String nom;

    /**
     * Constructeurs de la classe Additif utilisé par JPA
     */

    public Additif() {
    }

    /**
     * Constructeur de la classe Additif utilisé par le programme
     * @param nom : nom de l'additif
     */
    public Additif(String nom) {
        this.nom = nom;
    }

    

    public void setNom(String nom) {
        this.nom = nom;
    }
    

    public String getNom() {
        return nom;
    }

    /**
     * Méthode toString de la classe Additif
     * @return : une chaîne de caractères
     */

    public String toString() {
        return "Additif [id=" + id + ", nom=" + nom + "]";
    }

}
