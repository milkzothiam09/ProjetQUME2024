import static org.junit.Assert.*;

import org.example.Boisson;
import org.junit.Test;
import org.junit.Before;

public class BoissonTestAcceptance {
    private Boisson sprite;
    private Boisson cappuccino;
    private Boisson jusOrange;

    @Before
    public void setUp() {
        // Préparation commune pour les tests avec tous les paramètres requis
        sprite = new Boisson(1, "Sprite", 1000, "Boisson gazeuse citronnée",
                Boisson.Categorie.SODA, "Coca-Cola");
        cappuccino = new Boisson(2, "Cappuccino", 500, "Café avec mousse de lait",
                Boisson.Categorie.CAFE, "Starbucks");
        jusOrange = new Boisson(3, "Jus d'orange", 500, "Jus 100% naturel",
                Boisson.Categorie.JUS, "Tropicana");
    }

    @Test
    public void testCreationBoissonValide() {
        // Vérification des attributs de Sprite
        assertEquals(1, sprite.getId());
        assertEquals("Sprite", sprite.getNom());
        assertEquals(1000, sprite.getPrix(), 0.001);
        assertEquals("Boisson gazeuse citronnée", sprite.getDescription());
        assertEquals(Boisson.Categorie.SODA, sprite.getCategorie());
        assertEquals("Coca-Cola", sprite.getMarque());
    }

    @Test
    public void testMiseAJourPrixValide() {
        // Sauvegarde du prix initial pour vérification
        double prixInitial = cappuccino.getPrix();

        // Mise à jour du prix
        cappuccino.mettreAJourPrix(600);

        // Vérification du nouveau prix
        assertNotEquals(prixInitial, cappuccino.getPrix(), 0.001);
        assertEquals(600, cappuccino.getPrix(), 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMiseAJourPrixInvalide() {
        // Tentative de mise à jour avec prix invalide
        jusOrange.mettreAJourPrix(-1.0);
    }

    @Test
    public void testPrixInchangeApresEchecMiseAJour() {
        // Prix avant tentative de modification
        double prixInitial = jusOrange.getPrix();

        try {
            jusOrange.mettreAJourPrix(-1.0);
            fail("Devrait lancer une exception");
        } catch (IllegalArgumentException e) {
            // Vérification que le prix n'a pas changé
            assertEquals(prixInitial, jusOrange.getPrix(), 0.001);
        }
    }

    @Test
    public void testToStringComplet() {
        String resultat = sprite.toString();
        assertTrue(resultat.contains("Sprite"));
        assertTrue(resultat.contains("1000"));
        assertTrue(resultat.contains("SODA"));
        assertTrue(resultat.contains("Coca-Cola"));
    }
}