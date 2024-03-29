package com.DAO;

import com.dbconnpool.ConnectionPool;
import com.models.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {

    public List<Category> getAllCategories() throws SQLException {
        List<Category> categoryList = new ArrayList<>();
        String sql = "select name from categories";
        try (Connection conn = ConnectionPool.getConnection();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)) {

            while(rs.next()) {
                categoryList.add(new Category(rs.getString(1)));
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new SQLException(e.getMessage());
        }
        return categoryList;
    }

    public Category getCategory(int categoryid) throws SQLException {
        Category category = null;
        String sql = "select name from Categories where id = ?";
        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setInt(1, categoryid);

            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                category = new Category(rs.getString("name"));
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new SQLException(e.getCause());
        }
        return category;
    }

    public int addCategory(Category category) throws SQLException {
        String sql = "insert into categories (name) values (?)";
        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setString(1, category.getName());
            int updateStatus = pstmt.executeUpdate();
            pstmt.close();
            if (updateStatus == 1) {
                conn.commit();
                return updateStatus;
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new SQLException(e.getMessage());
        }
        return 0;
    }
}
