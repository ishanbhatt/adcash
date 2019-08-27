package com.DAO;

import com.dbconnpool.ConnectionPool;
import com.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    public List<User> getAllUsers() throws SQLException {
        List<User> userList = new ArrayList<>();
        String sql = "select name from users";
        try (Connection conn = ConnectionPool.getConnection();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)) {

            while(rs.next()) {
                userList.add(new User(rs.getString("name")));
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new SQLException(e.getCause());
        }
        return userList;
    }

    public User getUser(int userid) throws SQLException {
        User user = null;
        String sql = "select name from users where id = ?";
        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setInt(1, userid);

            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                user = new User(rs.getString("name"));
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new SQLException(e.getCause());
        }
        return user;
    }

}
