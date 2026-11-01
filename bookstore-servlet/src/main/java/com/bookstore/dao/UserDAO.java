package com.bookstore.dao;

import com.bookstore.model.User;
import com.bookstore.util.DBConnection;
import com.bookstore.util.PasswordUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * users table-oda ella database work-um inga.
 */
public class UserDAO {

    private static final int MYSQL_DUPLICATE_ENTRY = 1062;

    // REGISTER: puthu user save pannum. Email already irundha false tharum.
    public boolean registerUser(String name, String email, String rawPassword) throws SQLException {
        String sql = "INSERT INTO users (name, email, password_hash, role) VALUES (?, ?, ?, 'USER')";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, PasswordUtil.hash(rawPassword));
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            if (e.getErrorCode() == MYSQL_DUPLICATE_ENTRY) {
                return false; // same email already registered
            }
            throw e;
        }
    }

    public boolean emailExists(String email) throws SQLException {
        String sql = "SELECT 1 FROM users WHERE email = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    // LOGIN: email + password correct-aa irundha User tharum, illana null.
    public User login(String email, String rawPassword) throws SQLException {
        String sql = "SELECT id, name, email, password_hash, role FROM users WHERE email = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String storedHash = rs.getString("password_hash");
                    if (PasswordUtil.verify(rawPassword, storedHash)) {
                        User u = new User();
                        u.setId(rs.getInt("id"));
                        u.setName(rs.getString("name"));
                        u.setEmail(rs.getString("email"));
                        u.setRole(rs.getString("role"));
                        // passwordHash-a session-la vaikka vendaam, so set pannala
                        return u;
                    }
                }
            }
        }
        return null;
    }
}
