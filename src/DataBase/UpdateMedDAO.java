package DataBase;

import javax.swing.*;
import java.sql.*;

public class UpdateMedDAO {
    Connection con = null;

    public UpdateMedDAO(String url, String username, String password) {

        con = Connexion.getConnection(url, username, password);
    }


    public ResultSet rechrercher(int ID) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String query = "SELECT * FROM medecine WHERE id = ?";
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, ID);
            rs = pstmt.executeQuery();
            return rs;
        } catch (SQLException e) {
            // Log the error or handle it appropriately
            e.printStackTrace();
        }
        return null; // Return null if an exception occurs
    }


    public void update(ResultSet rs, String newCompanyName, String newMedicineName, double newPrice, int newQuantity, int addQuantity) {
        try {
            // Calculate the new quantity
            int updatedQuantity = newQuantity;
            if (addQuantity != 0) {
                updatedQuantity += addQuantity;
            }

            // Update query
            String query = "UPDATE medecine SET `company name` = ?, name = ?, price = ?, quantity = ? WHERE id = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, newCompanyName);
            pstmt.setString(2, newMedicineName);
            pstmt.setDouble(3, newPrice);
            pstmt.setInt(4, updatedQuantity);
            pstmt.setInt(5, rs.getInt("id")); // Assuming there's an 'id' column in the 'medecines' table

            // Execute update
            int rowsAffected = pstmt.executeUpdate();

            // Provide feedback
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Medicine updated successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to update medicine. Please check the input.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage());
        }

    }}

