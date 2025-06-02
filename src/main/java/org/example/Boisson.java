package org.example;

public class Boisson {

    private final int id;
    private final String nom;
    private double prix;
    private final String description;
    private final Categorie categorie;
    private final String marque;



    public Boisson(int id, String nom, double prix, String description, Categorie categorie, String marque) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.description = description;
        this.categorie = categorie;
        this.marque = marque;
    }



    public Categorie getCategorie() {
        return this.categorie;
    }

    public enum Categorie {
        BOISSON,
        CAFE,
        THE,
        EAU,
        CHOCOLAT,
        JUS
    }



    // Getters
    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public double getPrix() {
        return prix;
    }

    public String getDescription() {
        return description;
    }

    // Méthode pour mettre à jour le prix
    public void mettreAJourPrix(double nouveauPrix) {
        if (nouveauPrix <= 0) {
            throw new IllegalArgumentException("Le prix doit être positif");
        }
        this.prix = nouveauPrix;
    }

    @Override
    public String toString() {
        return String.format("Boisson[id=%d, nom=%s, prix=%.2f, catégorie=%s, marque=%s]",
                id, nom, prix, categorie, marque);
    }

}
