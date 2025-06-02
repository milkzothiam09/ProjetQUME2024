package org.example;
import java.time.LocalDateTime;

/**
 * Représente une transaction d'achat dans le distributeur
 */
public class TransactionAchat {
    private final int idTransaction;
    private final Boisson boisson;
    private final double montantInsere;
    private final double prixBoisson;
    private double monnaieRendue;
    private final LocalDateTime dateTransaction;
    private String statut; // "REUSSIE", "ECHOUEE", "ANNULEE"
    private final String utilisateurId;
    private final long tempsTransaction; // en ms

    // Constructeur
    public TransactionAchat(int idTransaction, Boisson boisson, double montantInsere,
                            String utilisateurId) {
        this.idTransaction = idTransaction;
        this.boisson = boisson;
        this.montantInsere = montantInsere;
        this.prixBoisson = boisson.getPrix();
        this.utilisateurId = utilisateurId;
        this.dateTransaction = LocalDateTime.now();
        this.statut = "EN_COURS";
        this.tempsTransaction = System.currentTimeMillis();
    }




    // Getters
    public int getIdTransaction() { return idTransaction; }
    public Boisson getBoisson() { return boisson; }
    public double getMontantInsere() { return montantInsere; }
    public double getPrixBoisson() { return prixBoisson; }
    public double getMonnaieRendue() { return monnaieRendue; }
    public LocalDateTime getDateTransaction() { return dateTransaction; }
    public String getStatut() { return statut; }
    public String getUtilisateurId() { return utilisateurId; }
    public long getTempsTransaction() { return tempsTransaction; }

    // Méthodes de gestion de transaction
    public boolean validerTransaction() {
        if (montantInsere >= prixBoisson) {
            this.monnaieRendue = calculerMonnaieRendues();
            this.statut = "REUSSIE";
            return true;
        }
        return false;
    }

    public void annulerTransaction() {
        this.statut = "ANNULEE";
        this.monnaieRendue = this.montantInsere; // Rendre tout l'argent
    }

    public double calculerMonnaieRendues() {
        return Math.max(0, montantInsere - prixBoisson);
    }

    public String obtenirDetailsTransaction() {
        return String.format("Transaction #%d - %s - %.2f€ - Statut: %s",
                idTransaction, boisson.getNom(), montantInsere, statut);
    }

    public boolean estReussie() {
        return "REUSSIE".equals(statut);
    }

    @Override
    public String toString() {
        return String.format("TransactionAchat{id=%d, boisson=%s, montant=%.2f, statut=%s}",
                idTransaction, boisson.getNom(), montantInsere, statut);
    }
}
