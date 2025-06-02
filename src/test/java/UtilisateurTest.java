

import org.example.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UtilisateurTest {

    private Utilisateur client;
    private Utilisateur admin;
    private Boisson boisson;
    private TransactionAchat achatReussi;
    private TransactionAchat achatRate;

    @BeforeEach
    void setUp() {
        client = new Utilisateur("u1", "Alice");
        admin = new Utilisateur("admin1", "Bob", "ADMINISTRATEUR");

        boisson = new Boisson(1, "Coca", 2.0, "C01", Boisson.Categorie.BOISSON, "coca.png");
        achatReussi = new TransactionAchat(1, boisson, boisson.getPrix(), "REUSSIE");
        achatRate = new TransactionAchat(2, boisson, boisson.getPrix(), "ECHOUÉE");
    }


    @Test
    void testAuthentificationAdminCorrecte() {
        assertTrue(admin.authentifier("admin123"));
    }

    @Test
    void testAuthentificationAdminIncorrecte() {
        assertFalse(admin.authentifier("wrongpass"));
    }

    @Test
    void testAuthentificationClientToujoursValide() {
        assertTrue(client.authentifier("nimportequoi"));
    }


    @Test
    void testAjouterAchatEchoue() {
        client.ajouterAchat(achatRate);
        List<TransactionAchat> historique = client.obtenirHistoriqueAchats();
        assertTrue(historique.isEmpty());
    }

    @Test
    void testEstAdministrateurEtClient() {
        assertTrue(admin.estAdministrateur());
        assertFalse(admin.estClient());
        assertTrue(client.estClient());
        assertFalse(client.estAdministrateur());
    }


}
