package com.barryibrahima.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
/**
 * Classe Allergene
 * @Entity : permet de définir une entité JPA
 * @Table : permet de spécifier le nom de la table en base de données
 */

@Entity
@Table(name = "ALLERGENE")

public class Allergene {

     /**
     * Attributs de la classe Allergene
     * @Id : permet de spécifier la clé primaire
     * @GeneratedValue : permet de spécifier la stratégie de génération de la clé primaire
     * @Column : permet de spécifier les propriétés de la colonne en base de données
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    /**id:Identifiant de l'allergene */
    private int id;
    /**nom: nom de l'allergene */
    @Column(name = "NOM", nullable = false, unique = true)
    private String nom;

    /**
     * Constructeurs de la classe Allergene utilisé par JPA
     */


    public Allergene(){

    }

    /**
     * Constructeur de la classe Allergene utilisé par le programme
     * @param nom : nom de la catégorie
     */


    public Allergene(String nom){
        this.nom=nom;

    }
    
     public void setNom(String nom) {
        this.nom = nom;
    }
    

    public String getNom() {
        return nom;
    }

    /**
     * Méthode toString de la classe Allergene
     * @return : une chaîne de caractères
     */

    @Override
    public String toString() {
        return "Allergene [id=" + id + ", nom=" + nom + "]";
    }

    



}
