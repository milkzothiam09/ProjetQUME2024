import org.example.Boisson;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BoissonTest {

    @Test
    void testCreationBoisson() {
        Boisson boisson = new Boisson(1, "Café Noir", 2.50,
                "Café noir intense", Boisson.Categorie.CAFE, "Nespresso");

        assertEquals(1, boisson.getId());
        assertEquals("Café Noir", boisson.getNom());
        assertEquals(2.50, boisson.getPrix(), 0.001);
        assertEquals("Café noir intense", boisson.getDescription());
        assertEquals(Boisson.Categorie.CAFE, boisson.getCategorie());
    }

    @Test
    void testMettreAJourPrix() {
        Boisson boisson = new Boisson(2, "Thé Vert", 1.80,
                "Thé vert bio", Boisson.Categorie.THE, "Lipton");

        boisson.mettreAJourPrix(2.00);
        assertEquals(2.00, boisson.getPrix(), 0.001);
    }

    @Test
    void testMettreAJourPrixNegatif() {
        Boisson boisson = new Boisson(3, "Eau Minérale", 1.00,
                "Eau minérale naturelle", Boisson.Categorie.EAU, "Evian");

        assertThrows(IllegalArgumentException.class,
                () -> boisson.mettreAJourPrix(-1.00));
    }


}