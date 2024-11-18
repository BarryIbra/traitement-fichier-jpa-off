package com.barryibrahima.utils;

import java.util.ArrayList;
import java.util.List;

import com.barryibrahima.models.Ingredient;
import com.barryibrahima.models.IngredientErreur;

/**
 * Classe utilitaire qui va permettre la gestion de l'extraction des ingredients
 */

public class ExtractionIngredients {

    /**
     * i: liste qui va contenir les ingredients normaux
     */
    private List<Ingredient> i;
    /**
     * iE: liste qui va contenir les ingredients difficiles à parser
     */
    private List<IngredientErreur>iE;

    /**
     * Constructeur de la classe
     */

    public ExtractionIngredients(){
        i=new ArrayList<Ingredient>();
        iE=new ArrayList<>();
    }

    /**
     * Methode pour ajouter un ingredient à la liste des ingredients normaux
     * @param in
     */

    public void addIngredient(Ingredient in)
    {
        i.add(in);

    }
    /**
     * Methode pour ajouter un ingredient à la liste des ingredients trop long
     * @param iErreur
     */
    
    public void addIngredientErreur(IngredientErreur iErreur){
        iE.add(iErreur);
    }

    public List<Ingredient> getI() {
        return i;
    }

    public List<IngredientErreur> getiE() {
        return iE;
    }

    

}
