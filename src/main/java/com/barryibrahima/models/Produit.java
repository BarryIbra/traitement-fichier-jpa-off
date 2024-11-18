package com.barryibrahima.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
/**
 * classe Produit
 * @Entity : permet de définir une entité JPA
 * @Table : permet de spécifier le nom de la table en base de données
 */

@Entity
@Table(name="PRODUIT")
public class Produit {
    /**
     * Attributs de la classe Produit
     * @Id : permet de spécifier la clé primaire
     * @Column : permet de spécifier les propriétés de la colonne en base de données
     * @ManyToOne : permet de spécifier la relation ManyToOne
     * @ManyToMany : permet de spécifier la relation ManyToMany
     * @JoinTable : permet de spécifier la table de jointure
     * @JoinColumn : permet de spécifier la colonne de jointure
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private int id;

    /**nom:nom du produit */
    
    @Column(name = "NOM",nullable = false)
    private String nom;

    /**marque: marque du produit */
    
    @ManyToOne
    @JoinColumn(name="ID_MARQUE",nullable = false)
    private Marque marque;

    /**ingredients: liste des ingrédients du produit non null */

    @ManyToMany
    @JoinTable(name="COMPOSITION",joinColumns = @JoinColumn(name="ID_PRODUIT" ,referencedColumnName = "ID"),
    inverseJoinColumns = @JoinColumn(name="ID_INGREDIENT", referencedColumnName = "ID"))
    private List<Ingredient> ingredients;

    @ManyToMany
    @JoinTable(name="COMPOSITION_ERREUR",joinColumns = @JoinColumn(name="ID_PRODUIT" ,referencedColumnName = "ID"),
    inverseJoinColumns = @JoinColumn(name="ID_INGREDIENT_E", referencedColumnName = "ID"))
    private List<IngredientErreur> ingredientsE;


    /**categorie: catégorie du produit  non null*/
    @ManyToOne
    @JoinColumn(name="ID_CATEGORIE",nullable = false)
    private Categorie categorie;

    /**allergenes: liste des allergènes du produit */

    @ManyToMany
    @JoinTable(name="CONTIENT",joinColumns = @JoinColumn(name="ID_PRODUIT" ,referencedColumnName = "ID"),
    inverseJoinColumns = @JoinColumn(name="ID_ALLERGENE", referencedColumnName = "ID"))
    private List<Allergene> allergenes;

    /**additifs: liste des additifs du produit */
    @ManyToMany
    @JoinTable(name="ADDITIF_PRODUIT",joinColumns = @JoinColumn(name="ID_PRODUIT" ,referencedColumnName = "ID"),
    inverseJoinColumns = @JoinColumn(name="ID_ADDITIF", referencedColumnName = "ID"))
    private List<Additif> additifs;

    @Column(name="NUTRITION_GRADE_FR")
    
    private char nutritionGradeFr; // Nutrition grade (a, b, c, etc.)
    @Column(name="ENERGIE")
    private Double energie; // Énergie pour 100g (en kcal)
    @Column(name="GRAISSE")
    private Double graisse; // Quantité de graisse pour 100g (en grammes)
    @Column(name="SUCRES")
    private Double sucres; // Quantité de sucres pour 100g (en grammes)
    @Column(name="FIBRES")
    private Double fibres; // Quantité de fibres pour 100g (en grammes)
    @Column(name="PROTEINES")
    private Double proteines; // Quantité de protéines pour 100g (en grammes)
    @Column(name="SEL")
    private Double sel; // Quantité de sel pour 100g (en grammes)
    @Column(name="VIT_A")
    private Double vitA; // Vitamine A pour 100g (en mg ou µg)
    @Column(name="VIT_D")
    private Double vitD; // Vitamine D pour 100g (en mg ou µg)
    @Column(name="VIT_E")
    private Double vitE; // Vitamine E pour 100g (en mg ou µg)
    @Column(name="VIT_K")
    private Double vitK; // Vitamine K pour 100g (en mg ou µg)
    @Column(name="VIT_C")
    private Double vitC; // Vitamine C pour 100g (en mg ou µg)
    @Column(name="VIT_B1")
    private Double vitB1; // Vitamine B1 pour 100g (en mg ou µg)
    @Column(name="VIT_B2")
    private Double vitB2; // Vitamine B2 pour 100g (en mg ou µg)
    @Column(name="VIT_PP")
    private Double vitPP; // Vitamine PP (Niacine) pour 100g (en mg)
    @Column(name="VIT_B6")
    private Double vitB6; // Vitamine B6 pour 100g (en mg ou µg)
    @Column(name="VIT_B9")
    private Double vitB9; // Vitamine B9 pour 100g (en mg ou µg)
    @Column(name="VIT_B12")
    private Double vitB12; // Vitamine B12 pour 100g (en mg ou µg)
    @Column(name="CALCIUM")
    private Double calcium; // Calcium pour 100g (en mg ou µg)
    @Column(name="MAGNESIUM")
    private Double magnesium; // Magnésium pour 100g (en mg ou µg)
    @Column(name="IRON")
    private Double iron; // Fer pour 100g (en mg)
    @Column(name="FER")
    private Double fer; // Autre champ pour Fer (si utilisé différemment)
    @Column(name="BETA_CAROTENE")
    private Double betaCarotene; // Bêta-Carotène pour 100g (en mg ou µg)
    @Column(name="PRESENCE_HUILE_PALME")
    private Boolean presenceHuilePalme; // Indique la présence d'huile de palme (true/false)


