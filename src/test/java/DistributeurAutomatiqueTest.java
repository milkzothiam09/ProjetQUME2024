import org.example.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DistributeurAutomatiqueTest {

    private DistributeurAutomatique distributeur;
    private Boisson boisson;

    @BeforeEach
    void setUp() {
        TransactionAchat fakeTransaction = new TransactionAchat(0, new Boisson(0, "Test", 1.0, "chaud", Boisson.Categorie.CAFE, "img.png"), 1.0, "U1");
        distributeur = new DistributeurAutomatique(1, "TestDistributeur", fakeTransaction);
        distributeur.mettreEnService();

        boisson = new Boisson(1, "Café", 1.5, "chaud", Boisson.Categorie.CAFE, "image.jpg");
        distributeur.ajouterNouvelleBoisson(boisson);
    }

    @Test
    void testAffichageBoissons() {
        assertEquals(1, distributeur.afficherBoissonsDisponibles().size());
    }

    @Test
    void testAchatAvecSucces() {
        TransactionAchat transaction = distributeur.effectuerAchat(1, 2.0, "U123");
        assertEquals("REUSSIE", transaction.getStatut());
        assertEquals(0.5, transaction.getMonnaieRendue(), 0.01);
    }

    @Test
    void testAchatMontantInsuffisant() {
        TransactionAchat transaction = distributeur.effectuerAchat(1, 1.0, "U123");
        assertEquals("ANNULEE", transaction.getStatut());
        assertEquals(1.0, transaction.getMonnaieRendue(), 0.01);
    }

    @Test
    void testBoissonNonTrouvee() {
        Exception e = assertThrows(IllegalStateException.class, () -> {
            distributeur.effectuerAchat(999, 2.0, "U999");
        });
        assertTrue(e.getMessage().contains("Boisson non trouvée"));
    }

    @Test
    void testAjoutBoissonHorsService() {
        distributeur.mettreHorsService();
        assertThrows(IllegalStateException.class, () -> {
            distributeur.ajouterNouvelleBoisson(new Boisson(2, "Thé", 1.2, "chaud", Boisson.Categorie.THE, "img.png"));
        });
    }

    @Test
    void testMontantNegatif() {
        assertThrows(IllegalArgumentException.class, () -> {
            distributeur.effectuerAchat(1, -1.0, "U123");
        });
    }
}
