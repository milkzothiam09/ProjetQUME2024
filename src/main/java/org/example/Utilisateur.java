package org.example;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Représente un utilisateur du distributeur automatique
 */
public class Utilisateur {
    private String id;
    private String nom;
    private String typeUtilisateur; // "CLIENT", "ADMINISTRATEUR", "TECHNICIEN"
    private LocalDate dateCreation;
    private LocalDateTime dernierAcces;
    private List<TransactionAchat> historiqueAchats;

    // Constructeur principal
    public Utilisateur(String id, String nom, String typeUtilisateur) {
        this.id = id;
        this.nom = nom;
        this.typeUtilisateur = typeUtilisateur;
        this.dateCreation = LocalDate.now();
        this.dernierAcces = LocalDateTime.now();
        this.historiqueAchats = new ArrayList<>();
    }

    // Constructeur simplifié pour client
    public Utilisateur(String id, String nom) {
        this(id, nom, "CLIENT");
    }

    // Méthode d'authentification
    public boolean authentifier(String motDePasse) {
        // Simulation simple - dans un vrai projet, on utiliserait un hash
        if ("ADMINISTRATEUR".equals(typeUtilisateur)) {
            return "admin123".equals(motDePasse);
        }
        return true; // Client n'a pas besoin de mot de passe
    }

    // Ajouter un achat à l'historique
    public void ajouterAchat(TransactionAchat transaction) {
        if (transaction != null && transaction.estReussie()) {
            historiqueAchats.add(transaction);
            dernierAcces = LocalDateTime.now();
        }
    }

    // Obtenir l'historique des achats
    public List<TransactionAchat> obtenirHistoriqueAchats() {
        return new ArrayList<>(historiqueAchats);
    }

    // Vérifier les permissions
    public boolean estAdministrateur() {
        return "ADMINISTRATEUR".equals(typeUtilisateur);
    }

    public boolean estClient() {
        return "CLIENT".equals(typeUtilisateur);
    }

    // Getters
    public String getId() { return id; }
    public String getNom() { return nom; }
    public String getTypeUtilisateur() { return typeUtilisateur; }
    public LocalDate getDateCreation() { return dateCreation; }
    public LocalDateTime getDernierAcces() { return dernierAcces; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Utilisateur that = (Utilisateur) obj;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("Utilisateur{id='%s', nom='%s', type='%s'}",
                id, nom, typeUtilisateur);
    }
}