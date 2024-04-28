package DataBase;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateUserDAO {
    Connection con = null;

    public UpdateUserDAO(String url, String username, String password) {
        con = Connexion.getConnection(url, username, password);
    }

    public ResultSet rechercher(String Username) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String query = "SELECT * FROM person WHERE username = ?";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, Username);
            rs = pstmt.executeQuery();
            return rs;
        } catch (SQLException e) {
            // Log the error or handle it appropriately
            e.printStackTrace();
            return null; // Return null if an exception occurs
        }
    }

    public void update(String role, String name, String email, String phone,
                       String dob, String address, String username) throws SQLException {
        String query = "UPDATE person SET role = ?, name = ?, email = ?, phone = ?, address = ?, dob = ? WHERE username = ?";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, role);
        pstmt.setString(2, name);
        pstmt.setString(3, email);
        pstmt.setString(4, phone);
        pstmt.setString(5, address);
        pstmt.setString(6, dob);
        pstmt.setString(7, username);

        // Execute update
        int rowsAffected = pstmt.executeUpdate();

        // Provide feedback
        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(null, "User updated successfully!");
        } else {
            JOptionPane.showMessageDialog(null, "Failed to update user. Please check the input.");
        }
    }
}
