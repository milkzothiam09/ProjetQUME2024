

import org.example.GestionnaireStock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GestionnaireStockTest {

    private GestionnaireStock stock;

    @BeforeEach
    void setup() {
        stock = new GestionnaireStock();
    }

    @Test
    void testAjouterStock() {
        stock.ajouterStock(1, 5);
        assertEquals(5, stock.obtenirQuantite(1));
    }

    @Test
    void testRetirerStock() {
        stock.ajouterStock(2, 10);
        stock.retirerStock(2, 4);
        assertEquals(6, stock.obtenirQuantite(2));
    }

    @Test
    void testStockEpuise() {
        assertTrue(stock.stockEpuise(3));
    }

    @Test
    void testObtenirQuantiteBoissonInexistante() {
        assertEquals(0, stock.obtenirQuantite(99));
    }

    @Test
    void testAjouterQuantiteInvalide() {
        assertThrows(IllegalArgumentException.class, () -> stock.ajouterStock(1, 0));
    }

    @Test
    void testRetirerTropDeStock() {
        stock.ajouterStock(4, 2);
        assertThrows(IllegalStateException.class, () -> stock.retirerStock(4, 5));
    }
}
