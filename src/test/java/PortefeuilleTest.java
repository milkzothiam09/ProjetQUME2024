import org.example.Portefeuille;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PortefeuilleTest {

    private Portefeuille portefeuille;

    @BeforeEach
    void setUp() {
        portefeuille = new Portefeuille();
    }

    @Test
    void testInsererMontantAugmenteMontantInsere() {
        portefeuille.insererMontant(5.0);
        assertEquals(5.0, portefeuille.getMontantInsere(), 0.0001);
    }

    @Test
    void testCalculerMonnaieRetourneDifference() {
        portefeuille.insererMontant(10.0);
        double monnaie = portefeuille.calculerMonnaie(7.5);
        assertEquals(2.5, monnaie, 0.0001);
    }

    @Test
    void testMontantSuffisantRetourneVraiSiAssez() {
        portefeuille.insererMontant(2.0);
        assertTrue(portefeuille.montantSuffisant(1.5));
        assertFalse(portefeuille.montantSuffisant(2.5));
    }
}
