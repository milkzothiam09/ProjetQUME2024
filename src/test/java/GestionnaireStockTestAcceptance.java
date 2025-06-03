import org.example.GestionnaireStock;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class GestionnaireStockTestAcceptance {
    private GestionnaireStock gestionnaireStock;

    @Before
    public void setUp() {
        gestionnaireStock = new GestionnaireStock();
        // Initialisation spécifique pour le scénario de retrait
        gestionnaireStock.ajouterStock(103, 10);
    }

    //  Ajout de stock avec quantité positive
    @Test
    public void testAjoutStockQuantitePositive() {
        // Étant donné un stock initial vide pour la boisson #101
        assertEquals(0, gestionnaireStock.obtenirQuantite(101));

        // Quand j'ajoute 5 unités de la boisson #101
        gestionnaireStock.ajouterStock(101, 5);

        // Alors la quantité disponible pour #101 doit être 5.
        assertEquals(5, gestionnaireStock.obtenirQuantite(101));
    }

    //  Tentative d'ajout avec quantité négative
    @Test(expected = IllegalArgumentException.class)
    public void testAjoutStockQuantiteNegative() {
        // Étant donné un stock initial vide pour la boisson #102
        assertEquals(0, gestionnaireStock.obtenirQuantite(102));

        // Quand j'essaie d'ajouter -3 unités de la boisson #102
        gestionnaireStock.ajouterStock(102, -3);

        // Alors une exception est levée (vérifiée par l'annotation)
        // Et le stock pour #102 doit rester à 0.
        assertEquals(0, gestionnaireStock.obtenirQuantite(102));
    }

    //  Retrait de stock avec quantité suffisante
    @Test
    public void testRetraitStockQuantiteSuffisante() {
        // Étant donné un stock initial de 10 unités pour la boisson #103
        assertEquals(10, gestionnaireStock.obtenirQuantite(103));

        // Quand je retire 4 unités de la boisson #103
        gestionnaireStock.retirerStock(103, 4);

        // Alors la quantité disponible pour #103 doit être 6.
        assertEquals(6, gestionnaireStock.obtenirQuantite(103));
    }
}