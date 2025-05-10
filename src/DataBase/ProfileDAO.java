package DataBase;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfileDAO {
    static Connection con = null;

    public ProfileDAO(String url, String username, String password) {

        con = Connexion.getConnection(url, username, password);

    }

    public void update( String name, String phone, String email,String address) throws SQLException {
        String query = "UPDATE person SET  name = ?, email = ?, phone = ?, address = ? WHERE email = ?";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, name);
        pstmt.setString(2, email);
        pstmt.setString(3, phone);
        pstmt.setString(4, address);
        pstmt.setString(5, email);



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

