package com.barryibrahima.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Classe IngredientErreur: contient les ingredients qui n'ont pas pu être parser.
 * 
 */

@Entity
@Table(name="INGREDIENT_ERREUR")
public class IngredientErreur implements IngredientBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    @Column(name="NOM",nullable = false, columnDefinition = "TEXT")
    private String nom;

    public IngredientErreur(){

    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    

}
