package org.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe représentant un distributeur automatique de boissons
 */
public class DistributeurAutomatique {

    private final int id;
    private final String nom;
    private boolean enService;

    public TransactionAchat getTransactions() {
        return transactions;
    }

    private final List<Boisson> boissons; // liste unique
    private final Portefeuille portefeuille;
    private final JournalVentes journalVentes;
    private final TransactionAchat transactions;

    public DistributeurAutomatique(int id, String nom, TransactionAchat transactions) {
        this.transactions = transactions;
        if (nom == null || nom.isEmpty()) {
            throw new IllegalArgumentException("Le nom du distributeur ne peut pas être vide");
        }

        this.id = id;
        this.nom = nom;
        this.enService = false; // Par défaut hors service
        this.boissons = new ArrayList<>();
        this.portefeuille = new Portefeuille();
        this.journalVentes = new JournalVentes();
    }

    public void mettreEnService() {
        this.enService = true;
    }

    public void mettreHorsService() {
        this.enService = false;
    }

    public List<Boisson> afficherBoissonsDisponibles() {
        if (!enService) {
            throw new IllegalStateException("Le distributeur est hors service");
        }
        return new ArrayList<>(boissons);
    }

    public boolean ajouterNouvelleBoisson(Boisson boisson) {
        if (!enService) {
            throw new IllegalStateException("Le distributeur est hors service");
        }
        if (boisson == null) {
            throw new IllegalArgumentException("La boisson ne peut pas être nulle");
        }
        if (boissons.stream().anyMatch(b -> b.getId() == boisson.getId())) {
            return false; // ID déjà existant
        }
        return boissons.add(boisson);
    }

    public boolean supprimerBoisson(Boisson boisson) {
        if (!enService) {
            throw new IllegalStateException("Le distributeur est hors service");
        }
        return boissons.remove(boisson);
    }

    public TransactionAchat effectuerAchat(int idBoisson, double montantInsere, String utilisateurId) {
        if (montantInsere <= 0) {
            throw new IllegalArgumentException("Le montant inséré doit être positif");
        }

        Boisson boisson = trouverBoissonParId(idBoisson);
        if (boisson == null) {
            throw new IllegalStateException("Boisson non trouvée avec l'ID: " + idBoisson);
        }

        TransactionAchat transaction = new TransactionAchat(
                journalVentes.getTransactions().size() + 1,
                boisson,
                montantInsere,
                utilisateurId
        );

        if (montantInsere >= boisson.getPrix()) {
            portefeuille.insererMontant(boisson.getPrix());
            transaction.validerTransaction();
        } else {
            transaction.annulerTransaction();
        }

        journalVentes.ajouterTransaction(transaction);
        return transaction;
    }

    private Boisson trouverBoissonParId(int id) {
        for (Boisson b : boissons) {
            if (b.getId() == id) {
                return b;
            }
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public List<Boisson> getBoissons() {
        return new ArrayList<>(boissons);
    }

    public Portefeuille getPortefeuille() {
        return portefeuille;
    }

    public JournalVentes getJournalVentes() {
        return journalVentes;
    }

    public boolean isEnService() {
        return enService;
    }

    @Override
    public String toString() {
        return String.format("Distributeur[id=%d, nom=%s, nombreBoissons=%d]",
                id, nom, boissons.size());
    }
}
