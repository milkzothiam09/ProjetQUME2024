package org.example;
import java.util.*;

/**
 * Gère les montants insérés et la monnaie dans le distributeur
 */
public class Portefeuille {
    private double montantInsere;
    private double montantTotal;
    private List<Double> piecesAcceptees;
    private List<Double> billetsAcceptes;


    // Méthode pour insérer de l'argent
    public void insererMontant(double montant) {
        if (montant <= 0) {
            throw new IllegalArgumentException("Le montant doit être positif");
        }
        this.montantInsere += montant;
    }

    public double calculerMonnaie(double prix) {
        if (prix <= 0) {
            throw new IllegalArgumentException("Le prix doit être positif");
        }
        return Math.max(0, montantInsere - prix);
    }

    public void rendreMonnaie(double prix) {
        if (prix <= 0) {
            throw new IllegalArgumentException("Le prix doit être positif");
        }
        this.montantInsere = calculerMonnaie(prix);
    }

    public boolean montantSuffisant(double prix) {
        return montantInsere >= prix;
    }

    // Getters
    public double getMontantInsere() { return montantInsere; }
    public double getMontantTotal() { return montantTotal; }
    public List<Double> getPiecesAcceptees() { return new ArrayList<>(piecesAcceptees); }
    public List<Double> getBilletsAcceptes() { return new ArrayList<>(billetsAcceptes); }

    // Réinitialisation
    public void reinitialiser() {
        this.montantInsere = 0.0;
    }

    @Override
    public String toString() {
        return String.format("Portefeuille{montantInsere=%.2f, montantTotal=%.2f}",
                montantInsere, montantTotal);
    }
}