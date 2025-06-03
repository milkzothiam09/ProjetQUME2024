import static org.junit.Assert.*;

import org.example.*;
import org.junit.Test;
import java.time.LocalDateTime;

public class TransactionAchatTestAcceptance {

    @Test
    public void testTransactionReussieAvecMonnaieRendue() {
        // Étant donné une boisson "Coca" au prix de 300
        Boisson coca = new Boisson(1, "Coca", 300, "Boisson gazeuse",
                Boisson.Categorie.SODA, "Coca-Cola");

        // Et une transaction #1001 avec 500 insérés
        TransactionAchat transaction = new TransactionAchat(1001, coca, 500, "user123");

        // Quand je valide la transaction.
        boolean resultat = transaction.validerTransaction();
        LocalDateTime dateFin = transaction.getDateTransaction();

        // Alors le statut doit être "REUSSIE"
        assertEquals("REUSSIE", transaction.getStatut());

        // Et la validation doit réussir.
        assertTrue(resultat);

        // Et la monnaie rendue doit être 200
        assertEquals(200, transaction.getMonnaieRendue(), 0.001);

        // Et la durée de transaction doit être enregistrée (positif)
        assertTrue(transaction.getTempsTransaction() > 0);

        // Et la date de transaction doit être récente
        assertTrue(dateFin.isAfter(LocalDateTime.now().minusMinutes(1)));
    }

    @Test
    public void testTransactionEchoueeFondsInsuffisants() {
        // Étant donné une boisson "Eau" au prix de 400
        Boisson eau = new Boisson(2, "Eau", 400, "Eau minérale",
                Boisson.Categorie.EAU, "Evian");

        // Et une transaction #1002 avec 250 insérés
        TransactionAchat transaction = new TransactionAchat(1002, eau, 250, "user123");
        LocalDateTime dateInitiale = transaction.getDateTransaction();

        // Quand je valide la transaction.
        boolean resultat = transaction.validerTransaction();

        // Alors le statut doit rester "EN_COURS".
        assertEquals("EN_COURS", transaction.getStatut());

        // Et la validation doit échouer
        assertFalse(resultat);

        // Et la monnaie rendue doit être 0.00
        assertEquals(0, transaction.getMonnaieRendue(), 0.001);

        // Et la transaction ne doit pas être marquée comme réussie
        assertFalse(transaction.estReussie());

        // Et la date de transaction ne doit pas changer
        assertEquals(dateInitiale, transaction.getDateTransaction());
    }

    @Test
    public void testAnnulationTransaction() {
        // Étant donné une boisson "Café" au prix de 150
        Boisson cafe = new Boisson(3, "Café", 150, "Café noir",
                Boisson.Categorie.CAFE, "Nespresso");

        // Et une transaction #1003 avec 200 insérés
        TransactionAchat transaction = new TransactionAchat(1003, cafe, 200, "user123");
        LocalDateTime dateInitiale = transaction.getDateTransaction();

        // Quand j'annule la transaction.
        transaction.annulerTransaction();
        LocalDateTime dateApresAnnulation = transaction.getDateTransaction();

        // Alors le statut doit être "ANNULEE"
        assertEquals("ANNULEE", transaction.getStatut());

        // Et la monnaie rendue doit être 200
        assertEquals(200, transaction.getMonnaieRendue(), 0.001);

        // Et la date de transaction doit être enregistrée et inchangée
        assertEquals(dateInitiale, transaction.getDateTransaction());
        assertNotNull(dateApresAnnulation);
    }
}