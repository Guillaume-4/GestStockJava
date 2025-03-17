package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import models.Product;
import models.Sale;

import java.sql.Date;

class UnitTestProductSale {
    private Product product;
    private Sale sale;

    @BeforeEach
    public void setUp() {
        // Create a product and a sale to test the relationship
        product = new Product(1, "Test Product", 10, 100.0, null, null);
        sale = new Sale(1, new Date(new java.util.Date().getTime()), product);
        product.addSale(sale);
    }

    @Test
    public void testProduitVenteRelation() {
        // Check that the product has a sale associated
        assertNotNull(product.getSales());
        assertEquals(1, product.getSales().size(), "Le produit devrait avoir une vente associée.");
        assertEquals(sale, product.getSales().get(0), "La vente associée au produit devrait être la bonne.");

        // Check that the sale has the correct product associated
        assertEquals(product, sale.getProduct(), "Le produit de la vente devrait correspondre au produit associé.");
    }

    @Test
    public void testAjoutVenteProduct() {
        // Create a new product and sale
        Product newProduct = new Product(2, "Test Product 2", 10, 100.0, null, null);
        Sale newSale = new Sale(2, new Date(new java.util.Date().getTime()), newProduct);

        // Add the sale to the product and check if it was added
        newProduct.addSale(newSale);
        assertTrue(newProduct.getSales().contains(newSale),
                "La nouvelle vente devrait être associée au produit.");
    }
}