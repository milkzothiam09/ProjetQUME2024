import org.example.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TransactionAchatTest {

    @Test
    void testTransactionReussie() {
        Boisson b = new Boisson(1, "Chocolat", 2.0, "chaud", Boisson.Categorie.CHOCOLAT, "img.png");
        TransactionAchat t = new TransactionAchat(1, b, 3.0, "U1");
        assertTrue(t.validerTransaction());
        assertEquals("REUSSIE", t.getStatut());
        assertEquals(1.0, t.getMonnaieRendue(), 0.01);
    }

    @Test
    void testTransactionAnnulee() {
        Boisson b = new Boisson(2, "Eau", 1.0, "froide", Boisson.Categorie.EAU, "img.png");
        TransactionAchat t = new TransactionAchat(2, b, 0.5, "U2");
        t.annulerTransaction();
        assertEquals("ANNULEE", t.getStatut());
        assertEquals(0.5, t.getMonnaieRendue(), 0.01);
    }

    @Test
    void testToStringEtDetails() {
        Boisson b = new Boisson(3, "Jus", 2.5, "froid", Boisson.Categorie.JUS, "img.jpg");
        TransactionAchat t = new TransactionAchat(3, b, 3.0, "U3");
        t.validerTransaction();
        assertTrue(t.obtenirDetailsTransaction().contains("Transaction #3"));
        assertTrue(t.toString().contains("TransactionAchat{id=3"));
    }
}
