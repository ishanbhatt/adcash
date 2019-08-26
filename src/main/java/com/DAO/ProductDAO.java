package com.DAO;

import com.dbconnpool.ConnectionPool;
import com.models.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    public List<Product> getAllProducts() throws SQLException {
        List<Product> productList = new ArrayList<>();
        String sql = "SELECT name, description, price, category from products";

        try (Connection conn = ConnectionPool.getConnection();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)) {

            while(rs.next()) {
                productList.add(new Product(
                        rs.getString(1), rs.getString(2),
                        rs.getDouble(3), rs.getInt(4)
                ));
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new SQLException(e.getCause());
        }
        return productList;
    }

    public List<Product> getProductByCategory(int categoryId) throws SQLException {
        List<Product> productList = new ArrayList<>();
        String sql = "SELECT name, description, price from products where category = ?";

        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, categoryId);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                productList.add(new Product(
                        rs.getString(1), rs.getString(2),
                        rs.getDouble(3), categoryId
                ));
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new SQLException(e.getCause());
        }
        return productList;

    }
}
