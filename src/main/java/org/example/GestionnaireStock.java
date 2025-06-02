package org.example;
import java.util.*;

/**
 * Gère le stock des boissons dans le distributeur
 */
public class GestionnaireStock {
    private final Map<Integer, Integer> stockBoissons; // ID Boisson -> Quantité

    public GestionnaireStock() {
        stockBoissons = new HashMap<>();
    }
    // Méthodes de gestion du stock
    public void ajouterStock(int idBoisson, int quantite) {
        if (quantite <= 0) {
            throw new IllegalArgumentException("La quantité doit être positive");
        }
        int quantiteActuelle = stockBoissons.getOrDefault(idBoisson, 0);
        stockBoissons.put(idBoisson, quantiteActuelle + quantite);
    }



    public void retirerStock(int idBoisson, int quantite) {
        if (quantite <= 0) {
            throw new IllegalArgumentException("La quantité doit être positive");
        }
        int quantiteActuelle = stockBoissons.getOrDefault(idBoisson, 0);
        if (quantiteActuelle < quantite) {
            throw new IllegalStateException("Stock insuffisant");
        }
        stockBoissons.put(idBoisson, quantiteActuelle - quantite);
    }




    public int obtenirQuantite(int idBoisson) {
        return stockBoissons.getOrDefault(idBoisson, 0);
    }




    public boolean stockEpuise(int idBoisson) {
        return obtenirQuantite(idBoisson) == 0;
    }

    public Map<Integer, Integer> getStockBoissons() {
        return new HashMap<>(stockBoissons);
    }
}
