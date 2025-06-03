import org.example.Utilisateur;
import org.junit.Test;
import static org.junit.Assert.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class UtilisateurTestAcceptance
{

    // Création simplifiée d'un client
    @Test
    public void testCreationSimplifieeClient() {
        // Étant donné que je crée un utilisateur avec ID "C123" et nom "Atta Fall"
        Utilisateur client = new Utilisateur("C123", "Atta Fall");

        // Alors le type d'utilisateur doit être "CLIENT".
        assertEquals("CLIENT", client.getTypeUtilisateur());

        // Et l'ID doit être "C123"
        assertEquals("C123", client.getId());

        // Et le nom doit être "Atta Fall"
        assertEquals("Atta Fall", client.getNom());

        // Et la date de création doit être aujourd'hui
        assertEquals(LocalDate.now(), client.getDateCreation());

        // Et la date du dernier accès ne doit pas être nulle
        assertNotNull(client.getDernierAcces());
    }

    //  Création d'un administrateur
    @Test
    public void testCreationAdministrateur() {
        // Étant donné que je crée un utilisateur avec ID "A176", nom "Yacine Seck" et type "ADMINISTRATEUR"
        Utilisateur admin = new Utilisateur("A176", "Yacine Seck", "ADMINISTRATEUR");

        // Alors le type d'utilisateur doit être "ADMINISTRATEUR".
        assertEquals("ADMINISTRATEUR", admin.getTypeUtilisateur());

        // Et l'ID doit être "A176"
        assertEquals("A176", admin.getId());

        // Et le nom doit être "Yacine Seck"
        assertEquals("Yacine Seck", admin.getNom());

        // Et il doit être identifié comme administrateur
        assertTrue(admin.estAdministrateur());

        // Et il ne doit pas être identifié comme client
        assertFalse(admin.estClient());
    }

    //  Authentification des utilisateurs
    @Test
    public void testAuthentificationUtilisateurs() {
        // Étant donné un administrateur avec ID "A176"
        Utilisateur admin = new Utilisateur("A176", "Yacine Seck", "ADMINISTRATEUR");

        // Et un client avec ID "C123"
        Utilisateur client = new Utilisateur("C123", "Atta Fall");

        // Quand l'administrateur s'authentifie avec le mot de passe "admin123"
        // Alors l'authentification doit réussir.
        assertTrue(admin.authentifier("admin123"));

        // Quand l'administrateur s'authentifie avec le mot de passe "perdu"
        // Alors l'authentification doit échouer
        assertFalse(admin.authentifier("perdu"));

        // Quand le client s'authentifie avec n'importe quel mot de passe
        // Alors l'authentification doit toujours réussir.
        assertTrue(client.authentifier("anypassword"));
        assertTrue(client.authentifier(""));
        assertTrue(client.authentifier(null));
    }


    // Vérification des permissions
    @Test
    public void testVerificationPermissions() {
        // Étant donné un client de type "CLIENT"
        Utilisateur client = new Utilisateur("C123", "Client", "CLIENT");

        // Et un administrateur de type "ADMINISTRATEUR"
        Utilisateur admin = new Utilisateur("A176", "Admin", "ADMINISTRATEUR");

        // Et un technicien de type "TECHNICIEN"
        Utilisateur technicien = new Utilisateur("T001", "Tech", "TECHNICIEN");

        // Alors le client doit être identifié comme client, mais pas comme administrateur
        assertTrue(client.estClient());
        assertFalse(client.estAdministrateur());

        // Et l'administrateur doit être identifié comme administrateur, mais pas comme client
        assertTrue(admin.estAdministrateur());
        assertFalse(admin.estClient());

        // Et le technicien ne doit être identifié ni comme client ni comme administrateur
        assertFalse(technicien.estClient());
        assertFalse(technicien.estAdministrateur());
    }
}

