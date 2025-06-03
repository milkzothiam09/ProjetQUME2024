import static org.junit.Assert.*;

import org.example.Portefeuille;
import org.junit.Test;
import org.junit.Before;

public class PortefeuilleTestacceptance {

    private Portefeuille portefeuille;

    @Before
    public void setUp() {
        portefeuille = new Portefeuille();
    }

    @Test
    public void testInsertionMontantValide() {
        // Étant donné un portefeuille initialisé (fait dans setUp)

        // Quand j'insère un montant de 2000
        portefeuille.insererMontant(2000);

        // Alors le montant inséré doit être 2000.
        assertEquals(2000, portefeuille.getMontantInsere(), 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInsertionMontantNegatif() {
        // Étant donné un portefeuille initialisé (fait dans setUp)

        // Quand j'insère un montant de -5.0
        portefeuille.insererMontant(-5.0);

        // Alors une erreur "Le montant doit être positif" doit être levée
        // (gérée par l'annotation expected.)
    }

    @Test
    public void testVerificationMontantSuffisant() {
        // Étant donné un portefeuille avec 500 insérés.
        portefeuille.insererMontant(500);

        // Quand je vérifie si le montant est suffisant pour un prix de 200
        boolean resultat = portefeuille.montantSuffisant(200);

        // Alors le résultat doit être vrai
        assertTrue(resultat);
    }

    @Test
    public void testVerificationMontantInsuffisant() {
        // Étant donné un portefeuille avec 50 insérés
        portefeuille.insererMontant(50);

        // Quand je vérifie si le montant est suffisant pour un prix de 500
        boolean resultat = portefeuille.montantSuffisant(500);

        // Alors le résultat doit être faux
        assertFalse(resultat);
    }
}