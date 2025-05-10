package DataBase;

import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.time.LocalDate;
import java.util.List;
import model.CartItem;
public class SellMedDao {
    public Connection con = null;

    public SellMedDao(String url, String username, String password) {
        con = Connexion.getConnection(url, username, password);
    }

    // Fetch medicine details for the table
    public void Tablefetch(DefaultTableModel model) throws SQLException {
        try (Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT ID, name FROM medecine")) {

            while (resultSet.next()) {
                Object[] row = {resultSet.getInt("ID"), resultSet.getString("name")};
                model.addRow(row);
            }
        }
    }

    // Search for a medicine by name
    public ResultSet SEARCH(String name) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            String query = "SELECT * FROM medecine WHERE name = ?";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, name);
            rs = pstmt.executeQuery();
            return rs; // Return the open ResultSet
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void commitCartToDatabase(List<CartItem> cartItems) throws SQLException {
        con.setAutoCommit(false); // Begin transaction

        try {
            // Calculate total price
            double total = calculateTotalPrice(cartItems);

            // Insert facture record and get its ID
            LocalDate currentDate = LocalDate.now();
            int factureID = addToFacture(currentDate, total);

            // Insert cart items into medicafacture
            for (CartItem item : cartItems) {
                addToCart(item.getMedId(), factureID, item.getTotalPrice(), item.getNbUnits());
            }

            con.commit(); // Commit transaction
        } catch (SQLException ex) {
            con.rollback(); // Rollback on error
            throw ex;
        } finally {
            con.setAutoCommit(true); // Restore auto-commit mode
        }
    }

    private double calculateTotalPrice(List<CartItem> cartItems) {
        double total = 0.0;
        for (CartItem item : cartItems) {
            total += item.getTotalPrice();
        }
        return total;
    }

    public void addToCart(int medId, int billId, double totalPrice, int nbUnits) throws SQLException {
        String sql = "INSERT INTO medicafacture (bill_id, medicine_id, quantity, medicineTotalPrice) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, billId);
            pstmt.setInt(2, medId);
            pstmt.setInt(3, nbUnits);
            pstmt.setDouble(4, totalPrice);
            pstmt.executeUpdate();
        }
    }
    // Add a new facture entry
    public int addToFacture(LocalDate date, double total) throws SQLException {
        String sql = "INSERT INTO facture (date, total) VALUES (?, ?)";
        try (PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setDate(1, Date.valueOf(date));
            pstmt.setDouble(2, total);
            pstmt.executeUpdate();

            // Retrieve the generated ID for the newly inserted facture
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1); // Return the generated ID
                } else {
                    throw new SQLException("Creating facture failed, no ID obtained.");
                }
            }
        }
    }

    // Fetch all entries from MedicaFacture for display
    public DefaultTableModel fetchMedicaFacture() throws SQLException {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Bill ID");
        model.addColumn("Medicine ID");
        model.addColumn("Quantity");
        model.addColumn("Total Price");

        String sql = "SELECT * FROM medicafacture";
        try (Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Object[] row = {
                        resultSet.getInt("bill_id"),
                        resultSet.getInt("medicine_id"),
                        resultSet.getInt("quantity"),
                        resultSet.getDouble("medicineTotalPrice")
                };
                model.addRow(row);
            }
        }
        return model;
    }


    public ResultSet getAllBills() throws SQLException {
        String query = "SELECT * FROM facture";
        PreparedStatement pstmt = con.prepareStatement(query);
        return pstmt.executeQuery();
    }
}