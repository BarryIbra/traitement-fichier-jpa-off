package com.barryibrahima.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import com.barryibrahima.models.Additif;
import com.barryibrahima.models.Allergene;
import com.barryibrahima.models.Ingredient;
import com.barryibrahima.models.IngredientBase;
import com.barryibrahima.models.IngredientErreur;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class Parse {

    /**
     * Parse une chaîne contenant des ingrédients en une instance d'ExtractionIngredients.
     * Les ingrédients trop longs (plus de 255 caractères) sont considérés comme des erreurs
     * et ajoutés à une liste d'IngredientErreur.
     *
     * @param data la chaîne contenant les ingrédients, séparés par des virgules ou des points-virgules.
     * @return une instance d'ExtractionIngredients contenant les ingrédients valides et ceux en erreur.
     */
    public static ExtractionIngredients parseIngredient(String data) {
        ExtractionIngredients ex = new ExtractionIngredients();
        if (data != null && !data.isEmpty()) {
            String[] items = data.split("[,;]"); // Séparateurs possibles
            for (String item : items) {
                if (item.length() > 255) {
                    IngredientErreur e = new IngredientErreur();
                    e.setNom(item);
                    ex.addIngredientErreur(e);
                } else {
                    Ingredient ingredient = new Ingredient();
                    ingredient.setNom(item.trim());
                    ex.addIngredient(ingredient);
                }
            }
        }
        return ex;
    }

    /**
     * Gère la répartition des ingrédients en vérifiant leur existence dans la base de données.
     * Si un ingrédient existe, il est récupéré et ajouté au produit. Sinon, il est persisté
     * dans la base de données avant d'être ajouté.
     *
     * @param <T>                le type d'ingrédient (Ingredient ou IngredientErreur).
     * @param ingredients        la liste des ingrédients à traiter.
     * @param classIngredient    la classe des ingrédients (Ingredient.class ou IngredientErreur.class).
     * @param addToProduitFunction une fonction qui ajoute l'ingrédient au produit.
     * @param em                 l'EntityManager pour accéder à la base de données.
     */
    public static <T> void RepartitionIngredients(
            List<T> ingredients,
            Class<T> classIngredient,
            Consumer<T> addToProduitFunction,
            EntityManager em) {
        for (T ingredient : ingredients) {
            TypedQuery<T> query = em.createQuery("SELECT i FROM " + classIngredient.getSimpleName() + " i WHERE i.nom = :nom",
                    classIngredient);
            query.setParameter("nom", ((IngredientBase) ingredient).getNom());
            List<T> result = query.getResultList();
            if (result.isEmpty()) {
                em.persist(ingredient);
                addToProduitFunction.accept(ingredient);
            } else {
                addToProduitFunction.accept(result.get(0));
            }
        }
    }

    /**
     * Parse une chaîne contenant des additifs et retourne une liste d'objets Additif.
     *
     * @param data la chaîne contenant les additifs, séparés par des virgules ou des points-virgules.
     * @return une liste d'objets Additif.
     */
    public static List<Additif> parseAdditif(String data) {
        List<Additif> list = new ArrayList<>();
        if (data != null && !data.isEmpty()) {
            String[] items = data.split("[,;]"); // Séparateurs possibles
            for (String item : items) {
                Additif additif = new Additif();
                additif.setNom(item.trim());
                list.add(additif);
            }
        }
        return list;
    }

    /**
     * Parse une chaîne contenant des allergènes et retourne une liste d'objets Allergene.
     *
     * @param data la chaîne contenant les allergènes, séparés par des virgules ou des points-virgules.
     * @return une liste d'objets Allergene.
     */
    public static List<Allergene> parseAllergene(String data) {
        List<Allergene> list = new ArrayList<>();
        if (data != null && !data.isEmpty()) {
            String[] items = data.split("[,;]"); // Séparateurs possibles
            for (String item : items) {
                Allergene allergene = new Allergene();
                allergene.setNom(item.trim());
                list.add(allergene);
            }
        }
        return list;
    }

    /**
     * Parse une chaîne en un booléen. La chaîne "1" correspond à true et "0" correspond à false.
     * Les autres valeurs ou null retournent null.
     *
     * @param value la chaîne à analyser.
     * @return un objet Boolean représentant la valeur analysée, ou null si la valeur est non reconnue.
     */
    public static Boolean parseBoolean(String value) {
        if (value == null) {
            return null; // Retourne null si la valeur est nulle
        }
        switch (value.trim()) {
            case "1":
                return true; // 1 correspond à true
            case "0":
                return false; // 0 correspond à false
            default:
                return null; // Retourne null pour les valeurs non reconnues
        }
    }

    /**
     * Parse une chaîne en un Double. Retourne null si la chaîne est vide ou invalide.
     *
     * @param value la chaîne à analyser.
     * @return un objet Double représentant la valeur analysée, ou null si la chaîne est invalide.
     */
    public static Double parseDouble(String value) {
        try {
            return value != null && !value.isEmpty() ? Double.parseDouble(value) : null;
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
