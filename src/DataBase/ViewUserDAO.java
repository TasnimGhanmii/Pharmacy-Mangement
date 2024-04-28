package DataBase;

import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class ViewUserDAO {
    Connection con = null;

    public ViewUserDAO(String url, String username, String password) {
        con = Connexion.getConnection(url, username, password);

    }

    public DefaultTableModel fetchUserData() {
        DefaultTableModel tableModel = new DefaultTableModel();

        try {
            // SQL query to fetch data from the medicine table
            String query = "SELECT * FROM person";
            PreparedStatement statement = con.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            // Populate the table model with data from the ResultSet
            ResultSetMetaData metaData = resultSet.getMetaData();

            // Get column count
            int columnCount = metaData.getColumnCount();

            // Add columns to the table model
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                tableModel.addColumn(metaData.getColumnName(columnIndex));

            }

            // Add rows to the table model
            while (resultSet.next()) {
                Object[] rowData = new Object[columnCount];
                for (int i = 0; i < columnCount; i++) {
                    rowData[i] = resultSet.getObject(i + 1);
                }
                tableModel.addRow(rowData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tableModel;
    }

    public boolean deleteUserByUsername(String user_name) {
        try {

            String query = "DELETE FROM person WHERE username=?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, user_name);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
