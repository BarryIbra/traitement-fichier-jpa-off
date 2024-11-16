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
    
    @Column(name = "NOM",nullable = false,unique = true)
    private String nom;
    
    @ManyToOne
    @JoinColumn(name="ID_MARQUE")
    @Column(nullable = false)
    private Marque marque;

    @ManyToMany
    @JoinTable(name="COMPOSITION",joinColumns = @JoinColumn(name="ID_PRODUIT" ,referencedColumnName = "ID"),
    inverseJoinColumns = @JoinColumn(name="ID_INGREDIENT", referencedColumnName = "ID"))
    @Column(nullable = false)
    private List<Ingredient> ingredients;

    @ManyToOne
    @JoinColumn(name="ID_CATEGORIE")
    @Column(nullable = false)
    private Categorie categorie;

    @ManyToMany
    @JoinTable(name="CONTIENT",joinColumns = @JoinColumn(name="ID_PRODUIT" ,referencedColumnName = "ID"),
    inverseJoinColumns = @JoinColumn(name="ID_ALLERGENE", referencedColumnName = "ID"))
    private List<Allergene> allergene;

    /**
     * Constructeurs de la classe Produit utilisé par JPA
     */
    public Produit(){
    }

    public Produit(String nom){
        this.nom=nom;
        this.ingredients=new ArrayList<>();
        this.allergene=new ArrayList<>();
    }

    public void addIngredient(Ingredient ingredient){
        this.ingredients.add(ingredient);
    }

    public void addAllergene(Allergene allergene){
        this.allergene.add(allergene);
    }



}
