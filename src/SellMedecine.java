import DataBase.Config;
import DataBase.SellMedDao;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import model.CartItem;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SellMedecine extends JFrame implements ActionListener {
    // Labels
    JLabel SellMed, MedId, Name, NB_unit, CompanyName, TotalPrice, Price, JLsearch;
    // Text Fields
    JTextField JMedId, JName, JNB_unit, JCompanyName, JTotalPrice, JPrice, JJLsearch;
    // Buttons
    JButton cart, search, viewBill;
    // Panels
    JPanel pnorth, center;
    // Borders and Images
    LineBorder b;
    ImageIcon img;
    URL url;
    // Tables and Models
    DefaultTableModel model, modelF;
    JTable table, tableF;
    JScrollPane scrollPane, scrollPaneF;
    // Local Cart Management
    private List<CartItem> cartItems;
    private int factureID; // Dynamically generated facture ID

    public SellMedecine() throws SQLException {
        // Initialize the cart
        cartItems = new ArrayList<>();

        // Generate a unique facture ID
        factureID = generateFactureID();

        // Frame Setup
        this.setTitle("SELL MEDICINE");
        this.setSize(1200, 800);
        this.getContentPane().setBackground(Color.WHITE);
        this.setLayout(new BorderLayout());

        // North Panel
        SellMed = new JLabel("SELL MEDICINE");
        Font f = new Font("Britannic Bold", Font.CENTER_BASELINE, 20);
        SellMed.setFont(f);
        pnorth = new JPanel(new BorderLayout());
        pnorth.setPreferredSize(new Dimension(700, 50));
        b = new LineBorder(Color.WHITE, 2);
        SellMed.setHorizontalAlignment(SwingConstants.CENTER);
        pnorth.setBorder(b);
        pnorth.add(SellMed, BorderLayout.CENTER);
        this.add(pnorth, BorderLayout.NORTH);

        // Center Panel
        center = new JPanel(null);
        center.setBackground(Color.DARK_GRAY);
        center.setBounds(0, 0, 700, 450);
        this.add(center, BorderLayout.CENTER);

        // Search Label and Field
        JLsearch = new JLabel("Search");
        JLsearch.setBounds(30, 25, 120, 20);
        JLsearch.setForeground(Color.WHITE);
        center.add(JLsearch);
        JJLsearch = new JTextField();
        JJLsearch.setBounds(30, 54, 250, 23);
        center.add(JJLsearch);

        // Medicine ID
        MedId = new JLabel("Medicine ID");
        MedId.setForeground(Color.WHITE);
        MedId.setBounds(500, 25, 80, 20);
        center.add(MedId);
        JMedId = new JTextField();
        JMedId.setBounds(500, 54, 250, 23);
        center.add(JMedId);

        // Medicine Name
        Name = new JLabel("Medicine Name");
        Name.setBounds(500, 100, 100, 20);
        Name.setForeground(Color.WHITE);
        center.add(Name);
        JName = new JTextField();
        JName.setBounds(500, 130, 250, 23);
        center.add(JName);

        // Number of Units
        NB_unit = new JLabel("Number of Units");
        NB_unit.setForeground(Color.WHITE);
        NB_unit.setBounds(850, 100, 90, 20);
        center.add(NB_unit);
        JNB_unit = new JTextField();
        JNB_unit.setBounds(850, 130, 250, 23);
        center.add(JNB_unit);

        // Company Name
        CompanyName = new JLabel("Company Name");
        CompanyName.setForeground(Color.WHITE);
        CompanyName.setBounds(500, 175, 90, 20);
        center.add(CompanyName);
        JCompanyName = new JTextField();
        JCompanyName.setBounds(500, 208, 250, 23);
        center.add(JCompanyName);

        // Total Price
        TotalPrice = new JLabel("Total Price");
        TotalPrice.setForeground(Color.WHITE);
        TotalPrice.setBounds(850, 175, 65, 23);
        center.add(TotalPrice);
        JTotalPrice = new JTextField();
        JTotalPrice.setBounds(850, 208, 250, 23);
        center.add(JTotalPrice);

        // Price Per Unit
        Price = new JLabel("Price Per Unit");
        Price.setForeground(Color.WHITE);
        Price.setBounds(850, 25, 90, 23);
        center.add(Price);
        JPrice = new JTextField();
        JPrice.setBounds(850, 54, 250, 23);
        center.add(JPrice);

        // Add to Cart Button
        url = getClass().getResource("/images/add to cart.png");
        img = new ImageIcon(url);
        cart = new JButton("Add To Cart", img);
        cart.setBounds(960, 245, 140, 30);
        center.add(cart);

        // Search Button
        url = getClass().getResource("/images/search.png");
        img = new ImageIcon(url);
        search = new JButton("Search", img);
        search.setBounds(300, 54, 100, 30);
        center.add(search);

        // Medicine Table
        table = new JTable();
        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(30, 90, 255, 300);
        center.add(scrollPane);
        model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Name");
        table.setModel(model);
        refreshMedicineTable();

        // View Bill Button
        url = getClass().getResource("/images/viewBill.png");
        img = new ImageIcon(url);
        Image original = img.getImage();
        Image resizedImage = original.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        viewBill = new JButton("View Bill", resizedIcon);
        viewBill.setBounds(1000, 550, 130, 30);
        center.add(viewBill);

        // Cart Table
        tableF = new JTable();
        scrollPaneF = new JScrollPane(tableF);
        scrollPaneF.setBounds(700, 240, 255, 300);
        center.add(scrollPaneF);
        modelF = new DefaultTableModel();
        modelF.addColumn("ID");
        modelF.addColumn("Name");
        modelF.addColumn("Total Price");
        tableF.setModel(modelF);

        // Action Listeners
        search.addActionListener(this);
        cart.addActionListener(this);
        viewBill.addActionListener(this);

        // Document Listener for Quantity Field
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

        this.setVisible(true);
    }

    // Refresh Medicine Table
    public void refreshMedicineTable() {
        model.setRowCount(0);
        SellMedDao dao = new SellMedDao(Config.URL, Config.USERNAME, Config.PASSWORD);
        try {
            dao.Tablefetch(model);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Handle Actions
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == search) {
            try {
                handleSearchAction();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } else if (e.getSource() == cart) {
            handleAddToCartAction();
        } else if (e.getSource() == viewBill) {
            generateBillPDF(); // Generate PDF when "View Bill" is clicked
            commitCartToDatabase();
        }
    }

    // Handle Search Button Action
    private void handleSearchAction() throws SQLException {
        String name = JJLsearch.getText();
        SellMedDao dao = new SellMedDao(Config.URL, Config.USERNAME, Config.PASSWORD);
        ResultSet rs = dao.SEARCH(name);
        try {
            if (rs.next()) {
                JMedId.setText(rs.getString("ID"));
                JName.setText(rs.getString("name"));
                JCompanyName.setText(rs.getString("company name"));
                JPrice.setText(String.valueOf(rs.getDouble("price")));
            } else {
                JOptionPane.showMessageDialog(null, "Medicine doesn't exist!");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error searching for medicine: " + ex.getMessage());
        }
    }

    // Handle Add to Cart Button Action
    private void handleAddToCartAction() {
        try {
            int medId = Integer.parseInt(JMedId.getText());
            double price = Double.parseDouble(JPrice.getText());
            int nbUnits = Integer.parseInt(JNB_unit.getText());
            double totalPrice = Double.parseDouble(JTotalPrice.getText());

            // Create a cart item
            CartItem item = new CartItem(medId, price, nbUnits, totalPrice);

            // Add to the local cart
            cartItems.add(item);

            // Refresh the cart table
            refreshCartTable();

            JOptionPane.showMessageDialog(null, "Added to cart successfully!");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter valid numbers.");
        }
    }

    // Calculate Total Price Dynamically
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
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid numbers.");
                JTotalPrice.setText("");
            }
        } else {
            JTotalPrice.setText("");
        }
    }

    // Refresh Cart Table
    private void refreshCartTable() {
        modelF.setRowCount(0); // Clear the table
        for (CartItem item : cartItems) {
            Object[] row = {
                    item.getMedId(),
                    item.getPrice(),
                    item.getTotalPrice()
            };
            modelF.addRow(row);
        }
    }

    // Commit Cart to Database
    private void commitCartToDatabase() {
        try {
            SellMedDao dao = new SellMedDao(Config.URL, Config.USERNAME, Config.PASSWORD);

            // Insert the facture record and get its ID
            LocalDate currentDate = LocalDate.now();
            double total = calculateGrandTotal();
            int generatedFactureID = dao.addToFacture(currentDate, total);

            // Insert all cart items into medicafacture
            for (CartItem item : cartItems) {
                dao.addToCart(item.getMedId(), generatedFactureID, item.getTotalPrice(), item.getNbUnits());
            }

            JOptionPane.showMessageDialog(null, "Cart committed successfully!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error committing cart: " + ex.getMessage());
        }
    }

    // Calculate the grand total of the cart
    private double calculateGrandTotal() {
        double total = 0.0;
        for (CartItem item : cartItems) {
            total += item.getTotalPrice();
        }
        return total;
    }

    // Get the list of cart items
    private List<CartItem> getCartItems() {
        return cartItems;
    }

    // Generate a unique facture ID
    private int generateFactureID() throws SQLException {
        SellMedDao dao = new SellMedDao(Config.URL, Config.USERNAME, Config.PASSWORD);
        String query = "SELECT MAX(id) AS maxID FROM facture";
        try (PreparedStatement pstmt = dao.con.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            if (rs.next()) {
                int maxID = rs.getInt("maxID");
                return maxID + 1; // Increment the maximum ID
            }
        }
        return 1; // Default value if no records exist
    }

    // Generate a PDF file for the bill
    private void generateBillPDF() {
        String pdfPath = "Bill_" + factureID + ".pdf"; // File name based on facture ID
        try {
            PdfWriter writer = new PdfWriter(new File(pdfPath));
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            // Add a title
            document.add(new Paragraph("BILL DETAILS").setBold().setFontSize(18));

            // Add facture ID and date
            LocalDate currentDate = LocalDate.now();
            document.add(new Paragraph("Facture ID: " + factureID));
            document.add(new Paragraph("Date: " + currentDate));

            // Create a table for cart items
            float[] columnWidths = {1, 3, 2, 2};
            Table table = new Table(columnWidths);
            table.addCell("ID");
            table.addCell("Name");
            table.addCell("Quantity");
            table.addCell("Total Price");

            double grandTotal = 0.0;

            for (CartItem item : cartItems) {
                table.addCell(String.valueOf(item.getMedId()));
                table.addCell(JName.getText()); // Use actual medicine name from the text field
                table.addCell(String.valueOf(item.getNbUnits()));
                table.addCell(String.valueOf(item.getTotalPrice()));
                grandTotal += item.getTotalPrice();
            }

            // Add the table to the document
            document.add(table);

            // Add the grand total
            document.add(new Paragraph("Grand Total: $" + grandTotal).setBold());

            // Close the document
            document.close();

            JOptionPane.showMessageDialog(null, "Bill saved as PDF: " + pdfPath);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error generating PDF: " + ex.getMessage());
        }
    }
}