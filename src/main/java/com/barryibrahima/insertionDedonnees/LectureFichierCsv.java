package com.barryibrahima.insertionDedonnees;

import java.io.FileReader;
import java.io.Reader;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import com.barryibrahima.connexion.Connexion;
import com.barryibrahima.models.Additif;
import com.barryibrahima.models.Allergene;
import com.barryibrahima.models.Categorie;
import com.barryibrahima.models.Ingredient;
import com.barryibrahima.models.IngredientErreur;
import com.barryibrahima.models.Marque;
import com.barryibrahima.models.Produit;
import com.barryibrahima.utils.ExtractionIngredients;
import com.barryibrahima.utils.Parse;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

/**
 * Classe LectureFichierCsv
 */
public class LectureFichierCsv {
    public static void main(String[] args) {

        String csvFile = "/home/py/Documents/maven/traitement-fichier-jpa-off/src/main/resources/open-food-facts.csv";

        try (Reader in = new FileReader(csvFile)) {
            // Lecture du fichier CSV en définissant la première ligne comme en-tête
            CSVFormat format = CSVFormat.DEFAULT.builder()
                    .setDelimiter('|') // Spécifie le caractère de séparation
                    .setHeader("categorie", "marque", "nom", "nutritionGradeFr", "ingredients",
                            "energie100g", "graisse100g", "sucres100g", "fibres100g", "proteines100g",
                            "sel100g", "vitA100g", "vitD100g", "vitE100g", "vitK100g", "vitC100g",
                            "vitB1100g", "vitB2100g", "vitPP100g", "vitB6100g", "vitB9100g", "vitB12100g",
                            "calcium100g", "magnesium100g", "iron100g", "fer100g", "betaCarotene100g",
                            "presenceHuilePalme", "allergenes", "additifs") // Utilise la première ligne comme en-tête
                    .setSkipHeaderRecord(true) // Ignore l'en-tête lors de l'itération
                    .build();

            Iterable<CSVRecord> records = format.parse(in);

            EntityManager em = Connexion.EMF.createEntityManager();
            EntityTransaction et = em.getTransaction();

            // Parcours des enregistrements CSV
            for (CSVRecord record : records) {
                String nomProduit = record.get("nom");

                // Vérifiez si le produit est déjà traité
                TypedQuery<Long> produitQuery = em.createQuery(
                        "SELECT COUNT(p) FROM Produit p WHERE p.nom = :nom",
                        Long.class);
                produitQuery.setParameter("nom", nomProduit);
                Long count = produitQuery.getSingleResult();

                if (count > 0) {
                    // Produit déjà traité, passez au suivant
                    continue;
                } else {
                    et.begin();
                    Produit produit = new Produit(nomProduit);

                    // Exemple de création d'une entité liée (Marque)
                    Marque marque = new Marque();
                    marque.setNom(record.get("marque"));
                    produit.setMarque(marque);
                    TypedQuery<Marque> queryM = em.createQuery("SELECT m FROM Marque m WHERE m.nom = :nom",
                            Marque.class);
                    queryM.setParameter("nom", marque.getNom());
                    List<Marque> resultM = queryM.getResultList();
                    if (resultM.size() == 0) {
                        em.persist(marque);
                        produit.setMarque(marque);
                    } else {
                        produit.setMarque(resultM.get(0));
                    }

                    // Exemple de création d'une catégorie
                    Categorie categorie = new Categorie();
                    categorie.setNom(record.get("categorie"));

                    TypedQuery<Categorie> queryCategorie = em.createQuery(
                            "SELECT c FROM Categorie c WHERE c.nom = :nom",
                            Categorie.class);
                    queryCategorie.setParameter("nom", categorie.getNom());
                    List<Categorie> resultCategorie = queryCategorie.getResultList();
                    if (resultCategorie.size() == 0) {
                        em.persist(categorie);
                        produit.setCategorie(categorie);
                    } else {
                        produit.setCategorie(resultCategorie.get(0));
                    }

                    // Liste des ingrédients
                    String ingredientsStr = record.get("ingredients");
                    ExtractionIngredients ex = Parse.parseIngredient(ingredientsStr);
                    // Traiter les ingrédients normaux
                    Parse.RepartitionIngredients(
                            ex.getI(),
                            Ingredient.class,
                            produit::addIngredient,
                            em);

                    // Traiter les ingrédients en erreur
                    Parse.RepartitionIngredients(
                            ex.getiE(),
                            IngredientErreur.class,
                            produit::addIngredientErreur,
                            em);

                    // Liste des allergènes
                    String allergenesStr = record.get("allergenes");
                    List<Allergene> allergenes = Parse.parseAllergene(allergenesStr);
                    for (Allergene allergene : allergenes) {
                        TypedQuery<Allergene> query = em.createQuery("SELECT a FROM Allergene a WHERE a.nom = :nom",
                                Allergene.class);
                        query.setParameter("nom", allergene.getNom());
                        List<Allergene> result = query.getResultList();
                        if (result.size() == 0) {

                            em.persist(allergene);
                            produit.addAllergene(allergene);
                        } else {
                            produit.addAllergene(result.get(0));
                        }

                    }

                    // Liste des additifs
                    String additifsStr = record.get("additifs");
                    List<Additif> additifs = Parse.parseAdditif(additifsStr);
                    for (Additif additif : additifs) {
                        TypedQuery<Additif> query = em.createQuery("SELECT a FROM Additif a WHERE a.nom = :nom",
                                Additif.class);
                        query.setParameter("nom", additif.getNom());
                        List<Additif> result = query.getResultList();
                        if (result.size() == 0) {
                            em.persist(additif);
                            produit.addAdditif(additif);
                        } else {
                            produit.addAdditif(result.get(0));
                        }
                    }

                    // Mapping des valeurs numériques et booléennes
                    produit.setNutritionGradeFr(record.get("nutritionGradeFr").charAt(0));
                    produit.setEnergie(Parse.parseDouble(record.get("energie100g")));
                    produit.setGraisse(Parse.parseDouble(record.get("graisse100g")));
                    produit.setSucres(Parse.parseDouble(record.get("sucres100g")));
                    produit.setFibres(Parse.parseDouble(record.get("fibres100g")));
                    produit.setProteines(Parse.parseDouble(record.get("proteines100g")));
                    produit.setSel(Parse.parseDouble(record.get("sel100g")));
                    produit.setVitA(Parse.parseDouble(record.get("vitA100g")));
                    produit.setVitD(Parse.parseDouble(record.get("vitD100g")));
                    produit.setVitE(Parse.parseDouble(record.get("vitE100g")));
                    produit.setVitK(Parse.parseDouble(record.get("vitK100g")));
                    produit.setVitC(Parse.parseDouble(record.get("vitC100g")));
                    produit.setVitB1(Parse.parseDouble(record.get("vitB1100g")));
                    produit.setVitB2(Parse.parseDouble(record.get("vitB2100g")));
                    produit.setVitPP(Parse.parseDouble(record.get("vitPP100g")));
                    produit.setVitB6(Parse.parseDouble(record.get("vitB6100g")));
                    produit.setVitB9(Parse.parseDouble(record.get("vitB9100g")));
                    produit.setVitB12(Parse.parseDouble(record.get("vitB12100g")));
                    produit.setCalcium(Parse.parseDouble(record.get("calcium100g")));
                    produit.setMagnesium(Parse.parseDouble(record.get("magnesium100g")));
                    produit.setIron(Parse.parseDouble(record.get("iron100g")));
                    produit.setFer(Parse.parseDouble(record.get("fer100g")));
                    produit.setBetaCarotene(Parse.parseDouble(record.get("betaCarotene100g")));
                    produit.setPresenceHuilePalme(Parse.parseBoolean(record.get("presenceHuilePalme")));
                    em.persist(produit);
                    et.commit();
                }

            }

            em.close();
            Connexion.EMF.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
