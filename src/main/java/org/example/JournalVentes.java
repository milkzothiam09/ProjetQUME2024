package org.example;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Gère l'historique des ventes et transactions
 */
public class JournalVentes {
    private final List<TransactionAchat> transactions;
    private double chiffreAffaireJournalier;
    private int nombreVentesJournalieres;
    private final LocalDate dateCreation;
    private LocalDateTime derniereVente;

    public JournalVentes() {
        this.transactions = new ArrayList<>();
        this.dateCreation = LocalDate.now();
        this.chiffreAffaireJournalier = 0;
        this.nombreVentesJournalieres = 0;
    }

    public void ajouterTransaction(TransactionAchat transaction) {
        if (transaction == null) {
            throw new IllegalArgumentException("La transaction ne peut pas être null");
        }
        transactions.add(transaction);
        if (transaction.estReussie()) {
            chiffreAffaireJournalier += transaction.getPrixBoisson();
            nombreVentesJournalieres++;
            derniereVente = transaction.getDateTransaction();
        }
    }

    public List<TransactionAchat> obtenirTransactionsParDate(LocalDate date) {
        List<TransactionAchat> resultats = new ArrayList<>();
        for (TransactionAchat t : transactions) {
            if (t.getDateTransaction().toLocalDate().equals(date)) {
                resultats.add(t);
            }
        }
        return resultats;
    }

    public double calculerChiffreAffaires() {
        return transactions.stream()
                .filter(TransactionAchat::estReussie)
                .mapToDouble(TransactionAchat::getPrixBoisson)
                .sum();
    }

    public List<Boisson> obtenirBoissonsLesPlusVendues() {
        Map<Boisson, Integer> compteur = new HashMap<>();
        for (TransactionAchat t : transactions) {
            if (t.estReussie()) {
                Boisson b = t.getBoisson();
                compteur.put(b, compteur.getOrDefault(b, 0) + 1);
            }
        }
        return compteur.entrySet().stream()
                .sorted(Map.Entry.<Boisson, Integer>comparingByValue().reversed())
                .map(Map.Entry::getKey)
                .toList();
    }

    // Getters
    public List<TransactionAchat> getTransactions() {
        return new ArrayList<>(transactions);
    }

    public double getChiffreAffaireJournalier() {
        return chiffreAffaireJournalier;
    }

    public int getNombreVentesJournalieres() {
        return nombreVentesJournalieres;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public LocalDateTime getDerniereVente() {
        return derniereVente;
    }

    @Override
    public String toString() {
        return String.format("JournalVentes{transactions=%d, CA=%.2f, ventes=%d}",
                transactions.size(), chiffreAffaireJournalier, nombreVentesJournalieres);
    }
}
