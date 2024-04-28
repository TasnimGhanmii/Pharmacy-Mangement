package DataBase;

import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.time.LocalDate;

public class SellMedDao {
    Connection con = null;


    public SellMedDao(String url, String username, String password) {

        con = Connexion.getConnection(url, username, password);
    }

    public void Tablefetch( DefaultTableModel model) throws SQLException {
        // Create a statement
        Statement statement = con.createStatement();

        // Execute a query to select ID and name columns from the medecine table
        ResultSet resultSet = statement.executeQuery("SELECT ID, name FROM medecine");

        // Add rows to the model
        while (resultSet.next()) {
            Object[] row = {resultSet.getInt("ID"), resultSet.getString("name")};
            model.addRow(row);
        }
        resultSet.close();
        statement.close();

    }


    public ResultSet SEARCH(String name)
    {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String query = "SELECT * FROM medecine WHERE name = ?";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, name);
            rs = pstmt.executeQuery();
            return rs;
        } catch (SQLException e) {
            // Log the error or handle it appropriately
            e.printStackTrace();
            return null; // Return null if an exception occurs
        }
    }
    public void addToCart(int medId, double totalPrice, int nbUnits,int Id) throws SQLException {
        String sql = "INSERT INTO MedicaFacture (idFac,idMed, PriceF, nbF) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, Id);
            pstmt.setInt(2, medId);
            pstmt.setDouble(3, totalPrice);
            pstmt.setInt(4, nbUnits);

            pstmt.executeUpdate();
        }
    }

    private double calculateTotalPrice(int factureID) throws SQLException {
        String selectSql = "SELECT SUM(totalPrice) AS total FROM MedicaFacture WHERE idFac = ?";
        try (PreparedStatement pstmt = con.prepareStatement(selectSql)) {
            pstmt.setInt(1, factureID);
            try (ResultSet resultSet = pstmt.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getDouble("total");
                } else {
                    return 0; // Return 0 if there are no matching records in MedicaFacture
                }
            }
        }
    }


    public void addToFacture(int factureID) throws SQLException {
        // Calculate total price from MedicaFacture table for the given factureID
        double total = calculateTotalPrice(factureID);

        // Get current date
        LocalDate currentDate = LocalDate.now();
        Date date = Date.valueOf(currentDate);

        // Insert data into facture table
        String insertSql = "INSERT INTO facture (date,IDF,totaleF) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = con.prepareStatement(insertSql)) {
            pstmt.setInt(1, factureID);
            pstmt.setDate(2, date);
            pstmt.setDouble(3, total);
            pstmt.executeUpdate();
        }
    }

    public DefaultTableModel fetchMedicaFacture() throws SQLException {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("IDFac");
        model.addColumn("IDMed");
        model.addColumn("totalPrice");
        model.addColumn("NB_unitsF");

        if (con == null) {
            throw new SQLException("Connection is not established.");
        }

        String sql = "SELECT * FROM medicafacture";
        try (Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Object[] row = {
                        resultSet.getInt("idFac"),
                        resultSet.getInt("idMed"),
                        resultSet.getDouble("PriceF"),
                        resultSet.getInt("nbF")
                };
                model.addRow(row);
            }
        }
        return model;
    }


    public void ViewBill()
    {

    }

}



