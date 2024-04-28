import DataBase.Config;
import DataBase.SellMedDao;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SellMedecine extends JFrame implements ActionListener {
    JLabel SellMed, MedId, Name, NB_unit, CompanyName, TotalPrice, Price, JLsearch;
    JTextField JMedId, JName, JNB_unit, JCompanyName, JTotalPrice, JPrice, JJLsearch;
    JButton cart, search, viewBill;
    JPanel pnorth, center;
    Border b;
    ImageIcon img;
    URL url;
    DefaultTableModel model,modelF;
    JTable table,tableF;
    JScrollPane scrollPane,scrollPaneF;

    SellMedecine() throws SQLException {
        this.setTitle("SELL MEDECINE");
        this.setSize(1200, 800);
        this.getContentPane().setBackground(Color.WHITE);
        this.setLayout(new BorderLayout());

        SellMed = new JLabel("SELL MEDECINE");
        Font f = new Font("Britannic Bold", Font.CENTER_BASELINE, 20);
        SellMed.setFont(f);
        pnorth = new JPanel(new BorderLayout()); // Use BorderLayout
        pnorth.setPreferredSize(new Dimension(700, 50));
        b = new LineBorder(Color.white, 2);
        SellMed.setHorizontalAlignment(SwingConstants.CENTER);
        pnorth.setBorder(b);
        pnorth.add(SellMed, BorderLayout.CENTER);
        this.add(pnorth, BorderLayout.NORTH);

        center = new JPanel(null);
        center.setBackground(Color.DARK_GRAY);
        center.setBounds(0, 0, 700, 450);
        this.add(center, BorderLayout.CENTER);

        JLsearch = new JLabel("Search");
        JLsearch.setBounds(30, 25, 120, 20);
        JLsearch.setForeground(Color.WHITE);
        center.add(JLsearch);

        JJLsearch = new JTextField();
        JJLsearch.setBounds(30, 54, 250, 23);
        center.add(JJLsearch);

        MedId = new JLabel("Medecine ID");
        MedId.setForeground(Color.WHITE);
        MedId.setBounds(500, 25, 80, 20);
        center.add(MedId);

        JMedId = new JTextField();
        JMedId.setBounds(500, 54, 250, 23);
        center.add(JMedId);

        Name = new JLabel("Medecine Name");
        Name.setBounds(500, 100, 100, 20);
        Name.setForeground(Color.WHITE);
        center.add(Name);

        JName = new JTextField();
        JName.setBounds(500, 130, 250, 23);
        center.add(JName);

        NB_unit = new JLabel("NB units");
        NB_unit.setForeground(Color.WHITE);
        NB_unit.setBounds(850, 100, 90, 20);
        center.add(NB_unit);

        JNB_unit = new JTextField();
        JNB_unit.setBounds(850, 130, 250, 23);
        center.add(JNB_unit);

        CompanyName = new JLabel("Company Name");
        CompanyName.setForeground(Color.WHITE);
        CompanyName.setBounds(500, 175, 90, 20);
        center.add(CompanyName);

        JCompanyName = new JTextField();
        JCompanyName.setBounds(500, 208, 250, 23);
        center.add(JCompanyName);

        TotalPrice = new JLabel("Total Price");
        TotalPrice.setForeground(Color.WHITE);
        TotalPrice.setBounds(850, 175, 65, 23);
        center.add(TotalPrice);

        JTotalPrice = new JTextField();
        JTotalPrice.setBounds(850, 208, 250, 23);
        center.add(JTotalPrice);
        Price = new JLabel("Price Per Unit ");
        Price.setForeground(Color.WHITE);
        Price.setBounds(850, 25, 90, 23);
        center.add(Price);

        JPrice = new JTextField();
        JPrice.setBounds(850, 54, 250, 23);
        center.add(JPrice);

        url = getClass().getResource("/images/add to cart.png");
        img = new ImageIcon(url);
        cart = new JButton("Add To Cart", img);
        cart.setBounds(960, 245, 140, 30);
        center.add(cart);

        url = getClass().getResource("/images/search.png");
        img = new ImageIcon(url);
        search = new JButton("Search", img);
        search.setBounds(300, 54, 100, 30);
        center.add(search);

        table = new JTable();
        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(30, 90, 255, 300); // Adjust the size and position as needed
        center.add(scrollPane);

        model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Name");
        TableColumnModel columnModel = table.getColumnModel();
        table.setModel(model);
        SellMedDao m = new SellMedDao(Config.URL, Config.USERNAME, Config.PASSWORD);
        try {
            m.Tablefetch(model);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        InaccessibleCellEditor in = new InaccessibleCellEditor();
        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            columnModel.getColumn(i).setCellEditor(in);
        }



        url = getClass().getResource("/images/viewBill.png");
        img = new ImageIcon(url);
        Image original = img.getImage();
        Image resizedImage = original.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);

        viewBill = new JButton("view bill", resizedIcon);
        viewBill.setBounds(1000, 550, 130, 30);
        center.add(viewBill);


        tableF = new JTable();
        scrollPaneF = new JScrollPane(tableF);
        scrollPaneF.setBounds(30, 90, 255, 300); // Adjust the size and position as needed
        center.add(scrollPaneF);

        modelF = new DefaultTableModel();
        modelF.addColumn("ID");
        modelF.addColumn("Name");
        modelF.addColumn("Total Price");
        tableF.setModel(modelF);
        try {
            m.Tablefetch(modelF);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            columnModel.getColumn(i).setCellEditor(in);
        }

        search.addActionListener(this);
        cart.addActionListener(this);

        // Add document listener to JNB_unit
        JNB_unit.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                calculateTotalPrice();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                calculateTotalPrice();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                calculateTotalPrice();
            }
        });

            SellMedDao dao=new SellMedDao(Config.URL,Config.USERNAME,Config.PASSWORD);
            DefaultTableModel medicaFactureModel = dao.fetchMedicaFacture();
            JTable medicaFactureTable = new JTable(medicaFactureModel);
            JScrollPane scrollPane = new JScrollPane(medicaFactureTable);
            scrollPane.setBounds(700, 240, 255, 300); // Adjust the size and position as needed
            center.add(scrollPane);

        this.setVisible(true);
    }
    public void refreshMedicineTable() {
        model.setRowCount(0); // Clear existing rows from the table model
        SellMedDao dao = new SellMedDao(Config.URL, Config.USERNAME, Config.PASSWORD);
        try {
            dao.Tablefetch(model); // Fetch new data from the database
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == search) {
            SellMedDao dao = new SellMedDao(Config.URL, Config.USERNAME, Config.PASSWORD);
            String NOM = JJLsearch.getText();
            ResultSet rs = dao.SEARCH(NOM);
            try {
                if (rs.next()) // Check if there is at least one row in the result set
                {
                    JMedId.setText(rs.getString("ID"));
                    JName.setText(rs.getString("name"));
                    JCompanyName.setText(rs.getString("company name"));
                    JPrice.setText(String.valueOf(rs.getDouble("price")));
                } else {
                    JOptionPane.showMessageDialog(null, "Medecine doesn't exist!");
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        else  if (e.getSource() == cart) {
            SellMedDao dao = new SellMedDao(Config.URL, Config.USERNAME, Config.PASSWORD);
            int medId = Integer.parseInt(JMedId.getText()); // Assuming JMedId contains the Medecine ID
            double totalPrice = Double.parseDouble(JTotalPrice.getText()); // Assuming JTotalPrice contains the Total Price
            int nbUnits = Integer.parseInt(JNB_unit.getText()); // Assuming JNB_unit contains the Number of Units
            int id=1;
            try {

                dao.addToCart(medId, totalPrice, nbUnits,id);

                try {

                    dao.addToFacture(id);

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error adding to facture: " + ex.getMessage());
                }


                JOptionPane.showMessageDialog(null, "Added to cart successfully!");
                id++;
                refreshMedicineTable();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error adding to cart: " + ex.getMessage());
            }
        }
        else  {
            calculateTotalPrice();
        }

    }

    // Method to calculate the total price
    private void calculateTotalPrice() {
        String priceText = JPrice.getText();
        String quantityText = JNB_unit.getText();

        if (!priceText.isEmpty() && !quantityText.isEmpty()) {
            try {
                double price = Double.parseDouble(priceText);
                int quantity = Integer.parseInt(quantityText);
                double totalPrice = price * quantity;
                JTotalPrice.setText(String.valueOf(totalPrice));
            } catch (NumberFormatException ex) {
                // Handle invalid input
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid numbers.");
                JTotalPrice.setText(""); // Clear total price if input is invalid
            }
        } else {
            JTotalPrice.setText(""); // Clear total price if price or quantity is empty
        }
    }


}
