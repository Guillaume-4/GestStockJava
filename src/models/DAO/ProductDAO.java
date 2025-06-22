package models.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Category;
import models.Furnisher;
import models.Product;

public class ProductDAO {
    public void addProduct(Product product) {
        String query = "INSERT INTO product (product_name, product_quantity, product_unit_price, category_id, furnisher_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, product.getProductName());
            statement.setInt(2, product.getProductQuantity());
            statement.setDouble(3, product.getProductUnitPrice());
            statement.setDouble(4, product.getCategory().getCategoryId());
            statement.setDouble(5, product.getFurnisher().getFurnisherId());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout du produit : " + e.getMessage());
        }
    }

    public Product getProductByID(int product_id) {
        String query = "SELECT * FROM Product WHERE product_id = ?";

        try (Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, product_id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString("product_name");
                int quantity = resultSet.getInt("product_quantity");
                Double unitPrice = resultSet.getDouble("product_unit_price");
                int categoryId = resultSet.getInt("category_id");
                int furnisherId = resultSet.getInt("furnisher_id");

                Category category = new CategoryDAO().getCategoryByID(categoryId);
                Furnisher furnisher = new FurnisherDAO().getFurnisherByID(furnisherId);

                return new Product(product_id, name, quantity, unitPrice, category, furnisher);
            } else
                return null;
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération du produit : " + e.getMessage());
            return null;
        }
    }

    public Product getProductByName(String product_name) {
        String query = "SELECT * FROM Product WHERE product_name = ?";

        try (Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, product_name);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("product_id");
                int quantity = resultSet.getInt("product_quantity");
                Double unitPrice = resultSet.getDouble("product_unit_price");
                int categoryId = resultSet.getInt("category_id");
                int furnisherId = resultSet.getInt("furnisher_id");

                Category category = new CategoryDAO().getCategoryByID(categoryId);
                Furnisher furnisher = new FurnisherDAO().getFurnisherByID(furnisherId);

                return new Product(id, product_name, quantity, unitPrice, category, furnisher);
            } else
                return null;
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération du produit : " + e.getMessage());
            return null;
        }
    }

    public void updateProduct(Product product) {
        String query = "UPDATE product SET product_name=?, product_quantity=?, product_unit_price=?, category_id=?, furnisher_id=? WHERE product_id=?";
        try (Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, product.getProductName());
            statement.setInt(2, product.getProductQuantity());
            statement.setDouble(3, product.getProductUnitPrice());
            statement.setInt(4, product.getCategory().getCategoryId());
            statement.setInt(5, product.getFurnisher().getFurnisherId());
            statement.setInt(6, product.getProductId());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erreur lors de la modification du produit : " + e.getMessage());
        }
    }

    public void deleteProduct(int productId) {
        String query = "DELETE FROM product WHERE product_id=?";
        try (Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, productId);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression du produit : " + e.getMessage());
        }
    }

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM Product";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("product_id");
                String name = resultSet.getString("product_name");
                int quantity = resultSet.getInt("product_quantity");
                Double unitPrice = resultSet.getDouble("product_unit_price");
                int categoryId = resultSet.getInt("category_id");
                int furnisherId = resultSet.getInt("furnisher_id");

                Category category = new CategoryDAO().getCategoryByID(categoryId);
                Furnisher furnisher = new FurnisherDAO().getFurnisherByID(furnisherId);

                products.add(new Product(id, name, quantity, unitPrice, category, furnisher));
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des produits : " + e.getMessage());
        }
        return products;
    }
    
    public boolean updateProductQuantity(int productId, int newQuantity) {
        String query = "UPDATE Product SET product_quantity = ? WHERE product_id = ?";
        try (Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, newQuantity);
            statement.setInt(2, productId);
            
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour de la quantité : " + e.getMessage());
            return false;
        }
    }
    
    public boolean reduceProductQuantity(int productId, int quantityToReduce) {
        // D'abord, récupérer la quantité actuelle
        Product product = getProductByID(productId);
        if (product == null) {
            return false;
        }
        
        int currentQuantity = product.getProductQuantity();
        int newQuantity = currentQuantity - quantityToReduce;
        
        // Vérifier que la nouvelle quantité n'est pas négative
        if (newQuantity < 0) {
            return false;
        }
        
        return updateProductQuantity(productId, newQuantity);
    }
    
    public boolean increaseProductQuantity(int productId, int quantityToAdd) {
        // Récupérer la quantité actuelle
        Product product = getProductByID(productId);
        if (product == null) {
            return false;
        }
        
        int currentQuantity = product.getProductQuantity();
        int newQuantity = currentQuantity + quantityToAdd;
        
        return updateProductQuantity(productId, newQuantity);
    }
    
    public boolean hasEnoughStock(int productId, int requiredQuantity) {
        Product product = getProductByID(productId);
        if (product == null) {
            return false;
        }
        
        return product.getProductQuantity() >= requiredQuantity;
    }
}