    /**
     * Constructeurs de la classe Produit utilisé par JPA
     */
    public Produit(){
    }

    /**
     * Constructeur de la classe Produit utilisé par le programme
     * @param nom : nom du produit
     */

    public Produit(String nom){
        this.nom=nom;
        this.ingredients=new ArrayList<>();
        this.ingredientsE=new ArrayList<>();
        this.allergenes=new ArrayList<>();
        this.additifs=new ArrayList<>();
    }

    /**
     * Methode pour ajouter un ingrédient à la liste des ingrédients
     * @param ingredient
     */

    public void addIngredient(Ingredient ingredient){
        this.ingredients.add(ingredient);
    }

     /**
     * Methode pour ajouter un ingrédient non parser à la liste des ingrédients
     * @param ingredientErreur
     */

     public void addIngredientErreur(IngredientErreur ingredientErreur){
        this.ingredientsE.add(ingredientErreur);
    }

    /**
     * Methode pour ajouter un allergene à la liste des allergènes
     * @param allergene
     */

    public void addAllergene(Allergene allergene){
        this.allergenes.add(allergene);
    }

    /**
     * Methode pour ajouter un additif à la liste des additifs
     * @param additif
     */

    public void addAdditif(Additif additif){
        this.additifs.add(additif);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Marque getMarque() {
        return marque;
    }

    public void setMarque(Marque marque) {
        this.marque = marque;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public void setNutritionGradeFr(char nutritionGradeFr) {
        this.nutritionGradeFr = nutritionGradeFr;
    }

    public void setEnergie(Double energie) {
        this.energie = energie;
    }

    public void setGraisse(Double graisse) {
        this.graisse = graisse;
    }

    public void setSucres(Double sucres) {
        this.sucres = sucres;
    }

    public void setFibres(Double fibres) {
        this.fibres = fibres;
    }

    public void setProteines(Double proteines) {
        this.proteines = proteines;
    }

    public void setSel(Double sel) {
        this.sel = sel;
    }

    public void setVitA(Double vitA) {
        this.vitA = vitA;
    }

    public void setVitD(Double vitD) {
        this.vitD = vitD;
    }

    public void setVitE(Double vitE) {
        this.vitE = vitE;
    }

    public void setVitK(Double vitK) {
        this.vitK = vitK;
    }

    public void setVitC(Double vitC) {
        this.vitC = vitC;
    }

    public void setVitB1(Double vitB1) {
        this.vitB1 = vitB1;
    }

    public void setVitB2(Double vitB2) {
        this.vitB2 = vitB2;
    }

    public void setVitPP(Double vitPP) {
        this.vitPP = vitPP;
    }

    public void setVitB6(Double vitB6) {
        this.vitB6 = vitB6;
    }

    public void setVitB9(Double vitB9) {
        this.vitB9 = vitB9;
    }

    public void setVitB12(Double vitB12) {
        this.vitB12 = vitB12;
    }

    public void setCalcium(Double calcium) {
        this.calcium = calcium;
    }

    public void setMagnesium(Double magnesium) {
        this.magnesium = magnesium;
    }

    public void setIron(Double iron) {
        this.iron = iron;
    }

    public void setFer(Double fer) {
        this.fer = fer;
    }

    public void setBetaCarotene(Double betaCarotene) {
        this.betaCarotene = betaCarotene;
    }

    public void setPresenceHuilePalme(Boolean presenceHuilePalme) {
        this.presenceHuilePalme = presenceHuilePalme;
    }
    

    

}
