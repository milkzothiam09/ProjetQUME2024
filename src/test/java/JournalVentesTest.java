import org.example.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JournalVentesTest {

    private JournalVentes journal;
    private Boisson boisson1;
    private Boisson boisson2;
    private TransactionAchat achat1;
    private TransactionAchat achat2;
    private TransactionAchat achat3;

    @BeforeEach
    void setUp() {
        journal = new JournalVentes();

        boisson1 = new Boisson(1, "Coca", 2.0, "Coca1", Boisson.Categorie.BOISSON, "coca.png");
        boisson2 = new Boisson(2, "Flécha", 1.5, "Thé1", Boisson.Categorie.THE, "the.png");

        achat1 = new TransactionAchat(1, boisson1, boisson1.getPrix(), "REUSSIE");
        achat2 = new TransactionAchat(2, boisson2, boisson2.getPrix(), "REUSSIE");
        achat3 = new TransactionAchat(3, boisson1, boisson1.getPrix(), "ECHOUÉE");
    }

    @Test
    void testObtenirTransactionsParDate() {
        LocalDate date = LocalDate.now();

        journal.ajouterTransaction(achat1);
        journal.ajouterTransaction(achat2);
        journal.ajouterTransaction(achat3);

        List<TransactionAchat> transactions = journal.obtenirTransactionsParDate(date);
        assertEquals(3, transactions.size());
    }

    @Test
    void testGetTransactionsRetourneCopie() {
        journal.ajouterTransaction(achat1);
        List<TransactionAchat> liste1 = journal.getTransactions();
        List<TransactionAchat> liste2 = journal.getTransactions();
        assertNotSame(liste1, liste2);
    }

    @Test
    void testToStringContientInfo() {
        journal.ajouterTransaction(achat1);
        String s = journal.toString();
        assertTrue(s.contains("transactions="));
        assertTrue(s.contains("CA="));
        assertTrue(s.contains("ventes="));
    }

    @Test
    void testAjouterTransactionNullLanceException() {
        assertThrows(IllegalArgumentException.class, () -> journal.ajouterTransaction(null));
    }
}
