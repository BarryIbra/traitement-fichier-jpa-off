package com.barryibrahima.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
/**
 * Classe Categorie
 * @Entity : permet de définir une entité JPA
 * @Table : permet de spécifier le nom de la table en base de données
 */
@Entity
@Table(name = "CATEGORIE")
public class Categorie {

    /**
     * Attributs de la classe Categorie
     * @Id : permet de spécifier la clé primaire
     * @GeneratedValue : permet de spécifier la stratégie de génération de la clé primaire
     * @Column : permet de spécifier les propriétés de la colonne en base de données
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private int id;

    @Column(name = "NOM", nullable = false, unique = true)
    private String nom;
    

    /**
     * Constructeurs de la classe Categorie utilisé par JPA
     */

    public Categorie() {
    }

    /**
     * Constructeur de la classe Categorie utilisé par le programme
     * @param nom : nom de la catégorie
     */

    public Categorie(String nom) {
        this.nom = nom;
    }
    

    public void setNom(String nom) {
        this.nom = nom;
    }
    

    public String getNom() {
        return nom;
    }

    /**
     * Méthode toString de la classe Categorie
     * @return : une chaîne de caractères
     */

    public String toString() {
        return "Categorie [id=" + id + ", nom=" + nom + "]";
    }


}
