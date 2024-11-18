package com.barryibrahima.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Classe Ingredient
 * @Entity : permet de définir une entité JPA
 * @Table : permet de spécifier le nom de la table en base de données
 */

@Entity
@Table(name = "INGREDIENT")
public class Ingredient implements IngredientBase {

    /**
     * Attributs de la classe Ingredient
     * @Id : permet de spécifier la clé primaire
     * @GeneratedValue : permet de spécifier la stratégie de génération de la clé primaire
     * @Column : permet de spécifier les propriétés de la colonne en base de données
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private int id;
    @Column(name = "NOM", nullable = false, unique = true )
    private String nom;

    /**
     * Constructeurs de la classe Ingredient utilisé par JPA
     */

    public Ingredient() {
    }

    /**
     * Constructeur de la classe Ingredient utilisé par le programme
     * @param nom : nom de l'ingrédient
     */

    public Ingredient(String nom) {
        this.nom = nom;
    }

    

    public void setNom(String nom) {
        this.nom = nom;
    }
    

    public String getNom() {
        return nom;
    }

    /**
     * Méthode toString de la classe Ingredient
     * @return : une chaîne de caractères
     */

    public String toString() {
        return "Ingredient [id=" + id + ", nom=" + nom + "]";
    }

}
