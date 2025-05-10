import DataBase.Config;
import DataBase.SellMedDao;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewBill extends JFrame {
    private JTable table;
    private DefaultTableModel model;

    public ViewBill() throws SQLException {
        setTitle("View All Bills");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Create a table to display bills
        model = new DefaultTableModel();
        model.addColumn("Facture ID");
        model.addColumn("Date");
        model.addColumn("Total");

        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Fetch and display all bills
        fetchBills();

        setVisible(true);
    }

    private void fetchBills() throws SQLException {
        SellMedDao dao = new SellMedDao(Config.URL, Config.USERNAME, Config.PASSWORD);
        ResultSet rs = dao.getAllBills();

        while (rs.next()) {
            Object[] row = {
                    rs.getInt("id"),
                    rs.getDate("date"),
                    rs.getDouble("total")
            };
            model.addRow(row);
        }
    }
}
